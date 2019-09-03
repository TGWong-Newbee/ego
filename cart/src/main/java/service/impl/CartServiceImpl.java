package service.impl;

import commons.EasyuiStatus;
import jedisDao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.ItemCustomer;
import pojo.Tb_item;
import providerInterface.ItemInterface;
import service.CartService;
import utils.CookieUtils;
import utils.HttpClientUtil;
import utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by 王俊 on 2019/8/26.
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private JedisDao jedisDao;
    @Autowired
    private ItemInterface itemInterface;
    @Value("${url}")
    private  String passportUrl;
    @Value("${cart_key}")
    private String cart_key;
    @Override
    public void addCart(long id, int num, HttpServletRequest request) {
        String key=getKey(request);
        List<ItemCustomer> customerList=new ArrayList<>();
        if (jedisDao.exist(key)){

            String json=jedisDao.get(key);
            if(json!=null&&!json.equals("")) {
            customerList=JsonUtils.jsonToList(json,ItemCustomer.class);
                for (ItemCustomer itemCustomer : customerList) {
                    if (id == itemCustomer.getId()) {
                        itemCustomer.setNum(itemCustomer.getNum() + num);
                        jedisDao.set(key, JsonUtils.objectToJson(customerList));
                        return;
                    }
                }
            }
        }
        Tb_item item=itemInterface.findItemById(id);
        //将Tb_item转成ItemCustomer
        ItemCustomer itemCustomer = new ItemCustomer();
        itemCustomer.setId(id);
        itemCustomer.setTitle(item.getTitle());
        itemCustomer.setNum(num);
        itemCustomer.setPrice(item.getPrice());
        itemCustomer.setImages(item.getImage()==null||item.getImage().equals("")?new String[1]:item.getImage().split(","));
        customerList.add(itemCustomer);
        jedisDao.set(key,JsonUtils.objectToJson(customerList));
    }

    @Override
    public List<ItemCustomer> showCart(HttpServletRequest request) {
        String key=getKey(request);
       String json= jedisDao.get(key);
       List<ItemCustomer> itemCustomerList= JsonUtils.jsonToList(json, ItemCustomer.class);
        return itemCustomerList;
    }

    @Override
    public EasyuiStatus uptadeCartItemNum(long id,int num,HttpServletRequest request) {
        String key=getKey(request);
        String json=jedisDao.get(key);
        List<ItemCustomer> customerList= JsonUtils.jsonToList(json, ItemCustomer.class);
        for (int i=0;i<customerList.size();i++){
            if(customerList.get(i).getId()==id){
                customerList.get(i).setNum(num);
                break;
            }
        }
        jedisDao.set(key,JsonUtils.objectToJson(customerList));
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        easyuiStatus.setStatus("200");
        return easyuiStatus;
    }

    @Override
    public EasyuiStatus deleteCartItem(long id,HttpServletRequest request) {
        String key=getKey(request);
        String json=jedisDao.get(key);
        List<ItemCustomer> customerList= JsonUtils.jsonToList(json, ItemCustomer.class);
        for (int i=0;i<customerList.size();i++){
            if(customerList.get(i).getId()==id){
                customerList.remove(i);
                break;
            }
        }
        jedisDao.set(key,JsonUtils.objectToJson(customerList));
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        easyuiStatus.setStatus("200");
        return easyuiStatus;
    }


    public  String  getKey(HttpServletRequest request){
        String token= CookieUtils.getCookieValue(request,"TT_TOKEN");
        String json=  HttpClientUtil.doPost(passportUrl + "/user/token/" + token);
        EasyuiStatus easyuiStatus= JsonUtils.jsonToPojo(json,EasyuiStatus.class);
        Map<String,Object> map = (HashMap<String,Object>)easyuiStatus.getData();
       String username= map.get("username").toString();
        return cart_key+username;
    }
}
