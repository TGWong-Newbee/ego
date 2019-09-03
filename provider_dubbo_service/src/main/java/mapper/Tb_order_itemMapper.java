package mapper;

import pojo.Tb_order_item;
import pojo.Tb_order_itemExample;

import java.util.List;

public interface Tb_order_itemMapper {
    int deleteByPrimaryKey(String id);

    int insert(Tb_order_item record);

    int insertSelective(Tb_order_item record);

    List<Tb_order_item> selectByExample(Tb_order_itemExample example);

    Tb_order_item selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Tb_order_item record);

    int updateByPrimaryKey(Tb_order_item record);
}