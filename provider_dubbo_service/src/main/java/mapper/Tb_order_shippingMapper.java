package mapper;

import pojo.Tb_order_shipping;
import pojo.Tb_order_shippingExample;

import java.util.List;

public interface Tb_order_shippingMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Tb_order_shipping record);

    int insertSelective(Tb_order_shipping record);

    List<Tb_order_shipping> selectByExample(Tb_order_shippingExample example);

    Tb_order_shipping selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Tb_order_shipping record);

    int updateByPrimaryKey(Tb_order_shipping record);
}