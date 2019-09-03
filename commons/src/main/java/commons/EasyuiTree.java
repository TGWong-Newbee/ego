package commons;

import java.util.List;

/**
 * Author : 王定
 * Date : 2019-08-13 10:31
 * Desscription : <描述>
 */
public class EasyuiTree<T> {
    private Long id;
    private String text;
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
