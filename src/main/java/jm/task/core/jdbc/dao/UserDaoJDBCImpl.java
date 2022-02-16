package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Connection conn = util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (
                Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE TABLE if not exists Users " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Statement st = null;
        try (
                Statement stmt = conn.createStatement();
        ) {
            String sql = "drop table if exists users";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        Statement st = null;
        try {
            String sql = "insert into users(first, last, age) VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        Statement st = null;
        try {
            st = conn.createStatement();
            String sql = "UPDATE users where id = " + id + " ";
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        Statement st = null;
        try {
            st = conn.createStatement();
            String sql = "select * from users";
            ResultSet resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                String name = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(name, lastname, age);
                users.add(user);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }

    public void cleanUsersTable() {
        Statement st = null;
        try {
            Util util = new Util();
            st = conn.createStatement();
            String sql = "delete from user";
            st.execute(sql);
        } catch (SQLException e) {

            e.printStackTrace();

        }
    }
}
