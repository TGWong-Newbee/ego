package service.Impl;

import commons.EasyuiStatus;
import jedisDao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.*;
import providerInterface.ItemInterface;
import providerInterface.OrderInterface;
import service.OrderService;
import utils.CookieUtils;
import utils.HttpClientUtil;
import utils.IDUtils;
import utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by 王俊 on 2019/8/27.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private JedisDao jedisDao;
    @Autowired
    private ItemInterface itemInterface;
    @Autowired
    private OrderInterface orderInterface;


    @Value("${url}")
    private String passportUrl;
    @Value("${cart_key}")
    private String cart_key;
    @Override
    public List<ItemCustomer> showOrder(List<Long> ids, HttpServletRequest request) {
        List<ItemCustomer> returnList=new ArrayList<>();
        String key=getKey(request);
        String json=jedisDao.get(key);
        List<ItemCustomer> itemCustomerList=JsonUtils.jsonToList(json,ItemCustomer.class);
        for (Long id:ids){
            for (ItemCustomer itemCustomer:itemCustomerList){
                if (itemCustomer.getId().longValue()==id.longValue()){
                    if (itemCustomer.getNum()<=itemInterface.findItemById(id).getNum()){
                        itemCustomer.setEnough(true);
                    }else {
                        itemCustomer.setEnough(false);
                    }
                    returnList.add(itemCustomer);
                }
            }
        }
        return returnList;
    }


    public Map<String,Object> createOrder(CreateOrderParam orderParam,HttpServletRequest request){
        Date date=new Date();
        Tb_order order=new Tb_order();
        String token= CookieUtils.getCookieValue(request, "TT_TOKEN");
        String json=  HttpClientUtil.doPost(passportUrl + "/user/token/" + token);
        EasyuiStatus easyuiStatus= JsonUtils.jsonToPojo(json, EasyuiStatus.class);
        Map<String,Object> map = (HashMap<String,Object>)easyuiStatus.getData();
        String buyername= map.get("username").toString();
        Long buyerId=Long.parseLong(map.get("id").toString());

        String orderId=IDUtils.genItemId()+"";


        order.setBuyerNick(buyername);
        order.setOrderId(orderId);
        order.setPayment(orderParam.getPayment());
        order.setPaymentType(orderParam.getPaymentType());
        order.setCreateTime(date);
        order.setUserId(buyerId);

       List<Tb_order_item> order_itemList= orderParam.getOrderItems();
        for (Tb_order_item order_item:order_itemList){
            order_item.setId(IDUtils.genItemId()+"");
            order_item.setOrderId(orderId);
        }


       Tb_order_shipping order_shipping= orderParam.getOrderShipping();
        order_shipping.setOrderId(orderId);
        order_shipping.setCreated(date);
        order_shipping.setUpdated(date);
        order_shipping.setReceiverName(buyername);

        int res=orderInterface.createOrder(order,order_shipping,order_itemList);

        Map<String,Object> resmap=new HashMap<>();
        if (res==1){
            String key=getKey(request);
            String json2=jedisDao.get(key);
            List<ItemCustomer> itemCustomerList= JsonUtils.jsonToList(json2, ItemCustomer.class);
            for (int i=0;i<orderParam.getOrderItems().size();i++){
                for (int j=0;j<itemCustomerList.size();j++){
                    if (Long.parseLong(orderParam.getOrderItems().get(i).getItemId().toString())==itemCustomerList.get(j).getId().longValue()){
                        itemCustomerList.remove(j);
                        j--;
                    }

                }
            }
            jedisDao.set(key,JsonUtils.objectToJson(itemCustomerList));
            resmap.put("orderId",orderId);
            resmap.put("payment",orderParam.getPayment());
            resmap.put("date",date);
        }


    return  resmap;
    }






    public  String  getKey(HttpServletRequest request){
        String token= CookieUtils.getCookieValue(request, "TT_TOKEN");
        String json=  HttpClientUtil.doPost(passportUrl + "/user/token/" + token);
        EasyuiStatus easyuiStatus= JsonUtils.jsonToPojo(json, EasyuiStatus.class);
        Map<String,Object> map = (HashMap<String,Object>)easyuiStatus.getData();
        String username= map.get("username").toString();
        return cart_key+username;
    }
}
