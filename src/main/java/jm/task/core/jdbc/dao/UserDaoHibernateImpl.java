package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.transaction.SystemException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Transaction transaction = null;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() throws SystemException {

        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }e.printStackTrace();
        }
    }



    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            String sql = sql = "DROP TABLE IF EXISTS user";

            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        User user = new User(name,lastName,age);
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }e.printStackTrace();
        }


    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();

            String sql = "DELETE FROM user where id = :id";
            Query query = session.createSQLQuery(sql);
            query.setParameter("id",id);

            query.executeUpdate();

            transaction.commit();
            session.close();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {

            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
         session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }e.printStackTrace();
        }

    }
}
