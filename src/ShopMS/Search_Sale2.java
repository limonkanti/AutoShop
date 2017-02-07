package ShopMS;

import dao.DatabaseHelper;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class Search_Sale2 implements ActionListener {

    JFrame frame1, frame2;
    JTextField textbox;
    JLabel label;
    JButton button, button2;
    JPanel panel, panel1;
    JTable table;
    String[] columnNames = {"Product", "Price", "Quantity"};
    Connection con;
    Statement st;
    ResultSet rs;
    java.util.Date date = new java.util.Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");////
    String string_date = sdf.format(date);

    Search_Sale2() {

        frame1 = new JFrame("View Sold On Specific Date");
        frame1.setBackground(Color.pink);
        frame1.setForeground(Color.pink);
        frame1.setLayout(null);
        textbox = new JTextField(string_date);
        textbox.setBounds(160, 40, 150, 40);
        label = new JLabel("Enter Date :");//
        label.setBounds(40, 40, 115, 40);//
        button = new JButton("Search");//
        button.setBounds(75, 130, 130, 30);//
        button.addActionListener(this);//
        button2 = new JButton("Back");//
        button2.setBounds(210, 130, 130, 30);//
        button2.addActionListener(this);
        frame1.add(textbox);
        frame1.add(label);
        frame1.add(button);
        frame1.setVisible(true);
        frame1.setSize(380, 230);
        frame1.setLocation(480, 180);
    }

    /* public static void main(String args[]) {
     Search_Sale2 ob = new Search_Sale2();

     }*/
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button) {
            showTableData();

        }

    }
//Searching selling products
    public void showTableData() {

        frame2 = new JFrame("Sale Information");
        frame2.setBackground(Color.pink);
        frame2.setForeground(Color.pink);
        frame2.setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable();
        table.setBackground(Color.pink);
        table.setModel(model);

        JScrollPane scroll = new JScrollPane(table);

        String textvalue = textbox.getText();
        String pname = "";
        String price = "";
        String qty = "";

        try {

            con = DatabaseHelper.getConnection();
            String sq3 = "SELECT  product.pname,sale.price,sale.qty from test_24.sale inner join test_24.product on sale.p_id=product.p_id where sale.sdate='" + textvalue + "'";
            PreparedStatement ps = con.prepareStatement(sq3);//
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                pname = rs.getString("pname");
                price = rs.getString("price");
                qty = rs.getString("qty");
                model.addRow(new Object[]{pname, price, qty});
                i++;
            }
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            if (i > 0) {
                System.out.println(i + " Records Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());
        }
        frame2.add(scroll);//
        frame2.setVisible(true);//
        frame2.setSize(400, 400);//
        frame2.setLocation(290, 80);
    }

}
