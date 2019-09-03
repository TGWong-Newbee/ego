package consumerInterface;

import commons.EasyUIDataGrid;
import commons.EasyuiStatus;
import pojo.Tb_content;

/**
 * Created by 王俊 on 2019/8/19.
 */
public interface ConsumerContentInterface {
    EasyUIDataGrid findContenByCatId (long categoryId,int page,int rows);
    EasyuiStatus addContent(Tb_content tb_content);
    EasyuiStatus updateContent(Tb_content tb_content);
    EasyuiStatus deleteContent(String ids);
}
