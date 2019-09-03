package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 王俊 on 2019/8/13.
 */
@Controller
public class PageController {
    /**
     * 访问首页
     * @return
     */
    @RequestMapping("/")
    public String page(){

        return "index";
    }

    @RequestMapping("{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}

