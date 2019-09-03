package service;

import commons.EasyuiStatus;
import pojo.ItemCustomer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by 王俊 on 2019/8/26.
 */
public interface CartService {
    void addCart(long id,int num,HttpServletRequest request);
    List<ItemCustomer> showCart(HttpServletRequest request);
    EasyuiStatus uptadeCartItemNum(long id,int num,HttpServletRequest request);
    EasyuiStatus deleteCartItem(long id,HttpServletRequest request);
}
