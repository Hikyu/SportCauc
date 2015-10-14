package sport.kyu.cauc.sportcauc.bean;

/**
 * Created by kyu on 2015/9/22 at 16:23.
 */
public class SportItem {
    private String imgUrl;
    private String present;
    private int imgLocal;
    public int getImgLocal() {
        return imgLocal;
    }

    public void setImgLocal(int imgLocal) {
        this.imgLocal = imgLocal;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPresent() {
        return present;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    @Override
    public String toString() {
        return "SportItem{" +
                "imgUrl='" + imgUrl + '\'' +
                ", present='" + present + '\'' +
                '}';
    }



}
