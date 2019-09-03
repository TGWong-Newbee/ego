package providerInterface;

import commons.EasyUIDataGrid;
import pojo.Tb_item;
import pojo.Tb_item_desc;
import pojo.Tb_item_param_item;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/13.
 */
public interface ItemInterface {
    /**
     * 分页查询所有商品信息
     */
    EasyUIDataGrid findAll(Integer page , Integer row) throws Exception;


    /**
     * 逻辑删除商品
     */
    int updateItemStatusById(Tb_item tb_item);

    /**
     * 新增商品
     */
    int addItem(Tb_item tb_item, Tb_item_desc desc, Tb_item_param_item tb_item_param_item);
    /**
     * solr导入数据
     */
    List<Tb_item> importData();
    Tb_item findItemById(long id);
}
