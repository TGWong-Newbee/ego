package controller;

import commons.EasyuiStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CartService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 王俊 on 2019/8/26.
 */
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    @RequestMapping("cart/add/{id}.html")
    public String addCart(@PathVariable long id,int num,HttpServletRequest request){
       cartService.addCart(id, num, request);
        return "cartSuccess";
    }
    @RequestMapping("/cart/cart.html")
    public String showCart(HttpServletRequest request,Model model){
        model.addAttribute("cartList",cartService.showCart(request));
        return "cart";
    }
    @RequestMapping("cart/update/num/{id}/{num}.action")
    @ResponseBody
    public EasyuiStatus updateCartNum(HttpServletRequest request,@PathVariable long id,@PathVariable int num){
        return cartService.uptadeCartItemNum(id, num, request);
    }
    @RequestMapping("/cart/delete/{id}.action")
    @ResponseBody
    public EasyuiStatus deleteCartItem(HttpServletRequest request,@PathVariable long id){
        return  cartService.deleteCartItem(id,request);
    }
}
