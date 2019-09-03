package mapper;

import pojo.Tb_content_category;
import pojo.Tb_content_categoryExample;

import java.util.List;

public interface Tb_content_categoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tb_content_category record);

    int insertSelective(Tb_content_category record);

    List<Tb_content_category> selectByExample(Tb_content_categoryExample example);

    Tb_content_category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tb_content_category record);

    int updateByPrimaryKey(Tb_content_category record);
}