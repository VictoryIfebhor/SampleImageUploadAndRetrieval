/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author RND SOFTWARE
 */
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        try {
            
            String path = request.getParameter("path");
            System.out.println(System.getProperty("user.dir"));
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(System.getProperty("user.dir") + "\\" + path));
            
            byte[] bytes = new byte[in.available()];
            
            in.read(bytes);
            in.close();

            // Write image contents to response.
            response.getOutputStream().write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
