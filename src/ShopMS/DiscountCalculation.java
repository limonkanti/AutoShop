
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
public class DiscountCalculation extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JTextField tname, price, pdate, qty, s_product;
    JLabel qty_label, price_label,lbl_paid,lbl_total_paid;
    JLabel line;
    JButton btn_calculate,btn_back;

    JTextField delettextfield,tf_paid,tf_paid_tk;
   

    DiscountCalculation()////conntructor which is used for calling other method/function
    {

        super("Discount Calculation");
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
        line = new JLabel("Calculate Discount Automatically");
        line.setForeground(Color.BLACK);
        line.setBounds(280, 20, 800, 30);
        panel.add(line);
    }

    void label_textfield()////method of adding label and textfield with panel...
    {

      

        ///////////////////////////
        price_label = new JLabel("Total Price    :  ");
        price_label.setForeground(Color.black);
        price_label.setBounds(250, 100, 100, 40);
        price = new JTextField(15);
        price.setBounds(350, 100, 170, 40);
        panel.add(price_label);
        panel.add(price);

        qty_label = new JLabel("Percentage     :  ");
        qty_label.setForeground(Color.black);
        qty_label.setBounds(250, 160, 170, 40);
        qty = new JTextField(15);
        qty.setBounds(350, 160, 170, 40);
        panel.add(qty_label);
        panel.add(qty);
        
        lbl_paid = new JLabel("Discount   :  ");
        lbl_paid.setForeground(Color.black);
        lbl_paid.setBounds(250, 220, 170, 40);
        tf_paid = new JTextField(15);
        tf_paid.setBounds(350, 220, 170, 40);
        panel.add(lbl_paid);
        panel.add(tf_paid);
                
        lbl_total_paid = new JLabel("To Be Paid   :  ");
        lbl_total_paid.setForeground(Color.black);
        lbl_total_paid.setBounds(250, 280, 170, 40);
        tf_paid_tk = new JTextField(15);
        tf_paid_tk.setBounds(350, 280, 170, 40);
        panel.add(lbl_total_paid);
        panel.add(tf_paid_tk);

    }

    void button()//////function or method of adding button with panel...
    {
        //up button

        ///down button
        btn_calculate = new JButton("Calculate");
        btn_calculate.setForeground(Color.LIGHT_GRAY);
        btn_calculate.setBackground(Color.darkGray);
        btn_calculate.addActionListener(this);
        btn_calculate.setBounds(150, 350, 180, 40);
        panel.add(btn_calculate);

        btn_back = new JButton("Back");
        btn_back.setForeground(Color.lightGray);
        btn_back.setBackground(Color.darkGray);
        btn_back.addActionListener(this);
        btn_back.setBounds(470, 350, 180, 40);
        panel.add(btn_back);

    }

  /*public static void main(String args[]) {
       DiscountCalculation ob=new DiscountCalculation();
    }*/

    public void actionPerformed(ActionEvent e)
    {

       if(e.getSource()==btn_calculate)
       {
           String s=price.getText();
           String s1=qty.getText();
            
           int a=Integer.parseInt(s);
           int b=Integer.parseInt(s1);
           double c=(a*b)/100;
           String paid=String.valueOf(c);
           tf_paid.setText(paid);
           String s2=price.getText();
           
           double x=Double.parseDouble(s2);
           double y=x-c;
           String total=String.valueOf(y);
           tf_paid_tk.setText(total);
           
           
           
           
           
       }
       if(e.getSource()==btn_back)
       {
           new Main();
           dispose();
       }

    }

}
