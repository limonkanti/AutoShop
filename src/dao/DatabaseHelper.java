package dao;

import java.sql.*;

public class DatabaseHelper {

    public static Connection getConnection()//connection with database using singleton design pattern.
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");//mysql

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_24", "root", "12345");
            return con;

        } catch (Exception e) {
            System.out.println("Connection Error.");
            return null;

        }
    }

}
