package RegisterListener;

import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RegisterListener implements ActionListener{
    private Register register;
    public RegisterListener(Register register){
        this.register = register;
    }
    @Override
    public void actionPerformed(ActionEvent e){
        String button = e.getActionCommand();
        boolean backHit = false;
        if (button.equals("Back")){
            register.backHit=true;
        }
        if (button.equals("Register")){
            try {
                String firstPass ="";
                String secondPass ="";

                for (char x:register.password.getPassword()){
                    firstPass += x+"";
                }
                for (char x:register.confirmPassword.getPassword()){
                    secondPass += x+"";
                }
                int cnt = 0;
                if (firstPass.length()==secondPass.length()){
                    int i = 0;
                    cnt = 0;
                    while(i<firstPass.length()){
                        if (firstPass.charAt(i)==secondPass.charAt(i)){
                            cnt ++;
                        }
                        i++;
                    }
                }
                if (cnt!=firstPass.length()||firstPass.length()!=secondPass.length()){
                    JOptionPane.showMessageDialog(register,"Mật khẩu bạn nhập chưa trùng khớp");
                }
                if (cnt==firstPass.length()){
                    insertAccountToDatabase();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void insertAccountToDatabase() throws SQLException{
        Connection conn = ConnectDataBase.checkConnection();
        System.out.println(conn);
        String query = "INSERT INTO account (user,password,email,permission) VALUES (?,?,?,?)";
        PreparedStatement insert_query = conn.prepareStatement(query);
        insert_query.setString(1,register.user.getText());
        String realPassword = "";
        for (char x : register.password.getPassword()){
            realPassword += x + "";
        }
        System.out.println(register.user.getText());
        insert_query.setString(2,realPassword);
        insert_query.setString(3,register.gmail.getText());
        insert_query.setInt(4,0);
        String email = register.gmail.getText();
        if (ConnectDataBase.checkAccount.contains(register.user.getText())){
            JOptionPane.showMessageDialog(register,"Tài khoản đã tồn tại trong hệ thống, xin vui lòng nhập lại");
        }
        if (ConnectDataBase.checkGmail.contains(register.gmail.getText())){
            JOptionPane.showMessageDialog(register,"Gmail đã tồn tại, vui lòng chọn gmail khác");
        }
        if (email.lastIndexOf("@gmail.com")>0&&ConnectDataBase.checkAccount.contains(register.user.getText())==false&&ConnectDataBase.checkGmail.contains(register.gmail.getText())==false) {
            JOptionPane.showMessageDialog(register,"Bạn đã đăng ký tài khoản thành công, xin mời quay trở lại đăng nhập");
            insert_query.execute();
        }
        if (email.lastIndexOf("@gmail.com")<=0){
            JOptionPane.showMessageDialog(register,"Gmail bạn nhập không chính xác, vui lòng nhập lại");
        }
    }
}
