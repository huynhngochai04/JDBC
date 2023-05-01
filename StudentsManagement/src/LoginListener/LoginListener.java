package LoginListener;

import View.ConnectDataBase;
import View.LoggedIn;
import View.Register;
import View.StudentsManagementView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginListener implements ActionListener {
    private LoggedIn login;

    public LoginListener(LoggedIn login) {
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        String user = "root";
        String correctPassword = "123456789";
        if (button.equals("Register")) {
            login.setHit(true);
        }
        if (button.equals("Login")) {
            boolean check = false;
            try {
                check = ConnectDataBase.checkDataAccount(login.userField.getText(),login.convertPasswordToString());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(check);
            if (check == true) {
                JOptionPane.showMessageDialog(login, "Đã đăng nhập thành công");
                login.setVisible(false);

                StudentsManagementView view = new StudentsManagementView();
            } else {
                JOptionPane.showMessageDialog(login, "Sai thông tin đăng nhập, vui lòng thử lại");
            }
        }
    }
}