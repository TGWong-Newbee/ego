package providerService;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import commons.EasyUIDataGrid;
import mapper.Tb_contentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_content;
import pojo.Tb_contentExample;
import providerInterface.ContentInterface;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/19.
 */
@Service
public class ContentService implements ContentInterface {
    @Autowired
    private Tb_contentMapper contentMapper;
    @Override
    public EasyUIDataGrid findContentByContentCatId(long categoryId, int page, int rows) {
        PageHelper.startPage(page,rows);
        List<Tb_content> list=contentMapper.selectByExampleWithBLOBs(new Tb_contentExample());
        PageInfo pageInfo=new PageInfo(list);
       EasyUIDataGrid dataGrid=new EasyUIDataGrid();
        dataGrid.setTotal(pageInfo.getTotal());
        dataGrid.setRows(pageInfo.getList());
        return dataGrid;
    }

    @Override
    public int addContent(Tb_content tb_content) {
        return contentMapper.insertSelective(tb_content);
    }

    @Override
    public int updateContent(Tb_content tb_content) {
        return contentMapper.updateByPrimaryKeyWithBLOBs(tb_content);
    }

    @Override
    public int deleteContent(long id) {
        return contentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Tb_content> showBigPic(int count, boolean isOrder) {
        Tb_contentExample example=new Tb_contentExample();
        if (isOrder){
            example.setOrderByClause("updated desc");
        }
        if(count>0){
            PageHelper.startPage(1,count);
            List<Tb_content> list=contentMapper.selectByExampleWithBLOBs(example);
            PageInfo pageInfo=new PageInfo(list);
            return pageInfo.getList();
        }

        return null;
    }
}
