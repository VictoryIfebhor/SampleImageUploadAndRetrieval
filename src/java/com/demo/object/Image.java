/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.object;

/**
 *
 * @author RND SOFTWARE
 */
public class Image {
    private String imgName;
    private String imgPath;
    
    public Image(String imgName, String imgPath){
        this.imgName = imgName;
        this.imgPath = imgPath;
    }

    public String getImgName() {
        System.out.println("Checking the image name" + imgName);
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgPath() {
        System.out.println("Checking the image path" + imgPath);
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
