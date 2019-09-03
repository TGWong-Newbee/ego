package providerInterface;

import commons.EasyUIDataGrid;
import pojo.Tb_item_param;

/**
 * Created by 王俊 on 2019/8/15.
 */
public interface ItemParamInterface {
    EasyUIDataGrid findAllItemParam(int page,int rows);
    int deleteItemParam(Long id);
    Tb_item_param findItemParamByCatId(Long id);
    int addItemParam(Tb_item_param item_param);
}
