package providerService;

import mapper.Tb_item_descMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_item_desc;
import providerInterface.ItemDescInterface;

/**
 * Created by 王俊 on 2019/8/21.
 */
@Service
public class ItemDescService implements ItemDescInterface {
    @Autowired
    private Tb_item_descMapper descMapper;
    @Override
    public Tb_item_desc findItemDescById(long id) {
        return descMapper.selectByPrimaryKey(id);
    }
}
