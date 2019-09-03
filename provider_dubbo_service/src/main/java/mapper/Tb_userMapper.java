package mapper;

import pojo.Tb_user;
import pojo.Tb_userExample;

import java.util.List;

public interface Tb_userMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Tb_user record);

    int insertSelective(Tb_user record);

    List<Tb_user> selectByExample(Tb_userExample example);

    Tb_user selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tb_user record);

    int updateByPrimaryKey(Tb_user record);
}