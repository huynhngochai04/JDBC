package View;

import Controller.StudentsManagementListener;
import Model.City;
import Model.Students;
import com.mysql.cj.jdbc.Driver;

import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.*;

public class ConnectDataBase{
    public static Connection conn = null;
    public static Double maxPoint = -10e9;
    public static Set<String> dataBestStudents = new HashSet<String>();
    public static int thisPermission = 0;
    public static int countBestStudents = 0;
    public static int firstTime = 0;
    public static Double maxOld = -10e9 ;
    public static Set<String> checkAccount = new HashSet<String>();
    public static Set<String> checkGmail = new HashSet<String>();
    public static ArrayList<Map<String,Map<String,Integer>>> listDataAccount = new ArrayList<>();
    public static Connection checkConnection() throws SQLException{
        try{
            DriverManager.registerDriver(new Driver());
            String url = "jdbc:mySQL://localhost:4306/dataStudents";
            String user = "root";
            String password = "123456789";
            conn = DriverManager.getConnection(url,user,password);
            return conn;
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void checkAccessConnection(StudentsManagementView view) throws SQLException{
        Connection conn = checkConnection();
        if (!conn.isClosed()){
            JOptionPane.showMessageDialog(view,"Bạn đã kết nối thành công tới Database");
        } else {
            JOptionPane.showMessageDialog(view,"Bạn không thể kết nối tới Database, vui lòng thử lại sau");
        }
    }
    public static String insertQuery(){
        String insert_query = "INSERT INTO data (id,fullName,city,birthday,sex,mathPoint,physicsPoint,chemistryPoint,totalPoint)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" ;
        return insert_query;
    }
    public static void inserDataToDatabase(StudentsManagementView view) throws SQLException{
        Vector listData = StudentsManagementView.getListData();
        checkAccount.add("root");
        checkGmail.add("mr.haihuynhngoc@gmail.com");
        Vector clone = new Vector();
        clone = (Vector) listData.clone();
        conn = checkConnection();
        PreparedStatement query = conn.prepareStatement(insertQuery());
        for (Object it : clone){
            Vector first = (Vector) it;
            String checkSex = "";
            if (first.elementAt(4).equals(false)){
                checkSex = "F";
            } else checkSex ="M";
            query.setString(1,first.elementAt(0).toString());
            query.setString(2,first.elementAt(1).toString());
            query.setString(3,first.elementAt(2).toString());
            query.setString(4,first.elementAt(3).toString());
            query.setString(5,first.elementAt(4).toString());
            query.setDouble(6,Double.valueOf(first.elementAt(5).toString()));
            query.setDouble(7,Double.valueOf(first.elementAt(6).toString()));
            query.setDouble(8,Double.valueOf(first.elementAt(7).toString()));
            query.setDouble(9,Double.valueOf(first.elementAt(8).toString()));
            query.execute();
            try {
                Connection conn = checkConnection();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void deleteAllData(StudentsManagementView view) throws SQLException{
        String delete_query = "TRUNCATE TABLE data";
        Connection conn = checkConnection();
        Statement delete = conn.createStatement();
        delete.executeUpdate(delete_query);
        System.out.println("Đã xóa hết dữ liệu hiện có trong database");
    }
    public static void getAllData(StudentsManagementView view) throws SQLException{
        Connection conn = checkConnection();
        String data_query = "SELECT * FROM data";
        Statement query = conn.createStatement();
        ResultSet temp = query.executeQuery(data_query);
        Vector res = view.getListData();
        res.clear();
        ArrayList<Double> listTotalPoint = new ArrayList<Double>();
        while(temp.next()){
            String id = temp.getString("ID");
            String fullName = temp.getString("fullName");
            String city = temp.getString("city");
            String birthday = temp.getString("birthday");
            String sex = temp.getString("sex");
            Double mathPoint = temp.getDouble("mathPoint");
            Double physicsPoint = temp.getDouble("physicsPoint");
            Double chemistryPoint = temp.getDouble("chemistryPoint");
            Double totalPoint = temp.getDouble("totalPoint");
            String checkSex = "Male";
            listTotalPoint.add(totalPoint);
            if (sex.equals("F")){
                checkSex = "Female";
            }
            Vector newRow = new Vector();
            newRow.add(id);
            newRow.add(fullName);
            newRow.add(city);
            newRow.add(birthday);
            newRow.add(checkSex);
            newRow.add(mathPoint);
            newRow.add(physicsPoint);
            newRow.add(chemistryPoint);
            newRow.add(totalPoint);
            view.addToListData(newRow);
        }
        for (int i=0;i<listTotalPoint.size();i++){
            if (listTotalPoint.get(i)>maxOld){
                maxOld = listTotalPoint.get(i);
            }
        }
        maxPoint = maxOld;
        Connection c = checkConnection();
        String data = "SELECT * FROM data";
        Statement query_data = c.createStatement();
        ResultSet tmp = query.executeQuery(data_query);
            while (tmp.next()) {
                Double totalPoint = tmp.getDouble("totalPoint");
                if (totalPoint.equals(maxOld)) {
                    String student = "ID: " + tmp.getString(1) + " Tên: " + tmp.getString(2) + " Tỉnh: " + tmp.getString(3) + " Tổng: " + totalPoint;
                    view.bestStudents.setText("Sinh viên cao điểm nhất: " + "\n" + student);
                }
            }
        view.showToApplication();
    }
    public static void updateData(StudentsManagementView view) throws SQLException{
        deleteAllData(view);
        inserDataToDatabase(view);
    }
    public static boolean checkDataAccount(String checkUser, String checkPassword) throws SQLException{
        Connection conn = checkConnection();
        Statement getData = conn.createStatement();
        String query = "SELECT * FROM account";
        ResultSet tmp = getData.executeQuery(query);
        while(tmp.next()){
            String user = tmp.getString(1);
            String password = tmp.getString(2);
            checkAccount.add(user);
            checkGmail.add(tmp.getString(3));
            if (checkUser.equals(user)){
                if (checkPassword.equals(password)){
                    thisPermission = tmp.getInt(4);
                    return true;
                }
            }
        }
        return false;
    }
    public static Map<String,Map<String,Integer>> convertData(String user, String password, int permission){
        Map<String,Integer> last = new HashMap<>();
        Map<String,Map<String,Integer>> result = new HashMap<>();
        last.put(password,permission);
        result.put(user,last);
        return result;
    }
}
