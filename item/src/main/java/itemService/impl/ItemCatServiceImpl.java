package itemService.impl;

import itemService.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.PortalMenu;
import pojo.PortalMenuNode;
import pojo.Tb_item_cat;
import providerInterface.ItemCatInterface;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 王俊 on 2019/8/20.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatInterface itemCatInterface;
    @Override
    public PortalMenu findItemCat() throws Exception {
        PortalMenu portalMenu=new PortalMenu();
       List<Tb_item_cat> tb_item_catList= itemCatInterface.findItemCat(0L);

        List list=getPortalMenuNode(tb_item_catList);
        portalMenu.setData(list);
        return portalMenu;
    }
    private List getPortalMenuNode(List<Tb_item_cat> catList){
        List nodeList=new ArrayList<>();
        for (Tb_item_cat item_cat:catList){
            if (item_cat.getIsParent()){
                PortalMenuNode node = new PortalMenuNode();
                node.setU("/products/" + item_cat.getId() + ".html");
                node.setN("<a href='/products/"+item_cat.getId()+".html'>" + item_cat.getName() + "</a>");
                node.setI(getPortalMenuNode(itemCatInterface.findItemCat(item_cat.getId())));
                nodeList.add(node);
            }else {
                nodeList.add("/products/"+item_cat.getId()+".html|"+item_cat.getName());
            }
        }
        return nodeList;
    }
}
