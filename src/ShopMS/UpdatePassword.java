package ShopMS;

import dao.UpdatePasswordDao;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
/*User can change his/her password by using this frame*/
public class UpdatePassword extends JFrame implements ActionListener {

    JPanel panel = new JPanel();
    JLabel lbl_name = new JLabel("New Password :");
    JLabel lbl_password = new JLabel("Old Password :");
    JPasswordField txt_new_pass = new JPasswordField(15);
    JPasswordField txt_old_password = new JPasswordField(15);
    JButton ub = new JButton("Update");
    JButton bb = new JButton("Back");
    Connection con;
    Statement st;
    ResultSet rs;

    UpdatePassword() {
        super("Update Password");
        panel.setLayout(null);
        add(panel);
        setSize(350, 280);
        setVisible(true);
        setLocation(480, 180);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Add_Component();

    }

    void Add_Component() {//method of adding component with panel
        lbl_name.setBounds(30, 30, 100, 40);
        txt_new_pass.setBounds(140, 30, 150, 40);

        txt_new_pass.setForeground(Color.GREEN);
        panel.add(lbl_name);
        panel.add(txt_new_pass);

        lbl_password.setBounds(30, 100, 100, 40);
        txt_old_password.setBounds(140, 100, 150, 40);
        panel.add(lbl_password);
        panel.add(txt_old_password);

        ub.setBounds(20, 180, 120, 40);
        ub.addActionListener(this);
        ub.setToolTipText("Update");
        ub.setMnemonic(KeyEvent.VK_U);
        panel.add(ub);

        bb.setBounds(160, 180, 130, 40);
        bb.setToolTipText("Back");
        bb.setMnemonic(KeyEvent.VK_B);
        bb.addActionListener(this);
        panel.add(bb);

    }

    /*public static void main(String[] args) {

     UpdatePassword ob = new UpdatePassword();

     }*/
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == ub) {//update password action

            try {
                String newpass = txt_new_pass.getText();
                String oldpass = txt_old_password.getText();

                boolean status = UpdatePasswordDao.updatePass(newpass, oldpass);
                if (status == true) {
                    JOptionPane.showMessageDialog(null, "Welcome!!! Your password has changed successfully.");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct username or password", "Warning!!!", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e1) {

                JOptionPane.showMessageDialog(null, "Please enter correct username or password", "Warning!!!", JOptionPane.ERROR_MESSAGE);
            }

        }
        if (e.getSource() == bb) {
            Login ob = new Login();
            dispose();
        }

    }
}
