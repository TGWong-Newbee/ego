package commons;

import java.io.Serializable;
import java.util.List;

/**
 * Author : 王定
 * Date : 2019-08-12 14:17
 * Desscription : 向前台返回的的数据
 *
 */
public class EasyUIDataGrid<T> implements Serializable{

    private Long total;
    private List<T> rows;


    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
