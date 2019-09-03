package controller;

import commons.EasyuiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Tb_user;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 王俊 on 2019/8/23.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/user/showLogin")
    public String showLogin(@RequestHeader(value = "Referer") String redirectUrl, Model model,String  interUrl){
        if (interUrl!=null&&!interUrl.equals("")){
            model.addAttribute("redirect",interUrl);
        }else if ((interUrl==null||interUrl.equals(""))&&redirectUrl!=null&&!redirectUrl.equals("")){
            model.addAttribute("redirect", redirectUrl);
        }
        return "login";
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public EasyuiStatus login(Tb_user tb_user,HttpServletRequest request,HttpServletResponse response){

        return userService.login(tb_user, request, response);
    }
    @RequestMapping("/user/token/{key}")
    @ResponseBody
    public Object selUserByToken(@PathVariable String key,String callback){
        EasyuiStatus easyuiStatus=userService.selUserByToken(key);
        if (callback!=null&&!callback.equals("")){
            MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(easyuiStatus);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return easyuiStatus;
    }
    @RequestMapping("/user/logout/{key}")
    @ResponseBody
    public Object userlogout(@PathVariable String key,String callback,HttpServletRequest request,HttpServletResponse response) {
     EasyuiStatus easyuiStatus=   userService.userLogout(key, request, response);
    if (callback!=null&&!callback.equals("")){
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(easyuiStatus);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
        return userService.userLogout(key, request, response);
    }
}
