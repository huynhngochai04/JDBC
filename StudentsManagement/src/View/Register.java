package View;

import RegisterListener.RegisterListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Register extends JFrame{
    public static boolean backHit = false;
    public JTextField user;
    public JPasswordField password;
    public JPasswordField confirmPassword;
    public JTextField gmail;
    public JPanel registerPanel;
    public JButton registerButton;
    public JButton backButton;

    public Register(){
        this.setContentPane(this.registerPanel);
        this.setTitle("Register");
        ActionListener actionListener = new RegisterListener(this);
        this.backButton.addActionListener(actionListener);
        this.registerButton.addActionListener(actionListener);
        this.setSize(400,250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        while(backHit==false||backButton.isSelected()==false){
            System.out.printf("%s","");
            if (backHit==true||backButton.isSelected()==true){
                backHit=false;
                backButton.setSelected(false);
                this.setVisible(false);
                System.out.println("ICU");
                new LoggedIn();
                break;
            }
        }
    }
    public void setBackHit(boolean index){
        this.backHit = index;
    }

}
