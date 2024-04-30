package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Book;
import model.Library;
import model.User;

import java.sql.SQLException;

public class IssueBook {

        @FXML
        private Button btnIssue;

        @FXML
        private ListView<Book> lstBookDisplay;

        @FXML
        private ListView<User> lstUserDisplay;

        @FXML
        private TextField txtBookID;

        @FXML
        private TextField txtBookSearch;

        @FXML
        private TextField txtUserID;

        @FXML
        private TextField txtUserSearch;
        @FXML
        private Label lblIssued;
        Library library;

        public void  initialize(){

        }
        public void initData(Library library) throws SQLException {
                this.library = library;


                lstBookDisplay.setItems(FXCollections.observableArrayList(library.getBooks()));
                lstUserDisplay.setItems(FXCollections.observableArrayList(library.getUsers()));

                txtBookSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
                        if(txtBookSearch.getText().isEmpty()) {
                            try {
                                lstBookDisplay.setItems(FXCollections.observableArrayList(library.getBooks()));
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            try {
                                lstBookDisplay.setItems(FXCollections.observableArrayList(library.searchBook(newValue)));
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        lstBookDisplay.refresh();
                });

                txtUserSearch.textProperty().addListener((observableValue, s, newValue) ->{
                        if(txtUserSearch.getText().isEmpty()) {
                            try {
                                lstUserDisplay.setItems(FXCollections.observableArrayList(library.getUsers()));
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        else {
                            try {
                                lstUserDisplay.setItems(FXCollections.observableArrayList(library.searchUser(newValue)));
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        lstUserDisplay.refresh();
                });

                lstUserDisplay.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                        if(newValue!=null)
                                txtUserID.setText (newValue.getID()+"");
                });
                lstBookDisplay.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                        if(newValue!=null)
                                txtBookID.setText (newValue.getID()+"");
                });

        }
        @FXML
        void IssuingBook(ActionEvent event) throws SQLException {
                library.msgLog.clear();
                if(txtBookID.getText().isEmpty()||txtUserID.getText().isEmpty()){
                    lblIssued.setText("Enter a book id");
                    lblIssued.setStyle("-fx-text-fill: darkred");
                }else {

                    int bookID = Integer.parseInt(txtBookID.getText());
                    int userID = Integer.parseInt(txtUserID.getText());

                    boolean result = library.issueBook(userID, bookID);
                    String message = "";
                    for (String s : library.msgLog)
                        message += s + "\n";
                    lblIssued.setText(message);
                    if (!result)
                        lblIssued.setStyle("-fx-text-fill: darkred");
                    else lblIssued.setStyle("-fx-text-fill: green");

                }
        }

    }


