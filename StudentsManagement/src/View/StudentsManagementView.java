package View;

import Controller.StudentsManagementListener;
import Model.City;
import Model.Students;
import Model.StudentsManagementModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class StudentsManagementView extends JFrame {
    public static Vector listDataFiltre = new Vector();
    public JTextPane bestStudents;
    public StudentsManagementModel model;
    public boolean checkSelectedRowDelete = false;
    public  JScrollPane jScrollPane;
    public JPanel panelMain;
    private JFileChooser fc = new JFileChooser();

    public JMenuBar menuBar1;
    public JMenu fileMenu;
    public JMenu aboutMenu;
    public JTabbedPane tablePanel;
    public JTextField idTextField;
    public JTextField nameTextFiel;
    public JComboBox comboBoxCityTyping;
    public JTextField dateTextFiel;
    public JTextField totalTextFiel;
    public JTextField mathTextFiel;
    public JTextField physicalTextFiel;
    public JTextField chemistryTextFiel;
    public JButton okButton;
    public JButton deleteButton;
    public JButton editButton;
    public JButton insertButton;
    public JButton cancelButton;
    public JComboBox cityComboBoxSelect;
    public JTextField idFiltre;
    public JButton filtreButton;
    public  JTable table;
    public JTable tableData;
    public JPanel tableControl;
    public JRadioButton mRadioButton;
    public JRadioButton fRadioButton;
    public ButtonGroup sexChoice;
    public JTable tableStudents;
    private JTabbedPane tablePanelFiltre;
    private JTable tableFiltre;
    private JScrollPane jScrollPaneFiltre;
    private JPanel jPanelFiltre;
    private JPanel jPanelFirstFiltre;
    private JButton sortButton;
    private JMenu dataBase;
    private JButton updateToDBButton;
    public JScrollPane jScrollPaneMain;
    public static Vector listData = new Vector();

    public StudentsManagementView (){
        this.model = new StudentsManagementModel();
        this.sexChoice = new ButtonGroup();
        this.sexChoice.add(this.mRadioButton);
        this.sexChoice.add(this.fRadioButton);
        try{
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            ActionListener actionListener = new StudentsManagementListener(this);
            this.setContentPane(this.panelMain);
            this.createTable(this);
            //-----------AddActionListener--------------
            this.cancelButton.addActionListener(actionListener);
            this.cancelButton.setIcon(new ImageIcon());
            this.okButton.addActionListener(actionListener);
            this.insertButton.addActionListener(actionListener);
            this.filtreButton.addActionListener(actionListener);
            this.editButton.addActionListener(actionListener);
            this.deleteButton.addActionListener(actionListener);
            this.updateToDBButton.addActionListener(actionListener);
            this.sortButton.addActionListener(actionListener);
            //--------------JMenuItem---------------------
            JMenuItem jMenuItemEdit = new JMenuItem("Update Data");
            jMenuItemEdit.addActionListener(actionListener);
            jMenuItemEdit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("update_icon.png"))));
            JMenuItem jMenuItemInsert = new JMenuItem("Insert Data");
            jMenuItemInsert.addActionListener(actionListener);
            jMenuItemInsert.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("insert_icon.png"))));
            JMenuItem jMenuItemGetData = new JMenuItem("Get Data");
            jMenuItemGetData.addActionListener(actionListener);
            jMenuItemGetData.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("data_icon.png"))));
            JMenuItem jMenuItemDeleteAllData = new JMenuItem("Delete All Data");
            jMenuItemDeleteAllData.addActionListener(actionListener);
            jMenuItemDeleteAllData.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("delete_icon.png"))));
            JMenuItem jMenuItemCheckConnecting = new JMenuItem("Check Connection");
            jMenuItemCheckConnecting.addActionListener(actionListener);
            jMenuItemCheckConnecting.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("loading.png"))));
            JMenuItem jMenuItemOpen = new JMenuItem("Open");
            jMenuItemOpen.addActionListener(actionListener);
            jMenuItemOpen.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("open_icon.png"))));
            JMenuItem jMenuItemSave = new JMenuItem("Save");
            jMenuItemSave.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("save_icon.png"))));
            jMenuItemSave.addActionListener(actionListener);
            JMenuItem jMenuItemExit = new JMenuItem("Exit");
            jMenuItemExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("exit_icon.png"))));
            jMenuItemExit.addActionListener(actionListener);

            JMenuItem jMenuItemAboutMe = new JMenuItem("About Me");
            jMenuItemAboutMe.addActionListener(actionListener);
            jMenuItemAboutMe.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(MainView.class.getResource("aboutme_icon.png"))));
            //------------DatabaseMenu------------
            this.dataBase.add(jMenuItemInsert);
            this.dataBase.addSeparator();
            this.dataBase.add(jMenuItemCheckConnecting);
            this.dataBase.addSeparator();
            this.dataBase.add(jMenuItemGetData);
            this.dataBase.addSeparator();
            this.dataBase.add(jMenuItemEdit);
            this.dataBase.addSeparator();
            this.dataBase.add(jMenuItemDeleteAllData);
            //------------FileMenu----------------
            this.fileMenu.add(jMenuItemOpen);
            this.fileMenu.add(jMenuItemSave);
            this.fileMenu.addSeparator();
            this.fileMenu.add(jMenuItemExit);
            //------------OpenMenu------------------
            this.aboutMenu.add(jMenuItemAboutMe);
            //-----Combo Box City --------------------------
            ArrayList<City> listCity = City.getListCity();
            this.cityComboBoxSelect.addItem("");
            this.comboBoxCityTyping.addItem("");
            for (City city : listCity){
                this.cityComboBoxSelect.addItem(city.getCityName());
                this.comboBoxCityTyping.addItem(city.getCityName());
            }
            //------------------Button Group Sex-------------------
            //-------------------
            //----------------------------------------------------------
            this.setTitle("Students Management");
            this.setSize(1000,800);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
            if (ConnectDataBase.thisPermission==0){
                JOptionPane.showMessageDialog(this,"Bạn đã truy cập với quyền truy cập nhân viên");
            } else {
                JOptionPane.showMessageDialog(this,"Bạn đã truy cập với quyền quản trị");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteForm(){
        idTextField.setText("");
        nameTextFiel.setText("");
        dateTextFiel.setText("");
        mathTextFiel.setText("");
        physicalTextFiel.setText("");
        chemistryTextFiel.setText("");
        comboBoxCityTyping.setSelectedIndex(-1);
        sexChoice.clearSelection();

    }
    public void createTable(StudentsManagementView view){
        Vector header = new Vector();
        header.add("ID");
        header.add("Name");
        header.add("City");
        header.add("Date");
        header.add("Sex");
        header.add("Math");
        header.add("Physics");
        header.add("Chemistry");
        header.add("Total");
        Vector data = new Vector();

        Vector row1 = new Vector();

        table = new JTable(data,header);
        tableFiltre = new JTable(data, header);
        tableData = new JTable(data,header);

        tablePanel.remove(tableControl);
        tablePanelFiltre.remove(jPanelFiltre);

        jScrollPane = new JScrollPane(table);
        jScrollPaneFiltre = new JScrollPane(tableFiltre);

        tablePanel.add("List Students", jScrollPane);
        tablePanelFiltre.add("List Filtre", jScrollPaneFiltre);
    }
    public void addStudent(Students students, StudentsManagementView view){
        this.model.insert(students);
        Vector header = new Vector();
        header.add("ID");
        header.add("Name");
        header.add("City");
        header.add("Date");
        header.add("Sex");
        header.add("Math");
        header.add("Physics");
        header.add("Chemistry");
        header.add("Total");
        Vector newRow = new Vector();
        Vector data = new Vector();
        int count=0;
        int row = 0;
        Vector cloneListData = new Vector();
        cloneListData =(Vector) listData.clone();
        while(!cloneListData.isEmpty()){
            data.add(cloneListData.get(count));
            cloneListData.removeElementAt(count);
        }
        String check = students.getId()+"";
        cloneListData =(Vector) listData.clone();
        row = 0;
        int countCheck = 0;
        for (Object x: cloneListData){
            Vector clone = new Vector();
            clone = (Vector) x;
            if (clone.elementAt(row).equals(check)){
                countCheck++;
                break;
            }
            row++;
        }
        if (countCheck==0) {
            newRow.add(students.getId() + "");
            newRow.add(students.getName());
            newRow.add(students.getCity().getCityName());
            newRow.add(students.getDate());
            String sexual = "";
            if (students.getSex()) {
                sexual = "Male";
            } else sexual = "Female";
            newRow.add(sexual);
            newRow.add(students.getMathPoint() + "");
            newRow.add(students.getPhysicsPoint() + "");
            newRow.add(students.getChemistryPoint() + "");
            newRow.add(students.getMathPoint()+students.getPhysicsPoint()+students.getChemistryPoint()+"");
            students.setTotal(students.getMathPoint()+students.getPhysicsPoint()+students.getChemistryPoint());
            data.add(newRow);
            listData.add(newRow);

            tablePanel.remove(jScrollPane);

            table = new JTable(listData, header);

            jScrollPane = new JScrollPane(table);

            tablePanel.add("List Students", jScrollPane);
        } else {
            JOptionPane.showMessageDialog(view,
                    "ID thí sinh đã tồn tại trong list data",
                    "Eror 404",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    public void updateStudent(Students students){
        int row = table.getSelectedRow();
        int count = 0;
                Vector clone = new Vector();
                clone.add(students.getId()+"");
                clone.add(students.getName());
                clone.add(students.getCity().getCityName());
                clone.add(students.getDate());
                String sexual ="";
                if (students.getSex()){
                    sexual = "Male";
                } else sexual="Female";
                clone.add(sexual);
                clone.add(students.getMathPoint()+"");
                clone.add(students.getPhysicsPoint()+"");
                clone.add(students.getChemistryPoint()+"");
                clone.add(students.getMathPoint()+students.getPhysicsPoint()+students.getChemistryPoint()+"");
                listData.remove(row);
                listData.add(row,clone);

                Vector header = new Vector();
                header.add("ID");
                header.add("Name");
                header.add("City");
                header.add("Date");
                header.add("Sex");
                header.add("Math");
                header.add("Physics");
                header.add("Chemistry");
                header.add("Total");

        table = new JTable(listData,header);

        tablePanel.remove(jScrollPane);

        jScrollPane = new JScrollPane(table);

        tablePanel.add("List Students", jScrollPane);
    }
    public void showStudentInfomation(StudentsManagementView view) {
        int row = table.getSelectedRow();
        if (row!=-1) {
            String id = table.getValueAt(row, 0) + "";
            String name = table.getValueAt(row, 1) + "";
            City city = City.getCityByName(table.getValueAt(row, 2) + "");
            String birthday = table.getValueAt(row, 3) + "";
            String choiceSex = table.getValueAt(row, 4) + "";
            if (choiceSex.equals("Male")) {
                mRadioButton.setSelected(true);
                fRadioButton.setSelected(false);
            } else {
                fRadioButton.setSelected(true);
                mRadioButton.setSelected(false);
            }
            double mathPoint = Double.valueOf(table.getValueAt(row, 5) + "");
            double physicsPoint = Double.valueOf(table.getValueAt(row, 6) + "");
            double chemistryPoint = Double.valueOf(table.getValueAt(row, 7) + "");

            view.idTextField.setText(id);
            view.nameTextFiel.setText(name);
            view.comboBoxCityTyping.setSelectedItem(city.getCityName());
            view.dateTextFiel.setText(birthday);
            view.mathTextFiel.setText(mathPoint + "");
            view.physicalTextFiel.setText(physicsPoint + "");
            view.chemistryTextFiel.setText(chemistryPoint + "");
        } else {
                    JOptionPane.showMessageDialog(view,
                    "Hãy chọn học sinh muốn chỉnh sửa",
                    "Error Not Founded",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteRowData(StudentsManagementView view) {
        int row = table.getSelectedRow();
        int count =0;
        if (row!=-1) {
            int choice = JOptionPane.showConfirmDialog(view,
                    "Bạn có chắc chắn muốn xóa dữ liệu thí sinh này chứ?",
                    "An Inane Question",
                    JOptionPane.YES_NO_OPTION);
            if (choice==JOptionPane.YES_OPTION) {
                listData.remove(row);
                Vector header = new Vector();
                header.add("ID");
                header.add("Name");
                header.add("City");
                header.add("Date");
                header.add("Sex");
                header.add("Math");
                header.add("Physics");
                header.add("Chemistry");
                header.add("Total");
                table = new JTable(listData, header);

                tablePanel.remove(jScrollPane);

                jScrollPane = new JScrollPane(table);

                tablePanel.add("List Students", jScrollPane);
            }
        } else {
            JOptionPane.showMessageDialog(view,
                    "Vui lòng chọn thí sinh bạn muốn xóa",
                    "Eror 404",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void undoSelected() {
        Vector header = new Vector();
        header.add("ID");
        header.add("Name");
        header.add("City");
        header.add("Date");
        header.add("Sex");
        header.add("Math");
        header.add("Physics");
        header.add("Chemistry");
        header.add("Total");

        table = new JTable(listData, header);

        tablePanel.remove(jScrollPane);

        jScrollPane = new JScrollPane(table);

        tablePanel.add("List Students", jScrollPane);
    }

    public void searchingInformation() {
        String cityCode = cityComboBoxSelect.getSelectedIndex()+"";
        City citySelected = City.getCityNameById(cityCode);
        if (citySelected.getCityName()!=null && idFiltre.getText().equals("")) {
            String cityNameSelected = citySelected.getCityName();
            Vector cloneListData = new Vector();
            cloneListData = (Vector) listData.clone();
            int row = 0;
            listDataFiltre.clear();
            int check = 0;
            while(!cloneListData.isEmpty()){
                Vector clone = new Vector();
                clone = (Vector) cloneListData.get(row);
                if (clone.elementAt(2).equals(cityNameSelected)){
                    listDataFiltre.add(clone);
                    check++;
                }
                cloneListData.remove(row);
            }
            Vector header = new Vector();
            header.add("ID");
            header.add("Name");
            header.add("City");
            header.add("Date");
            header.add("Sex");
            header.add("Math");
            header.add("Physics");
            header.add("Chemistry");
            header.add("Total");
            Vector data = new Vector();

            if (check!=0 ){
                tableFiltre = new JTable(listDataFiltre,header);
            } else {
                tableFiltre = new JTable(data,header);
            }

            tablePanelFiltre.remove(jScrollPaneFiltre);

            jScrollPaneFiltre = new JScrollPane(tableFiltre);

            tablePanelFiltre.add("List Filtre", jScrollPaneFiltre);

        } else {
            if (idFiltre.getText()!=""  && citySelected.getCityName()==null){
                Vector cloneListData = new Vector();
                listDataFiltre.clear();
                cloneListData = (Vector) listData.clone();
                String idSearching = idFiltre.getText();
                int row = 0;
                int check =0;
                while(!cloneListData.isEmpty()){
                    Vector clone  = new Vector();
                    clone = (Vector) cloneListData.get(row);
                    if (clone.elementAt(0).equals(idSearching+"")){
                        listDataFiltre.add(clone);
                        check++;
                    }
                    cloneListData.remove(row);
                }
                Vector header = new Vector();
                header.add("ID");
                header.add("Name");
                header.add("City");
                header.add("Date");
                header.add("Sex");
                header.add("Math");
                header.add("Physics");
                header.add("Chemistry");
                header.add("Total");
                Vector data = new Vector();

                if (check!=0 ){
                    tableFiltre = new JTable(listDataFiltre,header);
                } else {
                    tableFiltre = new JTable(data,header);
                }

                tablePanelFiltre.remove(jScrollPaneFiltre);

                jScrollPaneFiltre = new JScrollPane(tableFiltre);

                tablePanelFiltre.add("List Filtre", jScrollPaneFiltre);

            } else {
                if (idFiltre.getText()!="" && citySelected.getCityName() != null){
                    Vector cloneListData = new Vector();
                    cloneListData = (Vector) listData.clone();
                    String idSearching = idFiltre.getText();
                    int row = 0;
                    int check =0;
                    listDataFiltre.clear();
                    while(!cloneListData.isEmpty()){
                        Vector clone  = new Vector();
                        clone = (Vector) cloneListData.get(row);
                        if (clone.elementAt(0).equals(idSearching+"")&&clone.elementAt(2).equals(citySelected.getCityName())){
                            listDataFiltre.add(clone);
                            check++;
                        }
                        cloneListData.remove(row);
                    }
                    Vector header = new Vector();
                    header.add("ID");
                    header.add("Name");
                    header.add("City");
                    header.add("Date");
                    header.add("Sex");
                    header.add("Math");
                    header.add("Physics");
                    header.add("Chemistry");
                    header.add("Total");
                    Vector data = new Vector();

                    if (check!=0 ){
                        tableFiltre = new JTable(listDataFiltre,header);
                    } else {
                        tableFiltre = new JTable(data,header);
                    }

                    tablePanelFiltre.remove(jScrollPaneFiltre);

                    jScrollPaneFiltre = new JScrollPane(tableFiltre);

                    tablePanelFiltre.add("List Filtre", jScrollPaneFiltre);

                } else {
                    Vector header = new Vector();
                    header.add("ID");
                    header.add("Name");
                    header.add("City");
                    header.add("Date");
                    header.add("Sex");
                    header.add("Math");
                    header.add("Physics");
                    header.add("Chemistry");
                    header.add("Total");
                    Vector data = new Vector();

                    tableFiltre = new JTable(data,header);

                    tablePanelFiltre.remove(jScrollPaneFiltre);

                    jScrollPaneFiltre = new JScrollPane(tableFiltre);

                    tablePanelFiltre.add("List Filter", jScrollPaneFiltre);
                }
            }
        }
    }

    public void showAboutProgramming(StudentsManagementView view) {
        JOptionPane.showMessageDialog(view,
                "Made by: Hải Code Dạo",
                "Information",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void saveFile(StudentsManagementView view) throws IOException {
        int select = fc.showSaveDialog(view);
        Vector header = new Vector();
        header.add("ID");
        header.add("Name");
        header.add("City");
        header.add("Date");
        header.add("Sex");
        header.add("Math");
        header.add("Physics");
        header.add("Chemistry");
        header.add("Total");
        tableFiltre = new JTable(null,header);

        tablePanelFiltre.remove(jScrollPaneFiltre);

        jScrollPaneFiltre = new JScrollPane(tableFiltre);

        tablePanelFiltre.add("List Filter", jScrollPaneFiltre);
        try {
            if (select == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                OutputStream os = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(os);
                oos.writeObject(listData);
                listData.clear();
                table = new JTable(null,header);

                tablePanel.remove(jScrollPane);

                jScrollPane = new JScrollPane(table);

                tablePanel.add("List Students",jScrollPane);
                oos.flush();
                oos.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public void openFile(StudentsManagementView view) throws IOException, ClassNotFoundException {
        int select = fc.showOpenDialog(view);
        Students students = null;
            if (select == JFileChooser.APPROVE_OPTION) {
                try {
                    File file = fc.getSelectedFile();
                    FileInputStream f = new FileInputStream(file.getAbsolutePath());
                    ObjectInputStream inStream = new ObjectInputStream(f);
                    listData =(Vector) inStream.readObject();
                    System.out.println(listData);
                    inStream.close();
                    Vector header = new Vector();
                    header.add("ID");
                    header.add("Name");
                    header.add("City");
                    header.add("Date");
                    header.add("Sex");
                    header.add("Math");
                    header.add("Physics");
                    header.add("Chemistry");
                    header.add("Total");
                    tableFiltre = new JTable(null,header);

                    tablePanelFiltre.remove(jScrollPaneFiltre);

                    jScrollPaneFiltre = new JScrollPane(tableFiltre);

                    tablePanelFiltre.add("List Filter", jScrollPaneFiltre);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }

    public void refreshTable(StudentsManagementView studentsManagementView) {
        Vector header = new Vector();
        header.add("ID");
        header.add("Name");
        header.add("City");
        header.add("Date");
        header.add("Sex");
        header.add("Math");
        header.add("Physics");
        header.add("Chemistry");
        header.add("Total");
         Vector data = new Vector();

         table = new JTable((Vector) listData,header);

         tablePanel.remove(jScrollPane);

         jScrollPane = new JScrollPane(table);

         tablePanel.add("List Students",jScrollPane);
    }
    public void sortStudentsData(StudentsManagementView view){
        Vector header = new Vector();
        header.add("ID");
        header.add("Name");
        header.add("City");
        header.add("Date");
        header.add("Sex");
        header.add("Math");
        header.add("Physics");
        header.add("Chemistry");
        header.add("Total");
        Vector cloneListData = new Vector();
        cloneListData =(Vector) listData.clone();
        int row =0;
        listDataFiltre.clear();
        ArrayList<Double> totalPoint = new ArrayList<Double>();
        int length = listData.size();
        for (Object obj : listData){
            Vector clone = new Vector();
            clone = (Vector) obj;
            double total = Double.valueOf(clone.elementAt(8).toString());
            totalPoint.add(total);
        }
        for (int i=0;i<totalPoint.size();i++){
            for (int j=i;j<totalPoint.size();j++){
                if (totalPoint.get(i) >totalPoint.get(j)){
                    double temp = totalPoint.get(i);
                    totalPoint.set(i,totalPoint.get(j));
                    totalPoint.set(j,temp);
                }
            }
        }
        Queue<Double> queue = new PriorityQueue<Double>();
        for (int i=0;i<totalPoint.size();i++){
            queue.add(totalPoint.get(i));
        }
        listDataFiltre.clear();
        while(!queue.isEmpty()) {
            for (Object obj : listData) {
                double check =0;
                Vector clone = new Vector();
                clone = (Vector) obj;
                double total = Double.valueOf(clone.elementAt(8).toString());
                if (queue.isEmpty()) break;
                else {
                    check = queue.peek();
                }
                if (total == check) {
                    listDataFiltre.add(obj);
                    queue.poll();
                }
            }
        }



        tableFiltre = new JTable(listDataFiltre, header);

        tablePanelFiltre.remove(jScrollPaneFiltre);

        jScrollPaneFiltre = new JScrollPane(tableFiltre);

        tablePanelFiltre.add("List Data Filter", jScrollPaneFiltre);
    }
    public static Vector getListData(){
        return listData;
    }
    public static void setListData(Vector cloneData){
        listData.clear();
        for (Object each : cloneData){
            listData.add((Vector) each);
        }
    }
    public void showToApplication(){
        Vector header = new Vector();
        header.add("ID");
        header.add("Name");
        header.add("City");
        header.add("Date");
        header.add("Sex");
        header.add("Math");
        header.add("Physics");
        header.add("Chemistry");
        header.add("Total");
        Vector newRow = new Vector();
        Vector data = new Vector();
        int count=0;
        int row = 0;

        tablePanel.remove(jScrollPane);
        System.out.println(listData);
        table = new JTable(listData, header);

        jScrollPane = new JScrollPane(table);

        tablePanel.add("List Students", jScrollPane);
    }
    public static void addToListData(Vector index){
        listData.add((Vector) index);
    }
    public static Vector listDataFilter(){
        return listDataFiltre;
    }
    public void removeDataTable(){
        Vector header = new Vector();
        header.add("ID");
        header.add("Name");
        header.add("City");
        header.add("Date");
        header.add("Sex");
        header.add("Math");
        header.add("Physics");
        header.add("Chemistry");
        header.add("Total");
        tableFiltre = new JTable(null,header);

        tablePanelFiltre.remove(jScrollPaneFiltre);

        jScrollPaneFiltre = new JScrollPane(tableFiltre);

        tablePanelFiltre.add("List Students",jScrollPaneFiltre);

        listData.clear();
        table = new JTable(null,header);

        tablePanel.remove(jScrollPane);

        jScrollPane = new JScrollPane(table);

        tablePanel.add("List Students",jScrollPane);
    }
}
