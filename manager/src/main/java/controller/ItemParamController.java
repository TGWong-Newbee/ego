package controller;

import commons.EasyUIDataGrid;
import commons.EasyuiStatus;
import consumerInterface.ConsumerItemParamInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by 王俊 on 2019/8/15.
 */
@Controller
public class ItemParamController {
    @Autowired
    private ConsumerItemParamInterface itemParamService;

    @RequestMapping("item/param/list")
    @ResponseBody
    public EasyUIDataGrid findAllItemParam(int page,int rows){
        return itemParamService.findAllItemParam(page,rows);
    }

    @RequestMapping("item/param/delete")
    @ResponseBody
    public EasyuiStatus deleteItemParam(String ids){
      return   itemParamService.deleteItemParam(ids);
    }
    @RequestMapping("/item/param/query/itemcatid/{itemcatid}")
    @ResponseBody
    public EasyuiStatus queryItemParamByCatId(@PathVariable Long itemcatid){
        return itemParamService.findItemParamByCatId(itemcatid);
    }
    @RequestMapping("item/param/save/{cid}")
    @ResponseBody
    public  EasyuiStatus addItemParam(String paramData,@PathVariable Long cid){
    return itemParamService.addItemParam(paramData,cid);
    }
}
