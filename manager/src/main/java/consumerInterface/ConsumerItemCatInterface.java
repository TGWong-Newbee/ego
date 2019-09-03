package consumerInterface;

import commons.EasyuiTree;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/14.
 */
public interface ConsumerItemCatInterface {
    List<EasyuiTree> findItemCat(Long pid);
}
