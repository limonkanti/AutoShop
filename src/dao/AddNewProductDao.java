
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;


public class AddNewProductDao {
     public static boolean savePname(String pname)//method of saving new product
   {
       Connection con=null;
       CallableStatement csmt=null;
       boolean t=true;
       try{
           con=DatabaseHelper.getConnection();
           csmt=con.prepareCall("{CALL saveProduct(?)}");
           csmt.setString(1, pname);
           t=csmt.execute();
         
       }
       catch(Exception e)
       {
           System.out.println("Error is:\t"+e.toString());
       }
       return t;
   }
    
}

