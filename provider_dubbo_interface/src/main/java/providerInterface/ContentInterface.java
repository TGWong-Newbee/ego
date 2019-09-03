package providerInterface;

import commons.EasyUIDataGrid;
import pojo.Tb_content;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/19.
 */
public interface ContentInterface {
    EasyUIDataGrid findContentByContentCatId(long categoryId,int page,int rows);
    int addContent(Tb_content tb_content);
    int updateContent(Tb_content tb_content);
    int deleteContent(long id);
    List<Tb_content> showBigPic(int count, boolean isOrder);
}
