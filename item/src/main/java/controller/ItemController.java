package controller;

import itemService.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 王俊 on 2019/8/22.
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/item/{id}.html")
    public  String showItem(@PathVariable long id,Model model){
        model.addAttribute("item",itemService.showItemById(id));
        return "item";
    }
}
