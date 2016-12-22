package aa.edu.com.dc.bean;

/**
 * Created by Administrator on 2016/12/19 0019.
 */

public class Dish {

    private int dishId;
    private String dishName;
    private String price;
    private String introduction;
    private String dishClass;
    private String imgDownload;
    private String imgPath;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getDishClass() {
        return dishClass;
    }

    public void setDishClass(String dishClass) {
        this.dishClass = dishClass;
    }

    public String getImgDownload() {
        return imgDownload;
    }

    public void setImgDownload(String imgDownload) {
        this.imgDownload = imgDownload;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }


    public Dish(String dishName, String price, String introduction, String dishClass, String imgDownload, String imgPath) {

        this.dishName = dishName;
        this.price = price;
        this.introduction = introduction;
        this.dishClass = dishClass;
        this.imgDownload = imgDownload;
        this.imgPath = imgPath;
    }

    public Dish() {
    }
}
