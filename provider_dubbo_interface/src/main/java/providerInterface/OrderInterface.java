package providerInterface;

import pojo.Tb_order;
import pojo.Tb_order_item;
import pojo.Tb_order_shipping;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/27.
 */
public interface OrderInterface {
    int createOrder(Tb_order tb_order, Tb_order_shipping tb_order_shipping, List<Tb_order_item> tb_order_itemList);
}
