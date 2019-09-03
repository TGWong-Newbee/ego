package providerInterface;

import pojo.Tb_item_cat;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/14.
 */
public interface ItemCatInterface {
    List<Tb_item_cat> findItemCat (Long pid);
    Tb_item_cat findItemCatById(Long id);
}
