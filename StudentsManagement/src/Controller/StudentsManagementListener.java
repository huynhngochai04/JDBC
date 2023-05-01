package Controller;

import Model.City;
import Model.Date;
import Model.Students;
import View.ConnectDataBase;
import View.StudentsManagementView;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class StudentsManagementListener implements ActionListener{
    public StudentsManagementView studentsManagementView;
    public static int checkSort = 0;
    private Students students;

    public StudentsManagementListener(StudentsManagementView studentsManagementView){
        this.studentsManagementView=studentsManagementView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
        int permission = ConnectDataBase.thisPermission;
        if (button!="Update to DB"&&button!="About Me"&&button!="Exit"&&button!="Open"&&button!="Close"&&button!="Delete"&&button!="Insert Data"&&button!="Check Connection"&&button!="Delete All Data"&&button!="Get Data"&&button!="Update Data"){
            JOptionPane.showMessageDialog(studentsManagementView, "Bạn vừa nhấn vào nút: " + button);
        }
        if (button.equals("Update to DB")){
            if (permission==1) {
                if (checkSort != 0) {
                    this.studentsManagementView.removeDataTable();
                    Vector listData = studentsManagementView.listDataFiltre;
                    try {
                        ConnectDataBase.deleteAllData(studentsManagementView);
                        for (Object obj : listData) {
                            Vector clone = new Vector();
                            clone = (Vector) obj;
                            String id = clone.elementAt(0).toString();
                            String fullName = clone.elementAt(1).toString();
                            String city = clone.elementAt(2).toString();
                            String birthday = clone.elementAt(3).toString();
                            String sex = clone.elementAt(4).toString();
                            Double mathPoint = Double.valueOf(clone.elementAt(5).toString());
                            Double physicsPoint = Double.valueOf(clone.elementAt(6).toString());
                            Double chemistryPoint = Double.valueOf(clone.elementAt(7).toString());
                            Double totalPoint = mathPoint + physicsPoint + chemistryPoint;
                            Connection conn = ConnectDataBase.checkConnection();
                            String insert = "INSERT INTO data (ID, fullName, city, birthday, sex, mathPoint, physicsPoint, chemistryPoint, totalPoint) VALUES (?,?,?,?,?,?,?,?,?)";
                            PreparedStatement insert_query = conn.prepareStatement(insert);
                            insert_query.setString(1, id);
                            insert_query.setString(2, fullName);
                            insert_query.setString(3, city);
                            insert_query.setString(4, birthday);
                            insert_query.setString(5, sex);
                            insert_query.setDouble(6, mathPoint);
                            insert_query.setDouble(7, physicsPoint);
                            insert_query.setDouble(8, chemistryPoint);
                            insert_query.setDouble(9, totalPoint);
                            insert_query.execute();
                        }
                        JOptionPane.showMessageDialog(studentsManagementView, "Bạn đã cập nhật thành công dữ liệu vào database");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(studentsManagementView, "Không có dữ liệu để update vào database");
                }
            } else {
                JOptionPane.showMessageDialog(studentsManagementView,"Bạn không có quyền để truy cập database");
            }
        }
        if (button.equals("Update Data")){
            if (permission!=0) {
                try {
                    ConnectDataBase.updateData(studentsManagementView);
                    this.studentsManagementView.removeDataTable();
                    JOptionPane.showMessageDialog(studentsManagementView, "Đã chỉnh sửa dữ liệu thành công và cập nhật vào database");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(studentsManagementView,"Bạn không có quyền truy cập chức năng này");
            }
        }
        if (button.equals("Get Data")){
            try {
                ConnectDataBase.getAllData(studentsManagementView);
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        if (button.equals("Delete All Data")){
            if (permission!=0) {
                try {
                    this.studentsManagementView.removeDataTable();
                    ConnectDataBase.deleteAllData(studentsManagementView);
                    JOptionPane.showMessageDialog(studentsManagementView, "Đã xóa hết dữ liệu hiện có trong Database");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(studentsManagementView,"Bạn không có quyền truy cập chức năng này");
            }
        }
        if (button.equals("Insert Data")){
            if (permission!=0) {
                try {
                    ConnectDataBase.inserDataToDatabase(studentsManagementView);
                    this.studentsManagementView.removeDataTable();
                    JOptionPane.showMessageDialog(studentsManagementView, "Đã thêm hết data hiện có vào trong database");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                JOptionPane.showMessageDialog(studentsManagementView,"Bạn không có quyền truy cập chức năng này");
            }
        }
        if (button.equals("Check Connection")){
            try {
                ConnectDataBase.checkAccessConnection(studentsManagementView);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (button.equals("Ok")) {
            this.studentsManagementView.deleteForm();
            this.studentsManagementView.model.setChoice(button);
        } else {
            try {
                if (button.equals("Insert")) {
                    String id = studentsManagementView.idTextField.getText();
                    String name = studentsManagementView.nameTextFiel.getText();
                    String cityCode = studentsManagementView.comboBoxCityTyping.getSelectedIndex() + "";
                    City city = City.getCityNameById(cityCode);
                    String birthday = studentsManagementView.dateTextFiel.getText();
                    String date = birthday;
                    boolean sexCheck = true;
                    if (studentsManagementView.mRadioButton.isSelected()) {
                        sexCheck = true;
                    } else sexCheck = false;
                    double mathPoint = Double.valueOf(studentsManagementView.mathTextFiel.getText());
                    double physicsPoint = Double.valueOf(studentsManagementView.physicalTextFiel.getText());
                    ;
                    double chemistryPoint = Double.valueOf(studentsManagementView.chemistryTextFiel.getText());
                    ;
                    double total = mathPoint+physicsPoint+chemistryPoint;
                    if (total==ConnectDataBase.maxPoint){
                        String student = "ID: " + id + " Tên: " + name + " Tỉnh: " + city.getCityName() + " Tổng: " + total;
                        studentsManagementView.bestStudents.setText(studentsManagementView.bestStudents.getText()+'\n'+student);
                    }
                    if (total>ConnectDataBase.maxPoint){
                        studentsManagementView.bestStudents.setText("Sinh viên cao điểm nhất: ");
                        String student = "ID: " + id + " Tên: " + name + " Tỉnh: " + city.getCityName() + " Tổng: " + total;
                        studentsManagementView.bestStudents.setText(studentsManagementView.bestStudents.getText()+'\n'+student);
                        ConnectDataBase.maxPoint = total;
                    }
                    students = new Students(id, name, city, date, sexCheck, mathPoint, physicsPoint, chemistryPoint);
                    studentsManagementView.model.list.add(students);
                    if (this.studentsManagementView.model.getChoice().equals("Edit")) {
                        this.studentsManagementView.updateStudent(students);
                        this.studentsManagementView.deleteForm();
                    } else {
                        this.studentsManagementView.addStudent(students,studentsManagementView);
                    }
                }
                else {
                    if (button.equals("Edit")){
                        this.studentsManagementView.showStudentInfomation(studentsManagementView);
                        this.studentsManagementView.model.setChoice(button);
                    } else {
                        if (button.equals("Delete")){
                                this.studentsManagementView.deleteRowData(studentsManagementView);
                        }
                        else {
                            if (button.equals("Cancel")){
                                this.studentsManagementView.undoSelected();
                            }
                            else {
                                if (button.equals("Filter")){
                                    this.studentsManagementView.searchingInformation();
                                }
                                else {
                                    if (button.equals("Exit")){
                                        System.exit(0);
                                    } else {
                                        if (button.equals("About Me")){
                                            this.studentsManagementView.showAboutProgramming(studentsManagementView);
                                        } else {
                                            if (button.equals("Open")){
                                                this.studentsManagementView.openFile(studentsManagementView);
                                                this.studentsManagementView.refreshTable(studentsManagementView);
                                            }
                                            else {
                                                if (button.equals("Save")){
                                                    this.studentsManagementView.saveFile(studentsManagementView);
                                                    this.studentsManagementView.deleteForm();
                                                } else {
                                                    if (button.equals("Sort")){
                                                        this.studentsManagementView.sortStudentsData(studentsManagementView);
                                                        checkSort++;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    }
                } catch(Exception e2){
                    e2.printStackTrace();
                }
            }
        }
    }
