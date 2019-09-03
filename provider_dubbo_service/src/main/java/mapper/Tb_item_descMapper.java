package mapper;

import pojo.Tb_item_desc;
import pojo.Tb_item_descExample;

import java.util.List;

public interface Tb_item_descMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(Tb_item_desc record);

    int insertSelective(Tb_item_desc record);

    List<Tb_item_desc> selectByExampleWithBLOBs(Tb_item_descExample example);

    List<Tb_item_desc> selectByExample(Tb_item_descExample example);

    Tb_item_desc selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(Tb_item_desc record);

    int updateByPrimaryKeyWithBLOBs(Tb_item_desc record);

    int updateByPrimaryKey(Tb_item_desc record);
}