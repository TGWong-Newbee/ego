package providerInterface;

import pojo.Tb_content_category;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/19.
 */
public interface ContentCategoryInterface {
    List showChildNode (long id);
    int  addContentCategory(Tb_content_category content_category);
    int updateContentCategory(Tb_content_category content_category);
    int deleteContentCat(long id);
    long  findParentIdByIn(long id);
}
