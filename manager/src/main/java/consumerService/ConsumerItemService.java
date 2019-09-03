package consumerService;

import commons.EasyUIDataGrid;
import commons.EasyuiStatus;
import consumerInterface.ConsumerItemInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pojo.Tb_item;
import pojo.Tb_item_desc;
import pojo.Tb_item_param_item;

import providerInterface.ItemDescInterface;
import providerInterface.ItemInterface;
import utils.HttpClientUtil;
import utils.IDUtils;
import utils.JsonUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王俊 on 2019/8/13.
 */
@Service
public class ConsumerItemService implements ConsumerItemInterface {
    @Autowired
    private ItemInterface itemService;
    /*@Autowired
    private ItemCatInterface itemCatInterface;*/
    @Autowired
    ItemDescInterface descInterface;
    /*@Autowired
    private HttpClient httpClient;*/
    @Value("${httpcliten_url}")
    private String url;
    @Override
       public EasyUIDataGrid findAll(int page, int rows) throws Exception {
        return itemService.findAll(page,rows);
    }

    @Override
    public boolean updateItemStatusById(String ids,int status) {
            int res=0;
        String idArray[]=ids.split(",");
        for (int i=0;i<idArray.length;i++){
            Long id = Long.parseLong(idArray[i]);
            Tb_item tb_item=new Tb_item();
            tb_item.setId(id);
            tb_item.setStatus((byte)status);
            itemService.updateItemStatusById(tb_item);
            res+=1;
        }
            if(res==idArray.length){
                return true;
            }
        return false;
    }

    @Override
    public EasyuiStatus addItem(Tb_item tb_item, String desc, String itemParams)  {
        new Thread(){
            @Override
            public void run() {
                //将商品信息同步到索引库中   调用  ego-search 的控制器
                Map<String,Object> params = new HashMap<>();
                params.put("items",tb_item);
                params.put("desc",desc);
                HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(params));
            }
    }.start();

        Date date=new Date();
            tb_item.setId(IDUtils.genItemId());
            tb_item.setCreated(date);
            tb_item.setUpdated(date);
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        Tb_item_desc tb_item_desc=new Tb_item_desc();
            tb_item_desc.setItemDesc(desc);
            tb_item_desc.setCreated(date);
            tb_item_desc.setUpdated(date);
        tb_item_desc.setItemId(tb_item.getId());
        Tb_item_param_item tb_item_param_item=new Tb_item_param_item();
            tb_item_param_item.setCreated(date);
            tb_item_param_item.setUpdated(date);
            tb_item_param_item.setParamData(itemParams);
            tb_item_param_item.setItemId(tb_item.getId());
        int res=itemService.addItem(tb_item, tb_item_desc, tb_item_param_item);

        if (res==1){
            easyuiStatus.setStatus("200");
        }
        return easyuiStatus;
    }
}
