package pojo;

/**
 * Created by 王俊 on 2019/8/26.
 */
public class ItemCustomer extends Tb_item {
    private String[] images;
    private Boolean enough;

    public Boolean getEnough() {
        return enough;
    }

    public void setEnough(Boolean enough) {
        this.enough = enough;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }
}
