/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.bean;

import com.demo.image.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author RND SOFTWARE
 */
@ManagedBean
public class Display {
    private String imgName;
    
    private List<Image> images = null;

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
    
    public void display() throws ClassNotFoundException, SQLException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=Images";
        String usrName = "sa";
        String pwd = "Passw0rd";
        Connection con = DriverManager.getConnection(dbURL, usrName, pwd);
        
        if (con == null){
            System.out.println("No connection");
            return;
        } else {
            System.out.println("Connected");
        }
        
        String query = "SELECT * FROM Images";
        PreparedStatement pstmt = con.prepareStatement(query);
        System.out.println("Before query execution");
        ResultSet rs = pstmt.executeQuery();
        System.out.println("After query execution");
        
        images = new ArrayList<>();
        while (rs.next()){
            String firstParam = rs.getString("ImageName");
            String secondParam = rs.getString("ImagePath");
            System.out.println(firstParam + "    " + secondParam);
            
            images.add(new Image(firstParam, secondParam));
        }
    }
}
