package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Book;
import model.Library;
import model.Transaction;
import model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main extends Application {
    Library library;
    public static Stage  primaryStage;
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        User u1 = new User("John Stones","jstones@gmail.com","211 Ryder St. Tallahassee, FL 32304", LocalDate.of(1989,06,13), true);
        User u2 = new User("Jack Bauer","jack24@gmail.com","7400 Bay Rd. University Center, MI 48710",LocalDate.of(1988,11,15),false);
        User u3 = new User("Harry Kane","hkane@gmail.com","123 James Boyd Rd. Scranton, PA 28410",LocalDate.of(1988,2,1), false);
        User u4 = new User("Tim Arnold","ta123@gmail.com","3412 Dinsmore Ave, MA 01710",LocalDate.of(1999,1,15), true);
        Book b1 = new Book("Programming with Java","Daniel Liang","Pearson","Education","1234568924",2020);
        Book b2 = new Book("Data Structures and Algorithms","Robert Lafore","Pearson","Education","98726213",2001);
        Book b3 = new Book("Harry Potter and The Chamber of Secrets","J.K. Rowling","Scholastic","Adventure","343255323",1998);
        Book b4 = new Book("Lord of the Rings - The Two Towers","Tolkien","Wiley","Thriller","989636362",1945);

        library = new Library();
//        library.updateUserBalance(library.getUser(1),10);

//        library.deleteTransactions();
//        library.deleteAllUsers();
//        library.deleteAllBooks();


//        library.addUser(u1);
//        u1.setBalance(10);
//        library.addUser(u2);
//        library.addUser(u3);
//        library.addUser(u4);
//
//        library.addBook(b1);
//        library.addBook(b2);
//        library.addBook(b3);
//        library.addBook(b4);




       Main.primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/MainMenu.fxml"));
        Parent root = fxmlLoader.load();

        MainMenuController controller = fxmlLoader.getController();
        controller.initData(library);
        primaryStage.setTitle("Add New Book");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}


/*
    GETTING STARTED WITH FXML and Scene Builder

    This template code is provided to speed up the JavaFX process. To run this
    project, you need to do the following
    1) Go to File->Project Structure->Libraries. Add a new Java Project model.Library
    that points to the "lib" folder to your OpenJFX installation
    2) Click on Edit Configurations on the top-right, (you may need to run the project
    once before this shows up). Click on Modify Options --> Add VM Options. Then paste
    the following text, and modify the path to point to the "lib" folder again
    --module-path "path/to/your/javafx/lib" --add-modules javafx.controls,javafx.fxml

    If you need multiple FXML views and controllers (this just provides one), right-click on
    the package name in the Project Sidebar. Then
        1) Select New FXML file. This will generate a new view file for you.
        2) You can right-click again to create a new Java file that will act as your controller.
    In Scene Builder, make sure to link your View with the controller class, as this is not
    automatically done for you.

    QuickLinks
    SceneBuilder: https://gluonhq.com/products/scene-builder/#download
    JavaFX: https://gluonhq.com/products/javafx/
 */