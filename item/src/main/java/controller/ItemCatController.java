package controller;

import itemService.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 王俊 on 2019/8/20.
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;
    @RequestMapping("/rest/itemcat/all")
    @ResponseBody
    public MappingJacksonValue getCatNode(String callback) throws Exception {
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(itemCatService.findItemCat());
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
