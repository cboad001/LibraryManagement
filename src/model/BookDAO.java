package model;


import java.sql.*;
import java.util.ArrayList;

public class BookDAO {
    Connection connection;


    public BookDAO(String url) throws SQLException {

        connection = DriverManager.getConnection(url);
    }

    public  void insert(Book b) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into books (name,author,publisher,genre,ISBN,year)values (?,?,?,?,?,?)");
        ps.setString(1,b.getName());
        ps.setString(2,b.getAuthor());
        ps.setString(3,b.getPublisher());
        ps.setString(4,b.getGenre());
        ps.setString(5,b.getISBN());
        ps.setLong(6,b.getYear());

        ps.executeUpdate();
    }
    public void delete(Book b) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from books where id = ? ");
        ps.setInt(1,b.getID());

        ps.executeUpdate();
    }
    public void update(Book b) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update books set name=?,author=?,publisher=?,genre=?,ISBN=?,year=? where id=?");

        ps.setString(1, b.getName());
        ps.setString(2, b.getAuthor());
        ps.setString(3, b.getPublisher());
        ps.setString(4, b.getGenre());
        ps.setString(5,b.getISBN());
        ps.setInt(6,Math.toIntExact(b.getYear()));
        ps.setInt(7, b.getID());

        ps.executeUpdate();

    }
    public ArrayList<Book> getAll() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("select * from books");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Book b = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6),rs.getLong(7));
            books.add(b);
        }
        rs.close();
        return books;
    }
    public ArrayList<Book> getByQuery(String name) throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("select * from books where name LIKE ?");
        ps.setString(1,"%"+name+"%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Book b = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6),rs.getLong(7));
            books.add(b);
        }
        rs.close();
        return books;
    }
    public Book getBook(int id) throws SQLException {
        Book b;
        PreparedStatement ps = connection.prepareStatement("select * from books where ID =?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

         b = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5),rs.getString(6),rs.getLong(7));
        return b;
    }
}
