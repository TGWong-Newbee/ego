package service;

import pojo.CreateOrderParam;
import pojo.ItemCustomer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by 王俊 on 2019/8/27.
 */
public interface OrderService {
    List<ItemCustomer> showOrder(List<Long> ids,HttpServletRequest request);
    public Map<String,Object> createOrder(CreateOrderParam orderParam,HttpServletRequest request);
}
