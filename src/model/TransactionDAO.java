package model;


import java.sql.*;
import java.util.ArrayList;

public class TransactionDAO {
    Connection connection;

    public TransactionDAO(String url) throws SQLException {

        connection = DriverManager.getConnection(url);
    }

   public void insert(Transaction t) throws SQLException {
       PreparedStatement ps = connection.prepareStatement("insert into libTransactions values(?,?,?,?)");
        ps.setInt(1,t.getBookID());
        ps.setInt(2,t.getUserID());
        ps.setDate(3,Date.valueOf(t.getIssueDate()));
        ps.setBoolean(4,t.isStatus());

        ps.executeUpdate();

   }
   public void delete(Transaction t) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("delete from libTransactions where bookID=? AND userID=? AND issueDate=?");
        ps.setInt(1,t.getBookID());
        ps.setInt(2,t.getUserID());
        ps.setDate(3,Date.valueOf(t.getIssueDate()));

        ps.executeUpdate();
   }

    public void update(Transaction t) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update libTransactions set status=? where bookID=? AND userID=? AND issueDate=?");
        ps.setBoolean(1, t.isStatus());
        ps.setInt(2,t.getBookID());
        ps.setInt(3,t.getUserID());
        ps.setDate(4,Date.valueOf(t.getIssueDate()));

        ps.executeUpdate();
    }

   public ArrayList<Transaction> getAll()throws SQLException{
        ArrayList<Transaction> transactions = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("select * from libTransactions");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Transaction t = new Transaction(rs.getInt(1),rs.getInt(2));
            transactions.add(t);
        }
        rs.close();
        return transactions;
   }

   public ArrayList<Transaction> getCurrent() throws SQLException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement("select * from libTransactions where status=?");
        ps.setBoolean(1,true);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Transaction t = new Transaction(rs.getInt(1),rs.getInt(2));
            transactions.add(t);
        }
        rs.close();
        return transactions;
   }
    public ArrayList<Transaction> getByUser(int id) throws SQLException {
    ArrayList<Transaction> transactions = new ArrayList<>();
//        status=true AND
    PreparedStatement ps = connection.prepareStatement("select * from libTransactions where  userID = ?");
    ps.setInt(1,id);
    ResultSet rs = ps.executeQuery();
    while (rs.next()){
        Transaction t = new Transaction(rs.getInt(1),rs.getInt(2));
        transactions.add(t);
    }
    rs.close();
    return transactions;
}
public ArrayList<Transaction> getByBook(int id) throws SQLException {
    ArrayList<Transaction> transactions = new ArrayList<>();
    PreparedStatement ps = connection.prepareStatement("select * from libTransactions where bookID = ?");
    ps.setInt(1,id);
    ResultSet rs = ps.executeQuery();
    while (rs.next()){
        Transaction t = new Transaction(rs.getInt(1),rs.getInt(2));
        transactions.add(t);
    }
    rs.close();
    return transactions;
}
}




