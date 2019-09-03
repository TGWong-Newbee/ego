package service;

import commons.EasyuiStatus;
import pojo.Tb_user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 王俊 on 2019/8/23.
 */
public interface UserService {
    EasyuiStatus login(Tb_user tb_user,HttpServletRequest request,HttpServletResponse response);
    EasyuiStatus selUserByToken(String key);
    EasyuiStatus userLogout(String key,HttpServletRequest request,HttpServletResponse response);
}
