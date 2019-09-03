package providerService;

import mapper.Tb_userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_user;
import pojo.Tb_userExample;
import providerInterface.UserInterFace;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/23.
 */
@Service
public class UserService implements UserInterFace {
    @Autowired
    private Tb_userMapper tb_userMapper;
    @Override
    public Tb_user findUser(Tb_user user) {
        Tb_userExample example=new Tb_userExample();
       example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
        List<Tb_user> userList=tb_userMapper.selectByExample(example);

        return userList.get(0);
    }
}
