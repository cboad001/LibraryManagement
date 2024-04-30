package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Library;

import java.io.IOException;
import java.sql.SQLException;


public class MainMenuController {
    @FXML
    private Button btnIssueBook;

    @FXML
    private Button btnNewBook;

    @FXML
    private Button btnNewUser;

    @FXML
    private Button btnReturnBook;

    @FXML
    private Button btnViewIssueBooks;

    @FXML
    private Button btnViewUser;
    Library library;

    void  initialize(){

    }
    void initData(Library library){
    this.library = library;
    }

    @FXML
    void addBook(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/AddNewBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddNewBook controller = fxmlLoader.getController();
        controller.initData(library);
        Stage stage = new Stage();
        stage.setTitle("Add New Book");
        stage.setScene(scene);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.initOwner(thisStage);
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();


    }

    @FXML
    void addUser(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/AddNewUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AddNewUser controller = fxmlLoader.getController();
        controller.initData(library);
        Stage stage = new Stage();
        stage.setTitle("Add New User");
        stage.setScene(scene);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.initOwner(thisStage);
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();
    }

    @FXML
    void issueBook(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/IssueBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        IssueBook controller = fxmlLoader.getController();
        controller.initData(library);
        Stage stage = new Stage();
        stage.setTitle("Issue Book to User");
        stage.setScene(scene);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.initOwner(thisStage);
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();
    }

    @FXML
    void returnBook(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/ReturnBook.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ReturnBook controller = fxmlLoader.getController();
        controller.initData(library);
        Stage stage = new Stage();
        stage.setTitle("Return book from User");
        stage.setScene(scene);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.initOwner(thisStage);
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();
    }

    @FXML
    void viewBooks(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/ViewBooks.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ViewBooks controller = fxmlLoader.getController();
        controller.initData(library);
        Stage stage = new Stage();
        stage.setTitle("View Issued Books");
        stage.setScene(scene);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.initOwner(thisStage);
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();
    }

    @FXML
    void viewUser(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/ViewUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ViewUser controller = fxmlLoader.getController();
        controller.initData(library);
        Stage stage = new Stage();
        stage.setTitle("User Details");
        stage.setScene(scene);

        Stage thisStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.initOwner(thisStage);
        stage.initModality(Modality.WINDOW_MODAL);

        stage.showAndWait();
    }

}
