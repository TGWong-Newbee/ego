package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author : 王定
 * Date : 2019-08-15 14:27
 * Desscription : <描述>
 */
@Controller
public class ItemsPortalController {

    @RequestMapping("/")
    public String index(){

       /* return "index";*/
        //转发到/showBigPic
        return "forward:/showBigPic";
    }
}
