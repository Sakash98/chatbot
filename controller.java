package com.example.ctaaoop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.util.Locale;


public class controller {
    private final ObservableList<String> branch =
            FXCollections.observableArrayList("Computer Science And Engineering","Information Science And Engineering","Electronics And Communications Engineering","Electrical And Electronic Engineering","Mechanical Engineering");
    private final ObservableList<String> acYear =
            FXCollections.observableArrayList("2018-19","2019-20","2020-21","2021-22","2022-23");
    @FXML
    private TextField tname;
    @FXML
    private TextField tusn;
    @FXML
    private ComboBox<String> cobranch;
    @FXML
    private ComboBox<String> coacYear;
    @FXML
    public void initialize(){
        coacYear.setItems(acYear);
        cobranch.setItems(branch);
    }
    @FXML
    private TextField tia1;
    @FXML
    private TextField tia2;
    @FXML
    private TextField tia3;
    @FXML
    private TextField tcta;
    @FXML
    private Label cieRes;
    @FXML
    private Label bo2Res;
    @FXML
    private TableView<student> tblView;
    @FXML
    public TableColumn<student,String> tblusn;
    @FXML
    public TableColumn<student,String> tblname;
    @FXML
    public TableColumn<student,String> tblbranch;
    @FXML
    public TableColumn<student,String> tblacyear;
    @FXML
    public TableColumn<student,Integer> tblia1;
    @FXML
    public TableColumn<student,Integer> tblia2;
    @FXML
    public TableColumn<student,Integer> tblia3;
    @FXML
    public TableColumn<student,Integer> tblcta;
    @FXML
    public TableColumn<student,Integer> tblcie;
    String URL = "jdbc:mysql://localhost/ctaaoop";
    public void loadData(){
        tblusn.setCellValueFactory(new PropertyValueFactory<>("usn"));
        tblname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblbranch.setCellValueFactory(new PropertyValueFactory<>("branch"));
        tblacyear.setCellValueFactory(new PropertyValueFactory<>("acyear"));
        tblia1.setCellValueFactory(new PropertyValueFactory<>("ia1"));
        tblia2.setCellValueFactory(new PropertyValueFactory<>("ia2"));
        tblia3.setCellValueFactory(new PropertyValueFactory<>("ia3"));
        tblcta.setCellValueFactory(new PropertyValueFactory<>("cta"));
        tblcie.setCellValueFactory(new PropertyValueFactory<>("cie"));
    }
    @FXML
    public void onSubmitBtnClk(){
        String name = tname.getText();
        if(name.isEmpty()){
            Alert a0 = new Alert(Alert.AlertType.WARNING);
            a0.setHeaderText("NAME Cannot Be Empty");
            a0.showAndWait();
        }
        else if (!name.matches("[a-zA-Z ]+")) {
            Alert a1 = new Alert(Alert.AlertType.WARNING);
            a1.setHeaderText("Name cannot contain special character and digits");
            a1.showAndWait();
            tname.setText(null);
        }

        String usn = tusn.getText().toUpperCase(Locale.ROOT);
        if(usn.isEmpty()){
            Alert a2 = new Alert(Alert.AlertType.WARNING);
            a2.setHeaderText("USN Cannot Be Empty");
            a2.showAndWait();
        }
        else if(!usn.matches("2SD(.*)") || usn.length()!=10 ){
            Alert a9 = new Alert(Alert.AlertType.WARNING);
            a9.setHeaderText("Invalid USN");
            a9.showAndWait();
            tusn.setText(null);
        }

        String branch = cobranch.getValue();
        if(cobranch.getValue()==null){
            Alert a3 = new Alert(Alert.AlertType.WARNING);
            a3.setHeaderText("Please select the Branch");
            a3.showAndWait();
        }

        String academicYear = coacYear.getValue();
        if(coacYear.getValue()==null){
            Alert a4 = new Alert(Alert.AlertType.WARNING);
            a4.setHeaderText("Please select the academic year");
            a4.showAndWait();
        }

        int ia1 = Integer.parseInt(tia1.getText());
        if(ia1 > 20 || ia1<0 ){
            Alert a5 = new Alert(Alert.AlertType.WARNING);
            a5.setHeaderText("Invalid IA marks !");
            a5.setContentText("Marks should in between 0 & 20 ");
            a5.showAndWait();
            tia1.setText(null);
            ia1=0;
        }

        int ia2 = Integer.parseInt(tia2.getText());
        if(ia2 > 20 || ia2<0 ){
            Alert a6 = new Alert(Alert.AlertType.WARNING);
            a6.setHeaderText("Invalid IA marks !");
            a6.setContentText("Marks should in between 0 & 20 ");
            a6.showAndWait();
            tia2.setText(null);
            ia2=0;
        }

        int ia3 = Integer.parseInt(tia3.getText());
        if(ia3 > 20 || ia3<0 ){
            Alert a7 = new Alert(Alert.AlertType.WARNING);
            a7.setHeaderText("Invalid IA marks !");
            a7.setContentText("Marks should in between 0 & 20 ");
            a7.showAndWait();
            tia3.setText(null);
            ia3=0;
        }

        int cta = Integer.parseInt(tcta.getText());
        if(cta > 10 || cta<0 ){
            Alert a8 = new Alert(Alert.AlertType.WARNING);
            a8.setHeaderText("Invalid CTA marks !");
            a8.setContentText("Marks should in between 0 & 20 ");
            a8.showAndWait();
            tcta.setText(null);
            cta=0;
        }

        int val1 = Math.max(ia3, (Math.max(ia1, ia2)));
        int val2 = ia1 + ia2 + ia3 - val1 - Math.min(ia3, (Math.min(ia1, ia2)));
        bo2Res.setText(String.valueOf(val1 + val2));
        int cie = val1 + val2 + cta;
        cieRes.setText(String.valueOf(cie));

        String query = "INSERT INTO student "+"(usn,stuname,branch,ac_year,ia1,ia2,ia3,cta,cie)"+"values(?,?,?,?,?,?,?,?,?)";
        try{
            Connection connection = DriverManager.getConnection(URL,"root","Mattu@2001");
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1,usn);
            statement.setString(2,name);
            statement.setString(3,branch);
            statement.setString(4,academicYear);
            statement.setInt(5,ia1);
            statement.setInt(6,ia2);
            statement.setInt(7,ia3);
            statement.setInt(8,cta);
            statement.setInt(9,cie);

            statement.executeUpdate();
            statement.close();
            connection.close();
        }catch (SQLException e){e.printStackTrace();}
        ObservableList<student> data = FXCollections.observableArrayList(new student(usn,name,branch,academicYear,ia1,ia2,ia3,cta,cie));
        loadData();
        tblView.setItems(data);
    }
    @FXML
    public void onResetBtnClk() {
        tname.setText(null);
        tusn.setText(null);
        cobranch.setValue(null);
        coacYear.setValue(null);
        tia1.setText(null);
        tia2.setText(null);
        tia3.setText(null);
        tcta.setText(null);
        cieRes.setText(null);
        bo2Res.setText(null);
        tblView.setItems(null);
    }

    @FXML
    public void onSearchBtnClk(){
        String usn = tusn.getText().toUpperCase(Locale.ROOT);
        if(usn.isEmpty()){
            Alert a20 = new Alert(Alert.AlertType.WARNING);
            a20.setHeaderText("ENTER THE USN TO BE SEARCHED");
            a20.showAndWait();
        }
        else if(!usn.matches("2SD(.*)") || usn.length()!=10 ){
            Alert a21 = new Alert(Alert.AlertType.WARNING);
            a21.setHeaderText("Invalid USN");
            a21.showAndWait();
            tusn.setText(null);
        }
        ObservableList<student> data = FXCollections.observableArrayList();
        try {
            ResultSet resultSet;
            String query = "select * from student where usn="+"'"+usn+"'";
            Connection connection = DriverManager.getConnection(URL, "root", "Mattu@2001");
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data.add(new student(resultSet.getString("usn"), resultSet.getString("stuname"),
                        resultSet.getString("branch"), resultSet.getString("ac_year"),
                        resultSet.getInt("ia1"), resultSet.getInt("ia2"), resultSet.getInt("ia3"),
                        resultSet.getInt("cta"), resultSet.getInt("cie")));
            }
        }catch (SQLException e){e.printStackTrace();}
        loadData();
        tblView.setItems(data);
    }

    @FXML
    public void onDisplayBtnClk(){
        ObservableList<student> data = FXCollections.observableArrayList();
        try {
            ResultSet resultSet;
            String query = "select * from student ";
            Connection connection = DriverManager.getConnection(URL, "root", "Mattu@2001");
            PreparedStatement statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data.add(new student(resultSet.getString("usn"), resultSet.getString("stuname"),
                        resultSet.getString("branch"), resultSet.getString("ac_year"),
                        resultSet.getInt("ia1"), resultSet.getInt("ia2"), resultSet.getInt("ia3"),
                        resultSet.getInt("cta"), resultSet.getInt("cie")));
            }
        }catch (SQLException e){e.printStackTrace();}
        loadData();
        tblView.setItems(data);
    }

    @FXML
    public void onDeleteBtnClk(){
        String usn = tusn.getText().toUpperCase(Locale.ROOT);
        if(usn.isEmpty()){
            Alert a20 = new Alert(Alert.AlertType.WARNING);
            a20.setHeaderText("ENTER THE USN TO BE SEARCHED");
            a20.showAndWait();
        }
        else if(!usn.matches("2SD(.*)") || usn.length()!=10 ){
            Alert a21 = new Alert(Alert.AlertType.WARNING);
            a21.setHeaderText("Invalid USN");
            a21.showAndWait();
            tusn.setText(null);
        }
        ObservableList<student> data = FXCollections.observableArrayList();
        try {
            ResultSet resultSet;
            String query = "select * from student where usn="+"'"+usn+"'";
            String query1 = "delete from student where usn="+"'"+usn+"'";
            Connection connection = DriverManager.getConnection(URL, "root", "Mattu@2001");
            PreparedStatement statement = connection.prepareStatement(query);
            Statement statement1 = connection.createStatement();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data.add(new student(resultSet.getString("usn"), resultSet.getString("stuname"),
                        resultSet.getString("branch"), resultSet.getString("ac_year"),
                        resultSet.getInt("ia1"), resultSet.getInt("ia2"), resultSet.getInt("ia3"),
                        resultSet.getInt("cta"), resultSet.getInt("cie")));
            }
            statement1.executeUpdate(query1);
        }catch (SQLException e){e.printStackTrace();}
        loadData();
        tblView.setItems(data);
    }

    @FXML
    public void onUpdateButtonClk(){
        String usn = tusn.getText().toUpperCase(Locale.ROOT);
        if(usn.isEmpty()){
            Alert a22 = new Alert(Alert.AlertType.WARNING);
            a22.setHeaderText("ENTER THE USN TO BE UPDATED");
            a22.showAndWait();
        }
        else if(!usn.matches("2SD(.*)") || usn.length()!=10 ){
            Alert a23 = new Alert(Alert.AlertType.WARNING);
            a23.setHeaderText("Invalid USN");
            a23.showAndWait();
            tusn.setText(null);
        }
        else{
            Alert a24 = new Alert(Alert.AlertType.INFORMATION);
            a24.setHeaderText("YOU CAN UPDATE ONLY IA AND CTA MARKS");
            a24.showAndWait();
        }

        int ia1 = Integer.parseInt(tia1.getText());
        if(ia1 > 20 || ia1<0 ){
            Alert a51 = new Alert(Alert.AlertType.WARNING);
            a51.setHeaderText("Invalid IA marks !");
            a51.setContentText("Marks should in between 0 & 20 ");
            a51.showAndWait();
            tia1.setText(null);
            ia1=0;
        }

        int ia2 = Integer.parseInt(tia2.getText());
        if(ia2 > 20 || ia2<0 ){
            Alert a61 = new Alert(Alert.AlertType.WARNING);
            a61.setHeaderText("Invalid IA marks !");
            a61.setContentText("Marks should in between 0 & 20 ");
            a61.showAndWait();
            tia2.setText(null);
            ia2=0;
        }

        int ia3 = Integer.parseInt(tia3.getText());
        if(ia3 > 20 || ia3<0 ){
            Alert a71 = new Alert(Alert.AlertType.WARNING);
            a71.setHeaderText("Invalid IA marks !");
            a71.setContentText("Marks should in between 0 & 20 ");
            a71.showAndWait();
            tia3.setText(null);
            ia3=0;
        }

        int cta = Integer.parseInt(tcta.getText());
        if(cta > 10 || cta<0 ){
            Alert a81 = new Alert(Alert.AlertType.WARNING);
            a81.setHeaderText("Invalid CTA marks !");
            a81.setContentText("Marks should in between 0 & 20 ");
            a81.showAndWait();
            tcta.setText(null);
            cta=0;
        }

        int val1 = Math.max(ia3, (Math.max(ia1, ia2)));
        int val2 = ia1 + ia2 + ia3 - val1 - Math.min(ia3, (Math.min(ia1, ia2)));
        bo2Res.setText(String.valueOf(val1 + val2));
        int cie = val1 + val2 + cta;
        cieRes.setText(String.valueOf(cie));

        ObservableList<student> data = FXCollections.observableArrayList();
        try {
            ResultSet resultSet;
            String query = "select * from student where usn="+"'"+usn+"'";
            String query1 = "update student set ia1="+ia1+",ia2="+ia2+",ia3="+ia3+",cta="+cta+",cie="+cie+" where usn='"+usn+"'";
            Connection connection = DriverManager.getConnection(URL, "root", "Mattu@2001");
            PreparedStatement statement = connection.prepareStatement(query);
            Statement statement1 = connection.createStatement();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                data.add(new student(resultSet.getString("usn"), resultSet.getString("stuname"),
                        resultSet.getString("branch"), resultSet.getString("ac_year"),
                        resultSet.getInt("ia1"), resultSet.getInt("ia2"), resultSet.getInt("ia3"),
                        resultSet.getInt("cta"), resultSet.getInt("cie")));
            }
            statement1.executeUpdate(query1);
        }catch (SQLException e){e.printStackTrace();}
        loadData();
        tblView.setItems(data);
    }
}