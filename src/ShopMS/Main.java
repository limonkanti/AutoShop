package ShopMS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//Main working interface
public class Main extends JFrame implements ActionListener {

    String colorlayout_ = "#607D8B";
    String colorlabel_ = "#263238";
    String colorpane_ = "#B0BEC5";
    JLabel l_welcome = new JLabel("Controll Shop Automatically");

    JLabel l_add_new_product = new JLabel("Add New Product");
    JButton btn_add_new_product = new JButton("", new ImageIcon("image/New.png"));

    JLabel l_purchase_product = new JLabel("Purchase Products");
    JButton btn_purchase_product = new JButton("", new ImageIcon("image/Purchase.png"));

    JLabel l_view_products = new JLabel("View Products");
    JButton btn_view_products = new JButton("", new ImageIcon("image/View.png"));

    JLabel l_product_price = new JLabel("Get Products Price");
    JButton btn_product_price = new JButton("", new ImageIcon("image/Price.png"));

    JLabel l_sale_product = new JLabel("Sale Products");
    JButton btn_sale_product = new JButton("", new ImageIcon("image/Sale2.png"));

    JLabel l_daily_sale = new JLabel("Get Daily Sales");
    JButton btn_daily_sale = new JButton("", new ImageIcon("image/ViewSale.png"));

    JLabel l_discount_sale = new JLabel("Discount on Sale");
    JButton btn_discount_sale = new JButton("", new ImageIcon("image/Discount.png"));

    JDesktopPane pane = new JDesktopPane();
    ImageIcon ic = new ImageIcon();
    Font menufnt = new Font("Times New Roman", Font.PLAIN, 33);
    Font lfnt = new Font("Times New Roman", Font.PLAIN, 23);
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
//constructor

    Main() {
        super("Main");
        Container conn = this.getContentPane();
        conn.setLayout(new BorderLayout());
        conn.setBackground(Color.decode(colorlayout_));

        l_welcome.setHorizontalAlignment(SwingConstants.CENTER);
        l_welcome.setPreferredSize(new Dimension(80, 100));
        conn.add(l_welcome, BorderLayout.NORTH);
        setLocationRelativeTo(null);
        conn.add(pane, BorderLayout.CENTER);
        pane.setBackground(Color.decode(colorpane_));
        pane.setLayout(null);
        add(pane);
        setSize(950, 700);
        setVisible(true);
        setLocation(200, 10);
        setResizable(false);
        label();
        button();

    }
//method for adding label

    void label() {

        l_welcome.setFont(menufnt);
        l_welcome.setForeground(Color.decode(colorlabel_));

        l_add_new_product.setBounds(100, 40, 170, 20);
        l_add_new_product.setFont(lfnt);
        l_add_new_product.setForeground(Color.BLACK);
        pane.add(l_add_new_product);

        l_purchase_product.setBounds(390, 40, 175, 20);
        l_purchase_product.setFont(lfnt);
        l_purchase_product.setForeground(Color.BLACK);
        pane.add(l_purchase_product);

        l_view_products.setBounds(650, 40, 170, 20);
        l_view_products.setFont(lfnt);
        l_view_products.setForeground(Color.black);
        pane.add(l_view_products);

        l_product_price.setBounds(100, 200, 200, 60);
        l_product_price.setFont(lfnt);
        l_product_price.setForeground(Color.BLACK);
        pane.add(l_product_price);

        l_sale_product.setBounds(400, 200, 200, 60);
        l_sale_product.setFont(lfnt);
        l_sale_product.setForeground(Color.BLACK);
        pane.add(l_sale_product);

        l_daily_sale.setBounds(650, 200, 200, 60);
        l_daily_sale.setFont(lfnt);
        l_daily_sale.setForeground(Color.black);
        pane.add(l_daily_sale);

        l_discount_sale.setBounds(100, 350, 200, 60);
        l_discount_sale.setFont(lfnt);
        l_discount_sale.setForeground(Color.BLACK);
        pane.add(l_discount_sale);

    }
//method for adding button

    void button() {

        btn_add_new_product.setBounds(120, 70, 120, 60);
        btn_add_new_product.addActionListener(this);
        pane.add(btn_add_new_product);

        btn_purchase_product.setBounds(410, 70, 120, 60);
        btn_purchase_product.addActionListener(this);
        pane.add(btn_purchase_product);

        btn_view_products.setBounds(665, 70, 120, 60);
        btn_view_products.addActionListener(this);
        pane.add(btn_view_products);

        btn_product_price.setBounds(120, 260, 120, 60);
        btn_product_price.addActionListener(this);
        pane.add(btn_product_price);

        btn_sale_product.setBounds(410, 260, 120, 55);
        btn_sale_product.addActionListener(this);
        pane.add(btn_sale_product);

        btn_daily_sale.setBounds(665, 260, 120, 60);
        btn_daily_sale.addActionListener(this);
        pane.add(btn_daily_sale);

        btn_discount_sale.setBounds(120, 410, 120, 60);
        btn_discount_sale.addActionListener(this);
        pane.add(btn_discount_sale);

    }

    public static void main(String[] args) {
        Main ob = new Main();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_add_new_product) {
            Add_New_Product ob2 = new Add_New_Product();
            dispose();
        }
        if (e.getSource() == btn_purchase_product) {
            Purchase_Product ob2 = new Purchase_Product();
            dispose();
        }
        if (e.getSource() == btn_view_products) {
            View_Product2 ob2 = new View_Product2();

        }
        if (e.getSource() == btn_product_price) {
            view_product_price ob2 = new view_product_price();

        }
        if (e.getSource() == btn_sale_product) {
            SaleProduct ob2 = new SaleProduct();

        }

        if (e.getSource() == btn_daily_sale) {
            Search_Sale2 ob = new Search_Sale2();

        }

        if (e.getSource() == btn_discount_sale) {
            DiscountCalculation ob = new DiscountCalculation();
            dispose();

        }

    }

}
