package controller;

import itemService.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 王俊 on 2019/8/22.
 */
@Controller
public class ItemDescController {
    @Autowired
    private ItemDescService itemDescService;
    @RequestMapping("item/desc/{id}.html")
    @ResponseBody
    public String showItemDesc(@PathVariable long id){

        return itemDescService.findDescById(id).getItemDesc();
    }
}
