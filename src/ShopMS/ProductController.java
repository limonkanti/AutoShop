
package ShopMS;

import dao.DatabaseHelper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ProductController
{
    
  
 ////method of saving sale product
   public static boolean saveSale(String pname,String price,String date,String qty)
   {
       Connection con=null;
       CallableStatement csmt=null;
       boolean t=true;
       try{
           con=DatabaseHelper.getConnection();
           csmt=con.prepareCall("{CALL save_sale(getProductId(?),?,?,?)}");
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
   ////method of getting average product price
   public static void avgPrice()
   {
       
       Connection con=null;
       CallableStatement csmt=null;
       ResultSet rs=null;
       try{
           con=DatabaseHelper.getConnection();
           csmt=con.prepareCall("{CALL avg_price()}");
           csmt.execute(); 
           rs=csmt.getResultSet();
           List pList=new ArrayList();
           while(rs.next())
           {
               pList.add(rs.getString(1));
           }
          
       }
       catch(Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
   }
   //method of viewing product
   public static void viewProduct()
   {
       
       Connection con=null;
       CallableStatement csmt=null;
       ResultSet rs=null;
       try{
           con=DatabaseHelper.getConnection();
           csmt=con.prepareCall("{CALL view_product()}");
           csmt.execute(); 
           rs=csmt.getResultSet();
           List pList=new ArrayList();
           while(rs.next())
           {
               pList.add(rs.getString(1));
           }
          
       }
       catch(Exception e)
       {
           JOptionPane.showMessageDialog(null, e);
       }
   }
   
 
   ////method of saving sale product
   public static boolean saveSale1()
   {
       Connection con=null;
       CallableStatement csmt=null;
       boolean t=true;
       try{
           con=DatabaseHelper.getConnection();
           csmt=con.prepareCall("{CALL save_sale(getProductId(?),?,?,?)}");
           
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
