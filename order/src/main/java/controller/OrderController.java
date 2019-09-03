package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.CreateOrderParam;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 王俊 on 2019/8/27.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/order/order-cart.html")
    public String showOrder(@RequestParam("id") List<Long> ids,HttpServletRequest request){
        request.setAttribute("cartList",orderService.showOrder(ids, request));
        return "order-cart";
    }
    @RequestMapping("/order/create.html")
    public String createOrder(CreateOrderParam orderParam,HttpServletRequest request){
        Map<String,Object> map=orderService.createOrder(orderParam, request);

        request.setAttribute("orderId",map.get("orderId"));
        request.setAttribute("payment",map.get("payment").toString());
        request.setAttribute("date",map.get("date"));
        return "success";
    }
}
