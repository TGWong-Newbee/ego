package providerService;

import mapper.Tb_item_catMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_item_cat;
import pojo.Tb_item_catExample;
import providerInterface.ItemCatInterface;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/14.
 */
@Service
public class ItemCatService implements ItemCatInterface {
    @Autowired
    private Tb_item_catMapper tb_item_catMapper;
    @Override
    public List<Tb_item_cat> findItemCat(Long pid) {
        Tb_item_catExample example = new Tb_item_catExample();
        //封装查询条件
        example.createCriteria().andParentIdEqualTo(pid);
        List<Tb_item_cat> tb_item_catList=  tb_item_catMapper.selectByExample(example);
        return tb_item_catList;
    }

    @Override
    public Tb_item_cat findItemCatById(Long id) {
        return tb_item_catMapper.selectByPrimaryKey(id);
    }

}
