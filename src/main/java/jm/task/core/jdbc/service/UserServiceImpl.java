package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import javax.transaction.SystemException;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoHibernateImpl userDaoJDBC = new UserDaoHibernateImpl();
    public void createUsersTable() throws SystemException {
        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoJDBC.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
        userDaoJDBC.removeUserById(id);

    }

    public List<User> getAllUsers() {
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoJDBC.cleanUsersTable();

    }
}
