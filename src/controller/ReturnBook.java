package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;
import model.Library;

import java.sql.SQLException;

public class ReturnBook {

    @FXML
    private Button btnReturn;

    @FXML
    private Label lblReturned;

    @FXML
    private ListView<Book> lstDisplayBook;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtBookSearch;
    Library library;

    public void  initialize(){

    }
    public void initData(Library library) throws SQLException {
        this.library = library;
        lstDisplayBook.setItems(FXCollections.observableArrayList(library.getBooks()));


        txtBookSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if(txtBookSearch.getText().isEmpty()) {
                try {
                    lstDisplayBook.setItems(FXCollections.observableArrayList(library.getBooks()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    lstDisplayBook.setItems(FXCollections.observableArrayList(library.searchBook(newValue)));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            lstDisplayBook.refresh();
        });
        lstDisplayBook.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if(newValue!=null)
                 txtBookID.setText(newValue.getID()+"");
        });

    }

    @FXML
    void ReturningBook(ActionEvent event) throws SQLException {

        library.msgLog.clear();
        if (txtBookID.getText().isEmpty()) {
            lblReturned.setText("Enter a book id");
            lblReturned.setStyle("-fx-text-fill: darkred");
        } else {

            boolean result = library.returnBook(Integer.parseInt(txtBookID.getText()));

            String message = "";
            for (String s : library.msgLog)
                message += s + "\n";
            lblReturned.setText(message);
            if (!result)
                lblReturned.setStyle("-fx-text-fill: darkred");
            else
                lblReturned.setStyle("-fx-text-fill: green");
        }
    }
}
