
package ShopMS;

import dao.DatabaseHelper;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.*;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;


public class Sale_Product extends JFrame implements ActionListener
{
    JPanel panel=new JPanel();
    JTextField tname,price,pdate,qty,s_product;
    JLabel qty_label,pname,price_label,date;
    JLabel line;
    JButton purchase,anp,view_product,reset,sale_product,view_sale,product_price,next,back_product;

    JTextField delettextfield;
    JComboBox cmbProduct=new JComboBox();
    Connection con;
    Statement st = null;//help access to the database
    ResultSet rs;//database er table theke value tule anar jorno use hoi //
    //The SQL statements that read data from a database query return the data in a result set.

  //  Date date;

    Sale_Product()
    {
        super("Sale Product");
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        add(panel);
       
        setSize(860,400);
        setVisible(true);
        setLocation(250,100);
        setResizable(false);
        label_textfield();
        button();
    }
   
    
    void label_textfield()
    {
        //////////////////////////////////////////
        pname=new JLabel("Choose Product   : ");
        pname.setForeground(Color.black);
        pname.setBounds(100, 80, 170, 40);
        setFont(new Font("verdana", 3, 14));
        cmbProduct.setBounds(220, 80, 170, 40);
        panel.add(pname);
        panel.add(cmbProduct);
        /// retrieve data using combobox........................ .................
       Connection con=null;
       CallableStatement csmt=null;
       ResultSet rs=null;
       try{
           con=DatabaseHelper.getConnection();
           csmt=con.prepareCall("{CALL listProduct()}");
           csmt.execute(); 
           rs=csmt.getResultSet();
           while(rs.next())
           {
               
            String prolist=rs.getString(1);
            cmbProduct.addItem(prolist);
           
           }
           
       }
       catch(Exception e)
       {
           System.out.println("Eroor is:"+e.toString());
       }

       
       ///////////////////////////
        price_label=new JLabel("Purchase Price :");
        price_label.setForeground(Color.black);
        price_label.setBounds(410, 80, 170, 40);
        price=new JTextField(15);
        price.setBounds(530, 80, 170, 40);
        panel.add(price_label);
        panel.add(price);



        Date date1=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy");
        String student_id = sdf.format(date1);

        date=new JLabel("Purchase Date      :");
        date.setForeground(Color.black);
        date.setBounds(100, 150, 170, 40);
        pdate=new JTextField(student_id);
        pdate.setBounds(220, 150, 170, 40);
        panel.add(date);
        panel.add(pdate);



        qty_label=new JLabel("Quantity               :");
        qty_label.setForeground(Color.black);
        qty_label.setBounds(410, 150, 170, 40);
        qty=new JTextField(15);
        qty.setBounds(530, 150, 170, 40);
        panel.add(qty_label);
        panel.add(qty);



    }
    void button()
    {
       
      
        

        sale_product=new JButton("Sale Product");
        sale_product.setForeground(Color.lightGray);
        sale_product.setBackground(Color.darkGray);
        sale_product.setBounds(230, 250, 180, 40);
        sale_product.addActionListener(this);
        panel.add(sale_product);


        back_product=new JButton("Back");
        back_product.setForeground(Color.lightGray);
        back_product.setBackground(Color.darkGray);
        back_product.addActionListener(this);
        back_product.setBounds(430, 250, 180, 40);
        panel.add(back_product);

       

        
        /*update=new JButton("Update" ,new ImageIcon("update.png"));
        update.setForeground(Color.white);
        update.addActionListener(this);
        update.setBackground(Color.pink);
        update.setBounds(480, 480, 100, 40);
        panel.add(update);


        
        exitt=new JButton("exit" ,new ImageIcon("exit.png"));
        exitt.setForeground(Color.white);
        exitt.setBackground(Color.pink);
        exitt.setBounds(590, 480, 100, 40);
        exitt.addActionListener(this);
        panel.add(exitt);

        next=new JButton("Search");// ,new ImageIcon("next.Gif"));
        next.addActionListener(this);
        next.setForeground(Color.white);
        next.setBackground(Color.pink);
        next.setBounds(700, 480, 100, 40);
        panel.add(next);*/



    }
  /*  public static void main(String args[])
    {
        Sale_Product ob=new Sale_Product();
    }*/

    public void actionPerformed(ActionEvent e)
    {
        
      
       if(e.getSource()==sale_product)
       {
          if((price.getText().equals(""))&&(qty.getText().equals("")))
          {
              JOptionPane.showMessageDialog(null, "Please Enter Correct Information");
          }
         else
          {
           ProductController.saveSale(cmbProduct.getSelectedItem().toString(), price.getText(), pdate.getText(), qty.getText());
           JOptionPane.showMessageDialog(null, "Product Sale Successfully");
          }
           
       }
       if(e.getSource()==back_product)
       {
           
           Main ob=new Main();
           dispose();
           
       }
       
    }

    

}


