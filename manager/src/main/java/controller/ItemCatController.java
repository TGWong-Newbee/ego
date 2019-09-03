package controller;

import commons.EasyuiTree;
import consumerInterface.ConsumerItemCatInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/14.
 */
@Controller
public class ItemCatController {
    @Autowired
    private ConsumerItemCatInterface consumerItemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyuiTree> findItemCat(@RequestParam(defaultValue = "0")Long id){

        return consumerItemCatService.findItemCat(id);
    }
}
