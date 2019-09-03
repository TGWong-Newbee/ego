package consumerService;

import commons.EasyuiTree;
import consumerInterface.ConsumerItemCatInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_item_cat;
import providerInterface.ItemCatInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王俊 on 2019/8/14.
 */
@Service
public class ConsumerItemCatService implements ConsumerItemCatInterface {
    @Autowired
    private ItemCatInterface itemCatService;
    @Override
    public List<EasyuiTree> findItemCat(Long pid) {
        List<EasyuiTree> treeList=new ArrayList<EasyuiTree>() ;
        List<Tb_item_cat> list=  itemCatService.findItemCat(pid);
        for(Tb_item_cat tb_item_cat:list){
            EasyuiTree tree=new EasyuiTree();
            tree.setId(tb_item_cat.getId());
            tree.setText(tb_item_cat.getName());
            tree.setState(tb_item_cat.getIsParent()?"closed":"open");
            treeList.add(tree);
        }
        return treeList;
    }
}
