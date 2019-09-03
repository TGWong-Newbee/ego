package consumerInterface;

import commons.EasyUIDataGrid;
import commons.EasyuiStatus;

/**
 * Created by 王俊 on 2019/8/15.
 */
public interface ConsumerItemParamInterface {
    EasyUIDataGrid findAllItemParam(int page,int rows);
    EasyuiStatus deleteItemParam(String ids);
    EasyuiStatus findItemParamByCatId(Long id);
    EasyuiStatus addItemParam(String paramData,Long cid);
}
