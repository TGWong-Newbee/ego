package consumerService;

import commons.EasyUIDataGrid;
import commons.EasyuiStatus;
import consumerInterface.ConsumerItemParamInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_item_param;
import pojomanager.Tb_item_paramCustomer;
import providerInterface.ItemCatInterface;
import providerInterface.ItemParamInterface;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 王俊 on 2019/8/15.
 */
@Service
public class ConsumerItemParamService implements ConsumerItemParamInterface {
    @Autowired
    private ItemParamInterface ItemParamService;
    @Autowired
    private ItemCatInterface itemCatService;
    @Override
    public EasyUIDataGrid findAllItemParam(int page, int rows) {
        EasyUIDataGrid easyUIDataGrid= ItemParamService.findAllItemParam(page, rows);
        List<Tb_item_paramCustomer> customerList=new ArrayList<>();
        List<Tb_item_param> list= easyUIDataGrid.getRows();
        for(Tb_item_param item_param:list){
            Tb_item_paramCustomer customer=new Tb_item_paramCustomer();
            customer.setId(item_param.getId());
            customer.setCreated(item_param.getCreated());
            customer.setItemCatId(item_param.getItemCatId());
            customer.setUpdated(item_param.getUpdated());
            customer.setParamData(item_param.getParamData());
            customer.setItemCatName(itemCatService.findItemCatById(item_param.getId()).getName());
            customerList.add(customer);
        }
        easyUIDataGrid.setRows(customerList);
        return easyUIDataGrid;
    }

    @Override
    public EasyuiStatus deleteItemParam(String ids) {
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        int res=0;
        String []idArr=ids.split(",");
        for (int i=0;i<idArr.length;i++){
            res+=ItemParamService.deleteItemParam(Long.parseLong(idArr[i]));
        }
        if(res==idArr.length){
            easyuiStatus.setStatus("200");
        }
        return easyuiStatus;
    }

    @Override
    public EasyuiStatus findItemParamByCatId(Long id) {
        Tb_item_param tb_item_param= ItemParamService.findItemParamByCatId(id);
       EasyuiStatus easyuiStatus=new EasyuiStatus();
        easyuiStatus.setData(tb_item_param);
        easyuiStatus.setStatus("200");
        System.out.println(tb_item_param.getParamData());
        return easyuiStatus;
    }

    @Override
    public EasyuiStatus addItemParam(String paramData, Long cid) {
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        Tb_item_param item_param=new Tb_item_paramCustomer();
        item_param.setParamData(paramData);
        item_param.setItemCatId(cid);
        Date date=new Date();
        item_param.setUpdated(date);
        item_param.setCreated(date);
        int res=ItemParamService.addItemParam(item_param);
        if (res==1){
            easyuiStatus.setStatus("200");
        }
        return easyuiStatus;
    }
}
