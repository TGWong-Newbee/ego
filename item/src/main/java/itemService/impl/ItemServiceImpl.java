package itemService.impl;

import itemService.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_item;
import pojoitem.ItemCluster;
import providerInterface.ItemInterface;

/**
 * Created by 王俊 on 2019/8/22.
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemInterface itemInterface;
    @Override
    public ItemCluster showItemById(long id) {
        Tb_item tb_item=itemInterface.findItemById(id);
        ItemCluster itemCluster=new ItemCluster();
        itemCluster.setId(tb_item.getId());
        itemCluster.setPrice(tb_item.getPrice());
        itemCluster.setCid(tb_item.getCid());
        itemCluster.setImages(tb_item.getImage().split(","));
        itemCluster.setSellPoint(tb_item.getSellPoint());
        itemCluster.setTitle(tb_item.getTitle());
        return itemCluster;
    }
}
