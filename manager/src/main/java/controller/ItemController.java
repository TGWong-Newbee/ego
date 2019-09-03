package controller;

import commons.EasyUIDataGrid;
import commons.EasyuiStatus;
import consumerInterface.ConsumerItemInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Tb_item;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/13.
 */
@Controller
public class ItemController {
    @Autowired
    private ConsumerItemInterface consumerItemService;

    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGrid findAll(int page,int rows) throws Exception {
        EasyUIDataGrid easyUIDataGrid = consumerItemService.findAll(page, rows);
       List<Tb_item> list= easyUIDataGrid.getRows();
        System.out.println(list.size());
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        System.out.println(easyUIDataGrid.getTotal());
        System.out.println(easyUIDataGrid.getRows());
        return easyUIDataGrid;
    }
    //商品删除
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public EasyuiStatus deleteItem(String ids){
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        boolean res= consumerItemService.updateItemStatusById(ids,3);
        if(res){
            easyuiStatus.setStatus("200");
        }
        return easyuiStatus;
    }
    //商品上架
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public EasyuiStatus refreItem(String ids){
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        boolean res= consumerItemService.updateItemStatusById(ids,1);
        if(res){
            easyuiStatus.setStatus("200");
        }
        return easyuiStatus;
    }
    //商品下架
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public EasyuiStatus instock(String ids){
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        boolean res= consumerItemService.updateItemStatusById(ids,2);
        if(res){
            easyuiStatus.setStatus("200");
        }
        return easyuiStatus;
    }

    //商品新增
    @RequestMapping("/item/save")
    @ResponseBody
    public EasyuiStatus addItem(Tb_item item, String  desc,String itemParams){
        return consumerItemService.addItem(item,desc,itemParams);
    }
}
