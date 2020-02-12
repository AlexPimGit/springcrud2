package crud.dao;


import crud.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDaoImpl implements UserDao {
    private Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());

    //проверить работает ли без Autowired
    @Autowired// автоматически подтягивает бин-объект sessionFactory из метода import org.hibernate.SessionFactory;
    private SessionFactory sessionFactory;

    //как в этих методах быть с исключениями??
    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        LOGGER.log(Level.INFO, "User successfully saved. User details: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        LOGGER.log(Level.INFO, "User successfully updated. User details: " + user);
    }

    @Override
    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);

        if (user != null) {
            session.delete(user);
        }
        LOGGER.log(Level.INFO, "User successfully deleted. User details: " + user);
    }

    @Override
    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        LOGGER.log(Level.INFO, "User successfully loaded. User details: " + user);
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUser() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> userList = session.createQuery("FROM User").list();
        for (User user : userList) {
            LOGGER.log(Level.INFO, "User list: " + user);
        }
        return userList;
    }
}
