package controller;

import itemService.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 王俊 on 2019/8/22.
 */
@Controller
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;
    @RequestMapping( value = "item/param/{id}.html" , produces = "text/html;charset=utf-8")
    @ResponseBody
    public String showItemParam(@PathVariable long id){

        return itemParamService.showItemParam(id);
    }
}
