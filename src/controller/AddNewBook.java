package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Book;
import model.Library;

import java.sql.SQLException;

public class AddNewBook {

    @FXML
    private Button btnRegister;

    @FXML
    private ListView<String> lstPublisher;

    @FXML
    private TextField txtAuthor;

    @FXML
    private ComboBox<String> cmbGenre;

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtYear;
    Library library;
    ObservableList<String> publishers;
    ObservableList<String> genres;

    public void  initialize(){
    publishers = FXCollections.observableArrayList("Pearson","Disney","Penguin","Allen and Unwin","Swallow Press");
    genres = FXCollections.observableArrayList("Adventure","Thriller","History");
    lstPublisher.setItems(publishers);
    cmbGenre.setItems(genres);
    }
   public void initData(Library library){
        this.library = library;

    }

    @FXML
   public void RegisterBook(ActionEvent event) throws SQLException {

        String name = txtName.getText();
        String author = txtAuthor.getText();
        String publisher = lstPublisher.selectionModelProperty().toString();
        String genre = cmbGenre.getSelectionModel().getSelectedItem();
        String ISBN = txtISBN.getText();
        long year;
        if(txtYear.getText().isEmpty())
            year = 0;

        else
            year = Long.parseLong(txtYear.getText());

        library.addBook(new Book(name,author,publisher,genre,ISBN,year));

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        stage.hide();


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successful");
        alert.setHeaderText("Registered "+name);
        alert.initOwner(stage);
        alert.showAndWait();
    }

}
