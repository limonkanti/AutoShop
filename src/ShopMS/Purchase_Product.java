package ShopMS;

import dao.DatabaseHelper;
import dao.PurchaseProductDao;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.Date;
import javax.swing.*;

/*This is main frame.User can perform his work by using this frame*/
public class Purchase_Product extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JTextField tname, price, pdate, qty, s_product;
    JLabel qty_label, pname, price_label, date;
    JLabel line;
    JButton purchase, anp, view_product, btn_back, sale_product, view_sale, product_price, exit;

    JTextField delettextfield;
    JComboBox cmbProduct = new JComboBox();
    Connection con;
    Statement st = null;
    ResultSet rs;

    Purchase_Product()////conntructor which is used for calling other method/function
    {

        super("Purchase Products");
        panel.setBackground(Color.pink);
        panel.setLayout(null);
        add(panel);
        add_top_label();
        setSize(810, 450);
        setVisible(true);
        setLocation(200, 80);
        setResizable(false);
        label_textfield();
        button();
    }

    void add_top_label()//method of adding label top of the panel
    {
        line = new JLabel("Entry Purchases Products In The System");
        line.setForeground(Color.BLACK);
        line.setBounds(280, 70, 800, 30);
        panel.add(line);
    }

    void label_textfield()////method of adding label and textfield with panel...
    {

        pname = new JLabel("Choose Product   : ");
        pname.setForeground(Color.black);
        pname.setBounds(100, 150, 170, 40);
        setFont(new Font("verdana", 3, 14));
        cmbProduct.setBounds(220, 150, 170, 40);
        panel.add(pname);
        panel.add(cmbProduct);
        /// retrieve data from database using combobox........................ .................
        Connection con = null;
        CallableStatement csmt = null;
        ResultSet rs = null;
        try {
            con = DatabaseHelper.getConnection();
            csmt = con.prepareCall("{CALL listProduct()}");
            csmt.execute();
            rs = csmt.getResultSet();
            while (rs.next()) {

                String prolist = rs.getString(1);
                cmbProduct.addItem(prolist);

            }

        } catch (Exception e) {
            System.out.println("Eroor is:" + e.toString());
        }

        ///////////////////////////
        price_label = new JLabel("Purchase Price :");
        price_label.setForeground(Color.black);
        price_label.setBounds(410, 150, 170, 40);
        price = new JTextField(15);
        price.setBounds(530, 150, 170, 40);
        panel.add(price_label);
        panel.add(price);

        Date date1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String student_id = sdf.format(date1);

        date = new JLabel("Purchase Date      :");
        date.setForeground(Color.black);
        date.setBounds(100, 250, 170, 40);
        pdate = new JTextField(student_id);
        pdate.setBounds(220, 250, 170, 40);
        panel.add(date);
        panel.add(pdate);

        qty_label = new JLabel("Quantity               :");
        qty_label.setForeground(Color.black);
        qty_label.setBounds(410, 250, 170, 40);
        qty = new JTextField(15);
        qty.setBounds(530, 250, 170, 40);
        panel.add(qty_label);
        panel.add(qty);

    }

    void button()//////function or method of adding button with panel...
    {
        //up button

        ///down button
        purchase = new JButton("Purchase Product");
        purchase.setForeground(Color.LIGHT_GRAY);
        purchase.setBackground(Color.darkGray);
        purchase.addActionListener(this);
        purchase.setBounds(150, 350, 180, 40);
        panel.add(purchase);

        btn_back = new JButton("Back");
        btn_back.setForeground(Color.lightGray);
        btn_back.setBackground(Color.darkGray);
        btn_back.addActionListener(this);
        btn_back.setBounds(470, 350, 180, 40);
        panel.add(btn_back);

    }

    /*public static void main(String args[]) {
        Purchase_Product ob = new Purchase_Product();//calling constructor
    }*/

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == purchase) {
            if ((price.getText().equals("")) && (qty.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Please Enter Correct Information");
            } else {
                PurchaseProductDao.savePurchase(cmbProduct.getSelectedItem().toString(), price.getText(), pdate.getText(), qty.getText());
                JOptionPane.showMessageDialog(null, "Product Purchased Successfully");
            }

        }

        if (e.getSource() == btn_back) {
            new Main();
            dispose();
        }

    }

}
