package ShopMS;

import dao.DatabaseHelper;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SaleProduct implements ActionListener{
    /*Saleing product using jtable*/

    JFrame frame = new JFrame("Sale Product");
    JTable table = new JTable();
    JTextField textProductName = new JTextField();
    JTextField textProductPrice = new JTextField();
    JTextField textTotalPrice = new JTextField();
    JTextField textSaleDate;
    JTextField textQuantity = new JTextField();
    JButton add_btn = new JButton("Add");
    JButton delete_btn = new JButton("Delete");
    JButton update_btn = new JButton("Update");
    JButton confirm_btn = new JButton("Confirm");
    JButton preview_btn = new JButton("Preview");
    JButton total_btn = new JButton("Amount to be Paid");
    JButton back_btn = new JButton("Back");

    JLabel product_name_lbl = new JLabel("Select Product Name  :");
    JLabel product_price_lbl = new JLabel("Enter Product Price  :");
    JLabel product_sale_date_lbl = new JLabel("Product Sale Date  :");
    JLabel product_quantity_lbl = new JLabel("Product Quantity      :");
    JLabel lbl_customer_name = new JLabel("Customer Name      :");
    JTextField tf_customer_name = new JTextField();
    Object[] columns = {"Product Name", "Product Price", "Sale Date", "Quantity"};
    DefaultTableModel model = new DefaultTableModel();
    JComboBox cmbProduct = new JComboBox();
    Object[] row = new Object[4];

    SaleProduct() {

        Date date1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String student_id = sdf.format(date1);
        textSaleDate = new JTextField(student_id);

        model.setColumnIdentifiers(columns);
        table.setModel(model);
        table.setRowHeight(15);

        product_name_lbl.setBounds(20, 220, 150, 40);
        product_price_lbl.setBounds(20, 270, 150, 40);
        product_sale_date_lbl.setBounds(20, 320, 150, 40);
        product_quantity_lbl.setBounds(20, 370, 150, 40);
        lbl_customer_name.setBounds(350, 420, 120, 40);
        
        
        total_btn.setBounds(20, 520, 150, 40);
        total_btn.addActionListener(this);
        
        back_btn.setBounds(20,570, 150, 40);
        back_btn.addActionListener(this);

        cmbProduct.setBounds(180, 220, 150, 40);
        textProductPrice.setBounds(180, 270, 150, 40);
        textSaleDate.setBounds(180, 320, 150, 40);
        textQuantity.setBounds(180, 370, 150, 40);
        tf_customer_name.setBounds(500, 420, 150, 40);
        
        textTotalPrice.setBounds(180, 520, 150, 40);

        add_btn.setBounds(350, 220, 100, 25);
        add_btn.addActionListener(this);
        delete_btn.setBounds(350, 265, 100, 25);
        delete_btn.addActionListener(this);
        update_btn.setBounds(350, 310, 100, 25);
        update_btn.addActionListener(this);
        confirm_btn.setBounds(465, 265, 100, 25);
        confirm_btn.addActionListener(this);

        preview_btn.setBounds(665, 265, 100, 25);
        preview_btn.addActionListener(this);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        frame.setLayout(null);
        frame.add(pane);

        frame.add(product_name_lbl);
        frame.add(product_price_lbl);
        frame.add(product_sale_date_lbl);
        frame.add(product_quantity_lbl);

        frame.add(lbl_customer_name);
        frame.add(tf_customer_name);
        frame.add(total_btn);
       
        frame.add(cmbProduct);

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
        frame.add(textProductPrice);
        frame.add(textTotalPrice);
   
        frame.add(textSaleDate);
        frame.add(textQuantity);
        frame.add(textProductName);

        frame.add(add_btn);
        frame.add(delete_btn);
        frame.add(update_btn);
        frame.add(confirm_btn);
        frame.add(preview_btn);

        frame.setSize(900, 700);
        frame.setVisible(true);

    }

    /*public static void main(String args[]) {
        SaleProduct saleProduct = new SaleProduct();

    }*/

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add_btn) {//Adding Product to the table
            row[0] = cmbProduct.getSelectedItem().toString();
            row[1] = textProductPrice.getText();
            row[2] = textSaleDate.getText();
            row[3] = textQuantity.getText();
            model.addRow(row);

        }
        if (e.getSource() == delete_btn) {//deleting Product from the table
            int i = table.getSelectedRow();
            if (i >= 0) {
                model.removeRow(i);

            } else {
                JOptionPane.showMessageDialog(null, "Please Select Correct Records!!!");
            }

        }
        if (e.getSource() == update_btn) {//update Product of the table
            int i = table.getSelectedRow();
            if (i >= 0) {
                model.setValueAt(cmbProduct.getSelectedItem().toString(), i, 0);

                model.setValueAt(textProductPrice.getText(), i, 1);

                model.setValueAt(textSaleDate.getText(), i, 2);

                model.setValueAt(textQuantity.getText(), i, 3);

            } else {
                JOptionPane.showMessageDialog(null, "Update Error!!!");
            }

        }
        if (e.getSource() == confirm_btn) {//confirm sale Product of the table
            Connection con = null;
            CallableStatement csmt = null;

            try {
                con = DatabaseHelper.getConnection();
                csmt = con.prepareCall("{CALL save_sale(getProductId(?),?,?,?)}");

                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        Object o = model.getValueAt(i, j);

                        csmt.setObject(j + 1, o);

                    }
                    csmt.executeUpdate();

                }
                JOptionPane.showMessageDialog(null, "Product Have Sold Successfully!!!");

            } catch (Exception e1) {
                System.out.println("Error is:\n" + e1);

            }

        }
        if (e.getSource() == total_btn) {//getting total price from table
            int sum, total = 0;

            for (int i = 0; i < model.getRowCount(); i++) {

                int amount = Integer.parseInt(model.getValueAt(i, 1) + "");

                total = amount + total;

            }

            textTotalPrice.setText(String.valueOf(total));

        }
       
        if (e.getSource() == preview_btn) {//getting total price from table
            try {
                MessageFormat header = new MessageFormat("Computer shop \n Chawkbazar Chittagong");
                
                MessageFormat footer = new MessageFormat("Dear"+"\t"+tf_customer_name.getText()+"\t"+"You have to paid"+"\t"+textTotalPrice.getText());
                table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
               

                /*table.print();*/
            } catch (PrinterException pe) {
                System.err.println("Error printing: " + pe.getMessage());
            }

        }
        

    }

}
