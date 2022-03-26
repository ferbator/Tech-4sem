package dao.daoImpl;

import dao.daoInterface.DAO;
import dao.entities.FriendshipCat;
import dao.tools.DAOException;
import dao.tools.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class FriendshipCatsDAO implements DAO<FriendshipCat> {
    @Override
    public List<FriendshipCat> findAll() throws DAOException {
        try {
            List<FriendshipCat> objects;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            objects = session.createQuery("select e from FriendshipCat e order by e.id", FriendshipCat.class)
                    .getResultList();
            session.getTransaction().commit();
            session.close();

            return objects;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public boolean refract(FriendshipCat object) throws DAOException {
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
    public boolean add(FriendshipCat object) throws DAOException {
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
    public boolean del(FriendshipCat object) throws DAOException {
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
    public FriendshipCat getById(long id) throws DAOException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            FriendshipCat item = session.byId(FriendshipCat.class).load(id);
            session.close();

            return item;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}
