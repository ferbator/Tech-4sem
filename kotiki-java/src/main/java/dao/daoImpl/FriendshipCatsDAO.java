package dao.daoImpl;

import dao.daoInterface.DAO;
import dao.entities.FriendshipCats;
import dao.tools.DAOException;
import dao.tools.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class FriendshipCatsDAO implements DAO<FriendshipCats> {
    @Override
    public List<FriendshipCats> findAll() throws DAOException {
        try {
            List<FriendshipCats> objects;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            objects = session.createQuery("select e from FriendshipCats e order by e.id", FriendshipCats.class)
                    .getResultList();
            session.getTransaction().commit();
            session.close();

            return objects;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public boolean refract(FriendshipCats object) throws DAOException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(object);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public boolean add(FriendshipCats object) throws DAOException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(object);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public boolean del(FriendshipCats object) throws DAOException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(object);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public FriendshipCats getById(long id) throws DAOException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            FriendshipCats item = session.byId(FriendshipCats.class).load(id);
            session.close();

            return item;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}
