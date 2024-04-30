package model;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
Connection connection;


public UserDAO(String url) throws SQLException {
    connection = DriverManager.getConnection(url);
}

    public void insert(User u) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into users (name,email,address, dateOfBirth,isStudent,balance) values(?,?,?,?,?,?)");
        ps.setString(1,u.getName());
        ps.setString(2,u.getEmail());
        ps.setString(3,u.getAddress());
        ps.setDate(4,Date.valueOf(u.getDateOfBirth()));
        ps.setBoolean(5,u.getStudent());
        ps.setDouble(6,u.getBalance());

        ps.executeUpdate();

    }
public void delete(User u) throws SQLException {
    PreparedStatement ps = connection.prepareStatement("delete from users where id = ? ");
    ps.setInt(1,u.getID());

    ps.executeUpdate();
}
public void update(User u) throws SQLException {
    PreparedStatement ps = connection.prepareStatement("update users set name=?,email=?,address=?,dateOfBirth=?,isStudent=?,balance=? where id = ?");

    ps.setString(1, u.getName());
    ps.setString(2, u.getEmail());
    ps.setString(3, u.getAddress());
    ps.setDate(4, Date.valueOf(u.getDateOfBirth()));
    ps.setBoolean(5,u.getStudent());
    ps.setDouble(6,u.getBalance());
    ps.setInt(7, u.getID());

    ps.executeUpdate();

}
public ArrayList<User> getAll() throws SQLException {
    ArrayList<User> users = new ArrayList<>();
    PreparedStatement ps = connection.prepareStatement("select * from users");
    ResultSet rs = ps.executeQuery();
    while (rs.next()){
        User u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate(),rs.getBoolean(6),rs.getDouble(7));
    users.add(u);
    }
    rs.close();
    return users;
}
public ArrayList<User> getByQuery(String name) throws SQLException {
    ArrayList<User> users = new ArrayList<>();
    PreparedStatement ps = connection.prepareStatement("select * from users where name LIKE ?");
    ps.setString(1,"%"+name+"%");
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        User u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(), rs.getBoolean(6), rs.getDouble(7));
        users.add(u);
    }
    return users;
}
public User getUser(int id) throws SQLException {
    User u;
    PreparedStatement ps = connection.prepareStatement("select * from users where ID = ?");
    ps.setInt(1,id);
    ResultSet rs = ps.executeQuery();

    u = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate(),rs.getBoolean(6),rs.getDouble(7));
    return u;
}


}
