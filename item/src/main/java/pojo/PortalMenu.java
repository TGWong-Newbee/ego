package pojo;

import java.util.List;

/**
 * Author : 王定
 * Date : 2019-08-15 14:57
 * Desscription : 封装向页面返回的数据
 */
public class PortalMenu {

    private List<Object> data;

    public void setData(List<Object> data) {
        this.data = data;
    }

    public List<Object> getData() {
        return data;
    }
}
