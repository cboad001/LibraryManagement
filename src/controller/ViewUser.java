package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Book;
import model.Library;
import model.Transaction;
import model.User;

import java.sql.SQLException;

public class ViewUser {


    @FXML
    private ComboBox<User> cmbUser;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblName;

    @FXML
    private Label lblUserType;

    @FXML
    private Label lbldob;

    @FXML
    private ListView<Book> lstCheckedBooks;
    @FXML
    private Button btnCollectFine;
    Library library;

    public void  initialize(){

    }
    public void initData(Library library) throws SQLException {
        this.library = library;
        cmbUser.setItems(FXCollections.observableArrayList(library.getUsers()));

        cmbUser.setOnAction(event -> {
            lstCheckedBooks.getItems().clear();
            User u = cmbUser.getValue();
            displayInfo(u);
            btnCollectFine.setOnAction(event1 -> {
                try {
                    library.collectFine(u);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                displayInfo(u);
            });

            try {
                lstCheckedBooks.setItems(FXCollections.observableArrayList(library.getCheckedOutBooks(u)));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        });

    }
    void displayInfo(User u) {
        lbldob.setText(u.getDateOfBirth() + "");
        lblName.setText(u.getName());
        lblEmail.setText(u.getEmail());
        lblAddress.setText(u.getAddress());
        if (u.getStudent())
            lblUserType.setText("Student");
        else lblUserType.setText("Faculty");
        lblBalance.setText("$"+u.getBalance());
        if (u.getBalance() == 0) {
            lblBalance.setStyle("-fx-text-fill: green");
            btnCollectFine.setVisible(false);
        }
        else {
            lblBalance.setStyle("-fx-text-fill: darkred");
            btnCollectFine.setVisible(true);
        }
    }

}
