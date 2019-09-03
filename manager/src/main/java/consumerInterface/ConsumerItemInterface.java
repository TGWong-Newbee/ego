package consumerInterface;

import commons.EasyUIDataGrid;
import commons.EasyuiStatus;
import pojo.Tb_item;

/**
 * Created by 王俊 on 2019/8/13.
 */
public interface ConsumerItemInterface {
    /**
     * 分页查询所有商品信息
     */
    EasyUIDataGrid findAll(int page , int row) throws Exception;
    /**
     * 逻辑删除商品
     */
    boolean updateItemStatusById(String ids,int status);
    EasyuiStatus addItem(Tb_item tb_item,String desc,String itemParams);
}
