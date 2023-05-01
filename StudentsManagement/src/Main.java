import View.FakeLogin;
import View.LoggedIn;
import View.Register;
import View.StudentsManagementView;
import Model.*;
import Controller.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            new LoggedIn();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}