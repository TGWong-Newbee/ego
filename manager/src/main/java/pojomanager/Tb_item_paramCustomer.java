package pojomanager;

import pojo.Tb_item_param;

/**
 * Author : 王定
 * Date : 2019-08-14 10:02
 * Desscription : 对Tb_item_param进行扩展，满足前台获取数据的需求
 */
public class Tb_item_paramCustomer extends Tb_item_param {

    private String itemCatName;

    public String getItemCatName() {
        return itemCatName;
    }
    public void setItemCatName(String itemCatName) {
        this.itemCatName = itemCatName;
    }
}
