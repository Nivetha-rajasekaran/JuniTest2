package com.example.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionUtil {

    public static Connection getConnection(){

        try {
           return DriverManager.getConnection("jdbc:mysql://localhost:3306/appdb","root","Nivi@13"); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
