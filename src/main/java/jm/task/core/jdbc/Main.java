package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.transaction.SystemException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SystemException, SQLException {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
//        userDaoHibernate.createUsersTable();
//        userDaoHibernate.saveUser("ba","saw", (byte) 23);
//        userDaoHibernate.saveUser("sqd","wdqwf", (byte) 24);

        System.out.println(userDaoHibernate.getAllUsers().toString());
//        userDaoHibernate.cleanUsersTable();
//        userDaoHibernate.dropUsersTable();
//


    }
}
