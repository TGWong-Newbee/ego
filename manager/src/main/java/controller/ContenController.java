package controller;

import commons.EasyUIDataGrid;
import commons.EasyuiStatus;
import consumerInterface.ConsumerContentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Tb_content;

/**
 * Created by 王俊 on 2019/8/19.
 */
@Controller
public class ContenController {
    @Autowired
    private ConsumerContentInterface consumerContentService;

    @RequestMapping("content/query/list")
    @ResponseBody
    public EasyUIDataGrid findContent (@RequestParam(defaultValue = "1") int page,int rows,long categoryId){
        return  consumerContentService.findContenByCatId(categoryId, page, rows);
    }
    @RequestMapping("/content/save")
    @ResponseBody
    public EasyuiStatus addContent(Tb_content tb_content){
        return consumerContentService.addContent(tb_content);
    }
    @RequestMapping("/rest/content/edit")
    @ResponseBody
    public  EasyuiStatus updateContent(Tb_content tb_content){
        return consumerContentService.updateContent(tb_content);
    }
    @RequestMapping("/content/delete")
    @ResponseBody
    public EasyuiStatus deleteContent(String ids){
        return consumerContentService.deleteContent(ids);
    }
}
