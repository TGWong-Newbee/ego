package mapper;

import pojo.Tb_order;
import pojo.Tb_orderExample;

import java.util.List;

public interface Tb_orderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(Tb_order record);

    int insertSelective(Tb_order record);

    List<Tb_order> selectByExample(Tb_orderExample example);

    Tb_order selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(Tb_order record);

    int updateByPrimaryKey(Tb_order record);
}