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

public class View_Product2 {
    //view products
     View_Product2()
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
            csmt=con.prepareCall("{call view_product()}");
            csmt.execute();
            rs=csmt.getResultSet();
            //st = con.createStatement();
           // String sql = "SELECT product.pname,product.qty FROM product";
          //  rs = st.executeQuery(sql);
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
            columnNames.addElement("Names of the Product");
            columnNames.addElement("Quantities of the Product");
            table = new JTable(data, columnNames);
           
            table.setForeground(Color.pink);
            JFrame frame = new JFrame("List of Products");
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
           // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           // table = new JTable(data, column);.............
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

        View_Product2 ob=new View_Product2();
        

    }*/

}
