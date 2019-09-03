package controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Tb_item;
import service.SolrService;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王俊 on 2019/8/21.
 */
@Controller
public class SearchController {

    @Autowired
    private SolrService solrService;
    @RequestMapping(value="/import",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String importData(){
        try {
            solrService.impotData();
        } catch (Exception e) {
            e.printStackTrace();
            return "导入数据失败！";
        }
        return "导入数据成功！";
    }
    @RequestMapping("/solr/add")
    @ResponseBody
    public int addItemTosolr(@RequestBody Map<String,Object> params){
        Map<String ,Object> map= (HashMap<String, Object>) params.get("items");
        Tb_item tb_item=new Tb_item();
        tb_item.setId(Long.parseLong(map.get("id").toString()));
        tb_item.setTitle(map.get("title").toString());
        tb_item.setSellPoint(map.get("sellPoint").toString());
        tb_item.setPrice(Long.parseLong(map.get("price").toString()));
        tb_item.setImage(map.get("image").toString());
        tb_item.setCid(Long.parseLong(map.get("cid").toString()));
        String desc=params.get("desc").toString();
    return solrService.addItemToSolr(tb_item, desc);
    }


    @RequestMapping("/search.html")
    public  String itemSearch(@RequestParam(defaultValue = "1")int page,@RequestParam(defaultValue = "12")int rows, String q,Model model){
        try {
            q = new String(q.getBytes("ISO-8859-1"), "UTF-8");
            Map<String,Object>   map= solrService.searchItem(page, rows, q);
            model.addAttribute("page",page);
            model.addAttribute("totalPages",map.get("totalPages"));
            model.addAttribute("itemList",map.get("returnList"));
            model.addAttribute("query",q);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "search";
    }
}
