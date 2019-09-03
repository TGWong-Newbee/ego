package mapper;

import pojo.Tb_item_param_item;
import pojo.Tb_item_param_itemExample;

import java.util.List;

public interface Tb_item_param_itemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tb_item_param_item record);

    int insertSelective(Tb_item_param_item record);

    List<Tb_item_param_item> selectByExampleWithBLOBs(Tb_item_param_itemExample example);

    List<Tb_item_param_item> selectByExample(Tb_item_param_itemExample example);

    Tb_item_param_item selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tb_item_param_item record);

    int updateByPrimaryKeyWithBLOBs(Tb_item_param_item record);

    int updateByPrimaryKey(Tb_item_param_item record);
}