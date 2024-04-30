package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.Library;
import model.Transaction;
import model.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class ViewBooks {

    @FXML
    private TableColumn<Transaction, String> tbcBookID;

    @FXML
    private TableColumn<Transaction, LocalDate> tbcDate;

    @FXML
    private TableColumn<Transaction,Integer> tbcUserID;

    @FXML
    private TableView<Transaction> tbvIssuedTable;
    Library library;


    public void  initialize(){

    }
    public void initData(Library library) throws SQLException {
        this.library = library;
//        ObservableList<Transaction> transactions= FXCollections.observableArrayList();

//        for(Transaction t: library.transactions) {
//            if(t.isStatus())
//                transactions.add(t);
//        }

            tbvIssuedTable.setItems(FXCollections.observableArrayList(library.getActiveTransactions()));
            tbcBookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
            tbcUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
            tbcDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));






    }

}
