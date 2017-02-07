package dao;

import java.sql.*;

public class LoginDao {

    public static boolean login(String name, String password) {
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            con = DatabaseHelper.getConnection();
            ps = con.prepareStatement("select name,password from admin where name=? and password=?");
            ps.setString(1, name);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error is:\t" + e.toString());
            return false;

        }

    }

}
