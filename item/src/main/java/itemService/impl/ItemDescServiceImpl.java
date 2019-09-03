package itemService.impl;

import itemService.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_item_desc;
import providerInterface.ItemDescInterface;

/**
 * Created by 王俊 on 2019/8/22.
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private ItemDescInterface itemDescInterface;
    @Override
    public Tb_item_desc findDescById(long id) {
        return itemDescInterface.findItemDescById(id);
    }
}
