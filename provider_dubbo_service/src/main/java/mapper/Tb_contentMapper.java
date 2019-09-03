package mapper;

import pojo.Tb_content;
import pojo.Tb_contentExample;

import java.util.List;

public interface Tb_contentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tb_content record);

    int insertSelective(Tb_content record);

    List<Tb_content> selectByExampleWithBLOBs(Tb_contentExample example);

    List<Tb_content> selectByExample(Tb_contentExample example);

    Tb_content selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tb_content record);

    int updateByPrimaryKeyWithBLOBs(Tb_content record);

    int updateByPrimaryKey(Tb_content record);
}