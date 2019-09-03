package consumerInterface;

import commons.EasyuiStatus;
import commons.EasyuiTree;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/19.
 */
public interface ConsumerContentCategoryInterface {
    List<EasyuiTree> showContentCategory(long id);
    EasyuiStatus addContentCategory(long parentId,String name);
    EasyuiStatus deleteConttenCat(long id);
    EasyuiStatus updateContentCat(long id ,String name);
}
