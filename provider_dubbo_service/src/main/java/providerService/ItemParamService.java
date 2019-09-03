package providerService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import commons.EasyUIDataGrid;
import mapper.Tb_item_paramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_item_param;
import pojo.Tb_item_paramExample;
import providerInterface.ItemParamInterface;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/15.
 */
@Service
public class ItemParamService implements ItemParamInterface {
    @Autowired
    private Tb_item_paramMapper tb_item_paramMapper;
    @Override
    public EasyUIDataGrid findAllItemParam(int page, int rows) {
        PageHelper.startPage(page, rows);
        System.out.println(page+":"+rows);
        List<Tb_item_param> tb_item_params = tb_item_paramMapper.selectByExampleWithBLOBs(new Tb_item_paramExample());//  select * from tb_item
        System.out.println(tb_item_params);
        PageInfo<Tb_item_param> pageInfo = new PageInfo<>(tb_item_params);
        EasyUIDataGrid easyUIDataGrid = new EasyUIDataGrid();
        easyUIDataGrid.setTotal(pageInfo.getTotal());
        easyUIDataGrid.setRows(pageInfo.getList());
        return easyUIDataGrid;
    }

    @Override
    public int deleteItemParam(Long id) {

        return  tb_item_paramMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Tb_item_param findItemParamByCatId(Long id) {
        Tb_item_paramExample example=new Tb_item_paramExample();
        example.createCriteria().andItemCatIdEqualTo(id);
        List<Tb_item_param> list=tb_item_paramMapper.selectByExampleWithBLOBs(example);
        System.out.println("listSize"+list.size());
        if(list!=null&&list.size()>0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int addItemParam(Tb_item_param item_param) {

        return tb_item_paramMapper.insertSelective(item_param);
    }

}
