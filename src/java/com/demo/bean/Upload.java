/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author RND SOFTWARE
 */
@ManagedBean
public class Upload {
    private UploadedFile uploadedFile;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    
    public void save() throws ClassNotFoundException, SQLException, IOException{
        System.out.println("The beginning");
        System.out.println(1);
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=Images";
        String usrName = "sa";
        String pwd = "Passw0rd";
        Connection con = DriverManager.getConnection(dbURL, usrName, pwd);
//        Connection con = null;
        System.out.println(2);
        if (con == null){
            System.out.println("No connection");
            return;
        } else {
            System.out.println("Connected");
        }
        System.out.println(3);
        if (uploadedFile == null){
            System.out.println("No file uploaded");
            return;
        } else {
            System.out.println("File was uploaded");
        }
        System.out.println(4);
        String name = uploadedFile.getFileName();
        String home = System.getProperty("user.home");
        File directory = new File(home + "\\AppData\\Roaming\\SIUR\\images");
        System.out.println(5);
        if (!directory.exists()){
            System.out.println("The folder does not exist");
            boolean bool = directory.mkdir();
            
            if (bool){
                System.out.println("The image directory has been created");
            } else{
                System.out.println("The directory was not created!!!!");
            }
        } else {
            System.out.println("The folder already exists");
        }
        System.out.println(6);
        String path = (directory.getPath() + "\\" + name);
        System.out.println("1 " + directory.getPath()); System.out.println("2 " + path); System.out.println("3 " + directory.getAbsolutePath());
        String absPath = directory.getAbsolutePath() + "\\" + name;
        System.out.println("The path is: "+path);
        System.out.println("The absolute path is: "+absPath);
        System.out.println();
        try (//        System.out.println(System.getProperty("user.dir"));
                OutputStream outputStream = new FileOutputStream(absPath); 
                InputStream inputStream = uploadedFile.getInputStream()) {
            byte[] array = new byte[inputStream.available()];
            
            inputStream.read(array);
            outputStream.write(array);
            
        }
        
        String stamp = java.time.LocalDateTime.now().toString().replace(".", "").replace("-", "").replace(":", "");
        System.out.println(stamp);
        // rename the file so that images with the same name don't override each other
        File file = new File(absPath);
        
        // attaching time stamp to image name.
        
        // get the file extension
        String ext = absPath.substring(absPath.lastIndexOf("."), absPath.length());
        
        // get the file name gan gan (the name without the extension)
        String theFileNameGanGan = absPath.substring(home.length() + "\\AppData\\Roaming\\SIUR\\images".length(), absPath.lastIndexOf(".")).replace(" ", "_");
        
        // append everything to get the new path.
        String newPath = home + "\\AppData\\Roaming\\SIUR\\images" + theFileNameGanGan + stamp + ext;
        System.out.println("The old path is = " + absPath);
        System.out.println("The new path is = " + newPath);
        
        File newName = new File(newPath);
        
        if (file.renameTo(newName)){
            System.out.println("The file has successfully been renamed");
            String query = String.format("INSERT INTO Images VALUES('%s', '%s')", theFileNameGanGan, newPath);
            System.out.println(query);
            PreparedStatement pstmt = con.prepareStatement(query);
            System.out.println("Before query execution");
            int rows = pstmt.executeUpdate();
            if (rows > 0){
                System.out.println("Image has been uploaded successfully. No of rows affected = " + rows);
            } else {
                System.out.println("Image upload failed");
            }
            System.out.println("After query execution");
        } else{
            System.out.println("Failed to rename file");
        }
        
        
    }
}
