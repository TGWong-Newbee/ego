package mapper;

import pojo.Tb_item_param;
import pojo.Tb_item_paramExample;

import java.util.List;

public interface Tb_item_paramMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tb_item_param record);

    int insertSelective(Tb_item_param record);

    List<Tb_item_param> selectByExampleWithBLOBs(Tb_item_paramExample example);

    List<Tb_item_param> selectByExample(Tb_item_paramExample example);

    Tb_item_param selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tb_item_param record);

    int updateByPrimaryKeyWithBLOBs(Tb_item_param record);

    int updateByPrimaryKey(Tb_item_param record);
}