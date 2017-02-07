
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*UpdatePasswordDao//business logic here*/
public class UpdatePasswordDao {
       
   public static boolean updatePass(String newpass,String oldpass)
   {
       Connection con=null;
       ResultSet rs=null;
       PreparedStatement ps=null;
       
       try
       {
           con=DatabaseHelper.getConnection();
           ps=con.prepareStatement("update test_24.admin set password=? where password=?"); 
           ps.setString(1, newpass);
           ps.setString(2, oldpass);
           ps.execute();
           return true;
           
           
           
       }
       catch(Exception e)
       {
           System.out.println("Error is:\t"+e.toString());
            return false;
           
       }
       
   }
   
    
}
   
   