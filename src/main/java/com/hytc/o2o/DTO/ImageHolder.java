package com.hytc.o2o.DTO;

import java.io.InputStream;
import java.io.Serializable;

public class ImageHolder implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 6694954546980669373L;

    /** 图片名称 */
    private  String imageName;

    /** 图片流 */
    private InputStream image;

    public ImageHolder(String imageName, InputStream image) {
        this.imageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }
}
