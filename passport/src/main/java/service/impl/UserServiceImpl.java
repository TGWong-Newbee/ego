package service.impl;

import commons.EasyuiStatus;
import jedisDao.JedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_user;
import providerInterface.UserInterFace;
import service.UserService;
import utils.CookieUtils;
import utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by 王俊 on 2019/8/23.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JedisDao jedisDao;
    @Autowired
    private UserInterFace userInterFace;
    @Override
    public EasyuiStatus login(Tb_user tb_user, HttpServletRequest request, HttpServletResponse response) {
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        Tb_user user= userInterFace.findUser(tb_user);
        if (user!=null){
            easyuiStatus.setStatus("200");
            String key= UUID.randomUUID().toString();
            String value= JsonUtils.objectToJson(user);
            jedisDao.set(key,value);
            //设置redis的存放时间
            jedisDao.expire(key,24*60*60*7);

            CookieUtils.setCookie(request,response,"TT_TOKEN",key,24*60*60*7);
        }else {
            easyuiStatus.setMsg("用户名或密码有误！");
        }
        return easyuiStatus;
    }

    @Override
    public EasyuiStatus selUserByToken(String key) {

        EasyuiStatus easyuiStatus=new EasyuiStatus();
       if( jedisDao.exist(key)){
           String value=jedisDao.get(key);
           easyuiStatus.setStatus("200");
           easyuiStatus.setMsg("OK");
           easyuiStatus.setData(JsonUtils.jsonToPojo(value,Tb_user.class));
       }

        return easyuiStatus;
    }

    @Override
    public EasyuiStatus userLogout(String key, HttpServletRequest request,HttpServletResponse response) {
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        jedisDao.del(key);
        CookieUtils.deleteCookie(request,response,"TT_TOKEN");
        easyuiStatus.setMsg("OK");
        easyuiStatus.setStatus("200");

        return easyuiStatus;
    }
}
