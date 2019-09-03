package providerService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import commons.EasyUIDataGrid;
import mapper.Tb_itemMapper;
import mapper.Tb_item_descMapper;
import mapper.Tb_item_param_itemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_item;
import pojo.Tb_itemExample;
import pojo.Tb_item_desc;
import pojo.Tb_item_param_item;
import providerInterface.ItemInterface;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/13.
 */
@Service
public class ItemService implements ItemInterface {
    @Autowired
    private Tb_itemMapper tb_itemMapper;
    @Autowired
    private Tb_item_descMapper tb_item_descMapper;
    @Autowired
    private Tb_item_param_itemMapper tb_item_param_itemMapper;

    public EasyUIDataGrid findAll(Integer page, Integer rows) throws Exception {
        //设置分页参数
        PageHelper.startPage(page, rows);
        List<Tb_item> tb_itemList = tb_itemMapper.selectByExample(new Tb_itemExample());//  select * from tb_item
        PageInfo<Tb_item> pageInfo = new PageInfo<Tb_item>(tb_itemList);
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(pageInfo.getTotal());
        easyUIDataGrid.setRows(pageInfo.getList());

        return easyUIDataGrid;
    }

    @Override
    public int updateItemStatusById(Tb_item tb_item) {
       int result= tb_itemMapper.updateByPrimaryKeySelective(tb_item);
        return result;
    }

    @Override
    public int addItem(Tb_item tb_item, Tb_item_desc desc, Tb_item_param_item tb_item_param_item) {
        int res1=tb_itemMapper.insertSelective(tb_item);
        int res2=tb_item_descMapper.insertSelective(desc);
        int res3=tb_item_param_itemMapper.insertSelective(tb_item_param_item);
        int res=0;
        if (res1+res3+res2==3){
            res=1;
        }
        return res;
    }

    @Override
    public List<Tb_item> importData() {
        Tb_itemExample example=new Tb_itemExample();
        byte x=1;
        example.createCriteria().andStatusEqualTo(x);
        return tb_itemMapper.selectByExample(example);
    }

    @Override
    public Tb_item findItemById(long id) {
        return tb_itemMapper.selectByPrimaryKey(id);
    }

}
