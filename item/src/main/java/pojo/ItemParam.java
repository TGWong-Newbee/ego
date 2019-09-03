package pojo;

import java.util.List;

/**
 * Created by 王俊 on 2019/8/22.
 */
public class ItemParam {
    private String group;
    private List<Param> params;

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
