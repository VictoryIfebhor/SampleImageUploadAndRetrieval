/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author RND SOFTWARE
 */
@ManagedBean
public class Test {

    public void listenerAction() throws IOException {
        File sourceFile = new File("test.txt");
        if (sourceFile.createNewFile()){
            System.out.println("File was created successfully");
        } else{
            System.out.println("Failed to create file");
        }
        
        String name = "dist2021-04-22T11:59:53.078.txt";
        String path = name.substring(0, name.lastIndexOf(".")).replace(".", "").replace("-", "").replace(":", "") + name.substring(name.lastIndexOf("."), name.length());
        File destFile = new File(path);
        
        System.out.println(sourceFile.getAbsolutePath());
        System.out.println(destFile.getAbsolutePath());
        
        if (sourceFile.renameTo(destFile)) {
            System.out.println("File renamed successfully");
        } else {
            System.out.println("Failed to rename file");
        }
        
        System.out.println(sourceFile.exists());
        System.out.println(destFile.exists());
    }
}
