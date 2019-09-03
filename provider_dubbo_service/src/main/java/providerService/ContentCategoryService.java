package providerService;

import mapper.Tb_content_categoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Tb_content_category;
import pojo.Tb_content_categoryExample;
import providerInterface.ContentCategoryInterface;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/19.
 */
@Service
public class ContentCategoryService implements ContentCategoryInterface {
    @Autowired
    private Tb_content_categoryMapper content_categoryMapper;
    @Override
    public List showChildNode(long id) {
        Tb_content_categoryExample example=new Tb_content_categoryExample();
       example.createCriteria().andParentIdEqualTo(id);
        List list=content_categoryMapper.selectByExample(example);

        return list;
    }

    @Override
    public int addContentCategory(Tb_content_category content_category) {

        return content_categoryMapper.insertSelective(content_category);
    }

    @Override
    public int updateContentCategory(Tb_content_category content_category) {
        return content_categoryMapper.updateByPrimaryKeySelective( content_category);
    }

    @Override
    public int deleteContentCat(long id ) {
        return content_categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public long findParentIdByIn(long id) {

       Tb_content_category tb_content_category= content_categoryMapper.selectByPrimaryKey(id);
        return tb_content_category.getParentId();
    }
}
