package providerService;

import mapper.Tb_orderMapper;
import mapper.Tb_order_itemMapper;
import mapper.Tb_order_shippingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_order;
import pojo.Tb_order_item;
import pojo.Tb_order_shipping;
import providerInterface.OrderInterface;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/27.
 */
@Service
public class OrderService implements OrderInterface {
    @Autowired
    private Tb_orderMapper tb_orderMapper;
    @Autowired
    private Tb_order_itemMapper tb_order_itemMapper;
    @Autowired
    private Tb_order_shippingMapper tb_order_shippingMapper;
    @Override
    public int createOrder(Tb_order tb_order, Tb_order_shipping tb_order_shipping, List<Tb_order_item> tb_order_itemList) {
       int res= tb_orderMapper.insertSelective(tb_order);

       for (Tb_order_item tb_order_item :tb_order_itemList) {
          res+= tb_order_itemMapper.insertSelective(tb_order_item);
       }
       res+= tb_order_shippingMapper.insertSelective(tb_order_shipping);
        if (res==2+tb_order_itemList.size()){
            return 1;
        }
        return 0;
    }
}
