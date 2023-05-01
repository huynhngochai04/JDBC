package View;

import LoginListener.LoginListener;
import Model.Students;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class LoggedIn extends  JFrame{
    public JPasswordField passwordField;
    public JPanel loginPanel;
    public JLabel loginLable;
    public JTextField userField;
    public JPanel loginControlPanel;
    public JPanel comboButton;
    public JButton registerButton;
    public JButton loginButton;
    public JLabel codewalkerPanel;
    public JPanel buttonThis;
    public JLabel haicodedao;
    public boolean hit;
    public String user = "root";
    public char[] correctPassword = {'1','2','3','4','5','6','7', '8','9'};
    public LoggedIn(){
        try {
            ActionListener actionListener = new LoginListener(this);
            this.setContentPane(this.loginPanel);
            ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("login_icon.png")));
            this.loginLable.setIcon(image);
            this.loginControlPanel.setBackground(Color.CYAN);
            this.loginPanel.setBackground(Color.CYAN);
            this.buttonThis.setBackground(Color.CYAN);
            this.haicodedao.setBackground(Color.CYAN);
            this.haicodedao.setFont(new Font("Arial", Font.ITALIC,30));
            this.registerButton.addActionListener(actionListener);
            this.loginButton.addActionListener(actionListener);
            //--------------------------------------------
            this.setTitle("Login");
            this.setSize(350,300);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            String correctPassword = "123456789";
            while(this.loginButton.isSelected()==false||this.registerButton.isSelected()==false){
                if (ConnectDataBase.checkDataAccount(userField.getText(),convertPasswordToString())){
                    break;
                }
                if (hit==true){
                    System.out.println("AJISUHDIJOUASDIJOAIJOWD");
                    hit = false;
                    this.setVisible(false);
                    new Register();
                    break;
                }
                Random random = new Random();
                final float hue = random.nextFloat();
                final float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
                final float luminance = 1.0f; //1.0 for brighter, 0.0 for black
                Color color = Color.getHSBColor(hue, saturation, luminance);
                this.haicodedao.setForeground(color);
                Thread.sleep(300);
                System.out.println(color);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new LoggedIn();
    }
    public void setHit(boolean index){
        this.hit = index;
    }
    public String convertPasswordToString(){
        String result = "";
        for (char x : this.passwordField.getPassword()){
            result += x+"";
        }
        return result;
    }
}
