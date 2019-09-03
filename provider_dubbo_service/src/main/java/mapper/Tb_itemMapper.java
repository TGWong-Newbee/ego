package mapper;

import pojo.Tb_item;
import pojo.Tb_itemExample;

import java.util.List;

public interface Tb_itemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tb_item record);

    int insertSelective(Tb_item record);

    List<Tb_item> selectByExample(Tb_itemExample example);

    Tb_item selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tb_item record);

    int updateByPrimaryKey(Tb_item record);
}