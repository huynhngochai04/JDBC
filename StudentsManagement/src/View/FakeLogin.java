package View;

import LoginListener.LoginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Random;

public class FakeLogin extends  JFrame{
    private JPasswordField passwordField;
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
    public FakeLogin(){
        try {
            LoggedIn login = new LoggedIn();
            ActionListener actionListener = new LoginListener(login);
            login.setContentPane(login.loginPanel);
            ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("login_icon.png")));
            login.loginLable.setIcon(image);
            login.loginControlPanel.setBackground(Color.CYAN);
            login.loginPanel.setBackground(Color.CYAN);
            login.buttonThis.setBackground(Color.CYAN);
            login.haicodedao.setBackground(Color.CYAN);
            login.haicodedao.setFont(new Font("Arial", Font.ITALIC,30));
            login.registerButton.addActionListener(actionListener);
            login.loginButton.addActionListener(actionListener);
            //--------------------------------------------
            login.setTitle("Login");
            login.setSize(350,300);
            login.setLocationRelativeTo(null);
            login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            login.setVisible(true);
            while(this.loginButton.isSelected()==false||this.registerButton.isSelected()==false){
                if (checkLogin(user,correctPassword)||hit==true){
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

    public boolean checkLogin(String user, char[] password){
        if (user.equals(userField.getText())){
            int i=0;
            if (password.length!=passwordField.getPassword().length){
                return false;
            }
            else{
                int cnt = 0;
                while(i<password.length){
                    if (password[i] == passwordField.getPassword()[i]) {
                        cnt++;
                    } else {
                        return false;
                    }
                    if (cnt==password.length){
                        return true;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }
    public void setHit(boolean index){
        this.hit = index;
    }
}
