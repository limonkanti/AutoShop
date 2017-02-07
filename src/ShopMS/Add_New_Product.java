package ShopMS;

import dao.AddNewProductDao;
import dao.DatabaseHelper;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;

public class Add_New_Product extends JFrame implements ActionListener 
{

   JPanel panel = new JPanel();
   private JTextField  product_tf;
   private JLabel  pname_label;
  
   private JButton save_btn, back_btn;//declaring object for button

    Connection con;
    Statement st = null;
    ResultSet rs;
    
    Add_New_Product() {//conntructor which is used for calling method/function
        super("Add New Product");
        panel.setBackground(Color.pink);
        panel.setLayout(null);
        add(panel);
       
        setSize(600, 400);
        setVisible(true);
        setLocation(180, 60);
        setResizable(false);
        label_textfield();//
        button();
     

    }

    void label_textfield() {//function or method of adding label and textfield with panel...
       
        pname_label = new JLabel("Enter Product Name :");
        product_tf=new JTextField(15);
        pname_label.setForeground(Color.black);
        pname_label.setBounds(100, 100, 120, 40);
        product_tf.setBounds(240, 100, 150, 40);
        panel.add(pname_label);
        panel.add(product_tf);

    }

    void button() {////function or method of adding button with panel...
        save_btn = new JButton("Save Product");
        save_btn.setForeground(Color.LIGHT_GRAY);
        save_btn.setBackground(Color.darkGray);
        save_btn.addActionListener(this);
        save_btn.setBounds(80, 250, 180, 40);
        panel.add(save_btn);

        back_btn = new JButton("Back");
        back_btn.setForeground(Color.lightGray);
        back_btn.setBackground(Color.darkGray);
        back_btn.setBounds(280, 250, 180, 40);
        back_btn.addActionListener(this);
        panel.add(back_btn);

    }
/*  public static void main(String args[])
     {
       Add_New_Product ad=new Add_New_Product();
     }*/

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save_btn) {
            
            AddNewProductDao.savePname(product_tf.getText());
        }
        if(e.getSource()==back_btn)
        {
            Main ob=new Main();
            dispose();
        }
    }

}
