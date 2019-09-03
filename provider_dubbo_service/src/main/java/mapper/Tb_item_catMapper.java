package mapper;

import pojo.Tb_item_cat;
import pojo.Tb_item_catExample;

import java.util.List;

public interface Tb_item_catMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tb_item_cat record);

    int insertSelective(Tb_item_cat record);

    List<Tb_item_cat> selectByExample(Tb_item_catExample example);

    Tb_item_cat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tb_item_cat record);

    int updateByPrimaryKey(Tb_item_cat record);
}