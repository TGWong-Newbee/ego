package consumerService;

import commons.EasyuiStatus;
import commons.EasyuiTree;
import consumerInterface.ConsumerContentCategoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_content_category;
import providerInterface.ContentCategoryInterface;
import utils.IDUtils;

import java.util.*;

/**
 * Created by 王俊 on 2019/8/19.
 */
@Service
public class ConsumerContentCategoryService implements ConsumerContentCategoryInterface {
    @Autowired
    private ContentCategoryInterface contentCategoryInterface;
    @Override
    public List<EasyuiTree> showContentCategory(long id) {
        List<Tb_content_category> list= contentCategoryInterface.showChildNode(id);
        List<EasyuiTree> retrunList=new ArrayList<>();
        for(Tb_content_category content_category:list){
            EasyuiTree easyuiTree=new EasyuiTree();
            easyuiTree.setId(content_category.getId());
            easyuiTree.setText(content_category.getName());
            easyuiTree.setState(content_category.getIsParent()?"closed":"open");
            retrunList.add(easyuiTree);
        }
        return retrunList;
    }

    @Override
    public EasyuiStatus addContentCategory(long parentId,String name) {
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        List<Tb_content_category> list=contentCategoryInterface.showChildNode(parentId);
        for (Tb_content_category content_category:list){
            if(content_category.getName().equals(name)){
                return easyuiStatus;
            }
        }
        Tb_content_category content_category=new Tb_content_category();
        Date date=new Date();
        long id = IDUtils.genItemId();
        content_category.setId(id);
        content_category.setUpdated(date);
        content_category.setCreated(date);
        content_category.setIsParent(false);
        content_category.setName(name);
        content_category.setParentId(parentId);
        content_category.setSortOrder(1);
        content_category.setStatus(1);
        int res1= contentCategoryInterface.addContentCategory(content_category);
        int res2=0;
        if(res1>0){
            Tb_content_category parent =new Tb_content_category();
            parent.setId(content_category.getParentId());
            parent.setIsParent(true);
            parent.setUpdated(date);
            res2=contentCategoryInterface.updateContentCategory(parent);
        }

        if (res1+res2>1){
            easyuiStatus.setStatus("200");
            //将id传到页面
            Map<String,Long> data = new HashMap();
            data.put("id",id);
            easyuiStatus.setData(data);
        }
        return easyuiStatus;
    }

    @Override
    public EasyuiStatus deleteConttenCat(long id) {
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        int res=contentCategoryInterface.deleteContentCat(id);
        if (res>0){
            easyuiStatus.setStatus("200");
        }
        return easyuiStatus;
    }

    @Override
    public EasyuiStatus updateContentCat(long id, String name) {
        EasyuiStatus easyuiStatus=new EasyuiStatus();
        long parentId= contentCategoryInterface.findParentIdByIn(id);
        List<Tb_content_category> list= contentCategoryInterface.showChildNode(parentId);
        for (Tb_content_category content_category:list){
           if( content_category.getName().equals(name)){
               return  easyuiStatus;
           }
        }

        Tb_content_category content_category=new Tb_content_category();
        content_category.setId(id);
        content_category.setName(name);
        int res= contentCategoryInterface.updateContentCategory(content_category);
        if (res>0){
            easyuiStatus.setStatus("200");

        }
        return easyuiStatus;
    }
}
