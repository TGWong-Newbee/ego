package itemService;

import pojo.PortalMenu;

/**
 * Created by 王俊 on 2019/8/20.
 */
public interface ItemCatService {
    /**
     * 查询所有的  类目
     * @return
     */
    public PortalMenu findItemCat() throws Exception;
}
