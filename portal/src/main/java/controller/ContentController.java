package controller;

import service.ContentServicePortal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author : 王定
 * Date : 2019-08-19 9:51
 * Desscription : <描述>
 */
@Controller
public class ContentController {

    @Autowired
    private ContentServicePortal contentServicePortal;

    @RequestMapping("/showBigPic")
    public String showBigPic(Model model){

        model.addAttribute("ad1", contentServicePortal.showBigPic());
        return "index";
    }
}
