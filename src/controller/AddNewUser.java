package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Library;
import model.User;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddNewUser {

    @FXML
    private DatePicker dpdob;

    @FXML
    private ToggleGroup rdoType;

    @FXML
    private TextArea txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnRegister;


    @FXML
    private RadioButton rdoFaculty;

    @FXML
    private RadioButton rdoStudent;

    Library library;

    public void  initialize(){
    }
    public void initData(Library library){
        this.library = library;

    }
    @FXML
    void btnRegister(ActionEvent event) throws SQLException {
        String userType = ((RadioButton)rdoType.getSelectedToggle()).getText();
        Boolean isStudent = false;
        if(userType.equalsIgnoreCase("student"))
            isStudent = true;


        String name = txtName.getText();
       String email = txtEmail.getText();
       String address = txtAddress.getText();
        LocalDate date = dpdob.getValue();

        library.addUser(new User(name,email,address,date,isStudent));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.hide();

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText("Registered "+ name);
        alert.initOwner(stage);
        alert.showAndWait();

    }

    @FXML
    void rdoFaculty(ActionEvent event) {

    }

    @FXML
    void rdoStudent(ActionEvent event) {

    }

}
