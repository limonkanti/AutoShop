

package ShopMS;

import dao.DatabaseHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.*;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class view_product_price {
    
     view_product_price()//getting product and product per price using JTable
     {
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CallableStatement csmt=null;
         
        Vector data = new Vector();
        JPanel panel = new JPanel();
        JTable table;
        try {
            con =DatabaseHelper.getConnection();
            csmt=con.prepareCall("{call view_product_price()}");
            csmt.execute();
            rs=csmt.getResultSet();
            ResultSetMetaData rsmt = rs.getMetaData();
            int c = rsmt.getColumnCount();
            Vector column = new Vector(c);
            for (int i = 1; i <= c; i++) {
                column.add(rsmt.getColumnName(i));
            }
            Vector row = new Vector();
            while (rs.next()) {
                row = new Vector(c);
                for (int i = 1; i <= c; i++)
                {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
            Vector columnNames = new Vector();
            columnNames.addElement("Product Name");
            columnNames.addElement("Price");
            table = new JTable(data, columnNames);
            table.setForeground(Color.PINK);
            JFrame frame = new JFrame("Average Product Price");
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            JScrollPane pane = new JScrollPane(table);
            panel.setLayout(new BorderLayout());
            panel.add(pane, BorderLayout.CENTER);
            frame.setContentPane(panel);
            frame.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
         
     }

  /* public static void main(String args[])
    {

        view_product_price ob=new view_product_price();
        

    }*/

}
