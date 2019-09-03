package providerService;

import mapper.Tb_item_param_itemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_item_param_item;
import pojo.Tb_item_param_itemExample;
import providerInterface.ItemParamItemInterface;

/**
 * Created by 王俊 on 2019/8/22.
 */
@Service
public class ItemParamItemService implements ItemParamItemInterface {
    @Autowired
    private Tb_item_param_itemMapper tb_item_param_itemMapper;
    @Override
    public Tb_item_param_item findItemParamItemByI(long id) {
        Tb_item_param_itemExample example=new Tb_item_param_itemExample();
        example.createCriteria().andItemIdEqualTo(id);
        return tb_item_param_itemMapper.selectByExampleWithBLOBs(example).get(0);
    }
}
