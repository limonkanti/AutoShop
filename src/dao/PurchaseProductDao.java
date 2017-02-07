
package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PurchaseProductDao 
{
      public static boolean savePurchase(String pname,String price,String date,String qty)////method of saving purchase product
   {
       Connection con=null;
       CallableStatement csmt=null;
       boolean t=true;
       try{
           con=DatabaseHelper.getConnection();
           csmt=con.prepareCall("{CALL save_purchase(getProductId(?),?,?,?)}");
           
           csmt.setString(1, pname);
           csmt.setString(2, price);
           csmt.setString(3, date);
           csmt.setString(4, qty);
           
           t=csmt.execute();
         
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       finally{
           try {
               con.close();
               csmt.close();
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       }
       return t;
   }
    
}
