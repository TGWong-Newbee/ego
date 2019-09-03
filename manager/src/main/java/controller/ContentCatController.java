package controller;

import commons.EasyuiStatus;
import commons.EasyuiTree;
import consumerService.ConsumerContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/19.
 */
@Controller
public class ContentCatController {
    @Autowired
    private ConsumerContentCategoryService contentCategoryService;
    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyuiTree> showContentCat(@RequestParam(defaultValue = "0") long id){
       return contentCategoryService.showContentCategory(id);
    }

    @RequestMapping("/content/category/create")
    @ResponseBody
    public EasyuiStatus addContenCat(long parentId,String name){
        return contentCategoryService.addContentCategory(parentId,name);
    }
    @RequestMapping("/content/category/delete")
    @ResponseBody
    public EasyuiStatus deleteContCat(long id){
        return contentCategoryService.deleteConttenCat(id);
    }
    @RequestMapping("/content/category/update")
    @ResponseBody
    public EasyuiStatus updateContCat(long id,String name){
        return contentCategoryService.updateContentCat(id,name);
    }
}
