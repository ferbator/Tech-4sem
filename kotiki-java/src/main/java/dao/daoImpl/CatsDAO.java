package dao.daoImpl;

import dao.daoInterface.DAO;
import dao.entities.Cat;
import dao.tools.DAOException;
import dao.tools.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class CatsDAO implements DAO<Cat> {
    @Override
    public List<Cat> findAll() throws DAOException {
        try {
            List<Cat> objects;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            objects = session.createQuery("select e from Cat e order by e.id", Cat.class)
                    .getResultList();
            session.getTransaction().commit();
            session.close();

            return objects;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public boolean refract(Cat object) throws DAOException {
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
    public boolean add(Cat object) throws DAOException {
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
    public boolean del(Cat object) throws DAOException {
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
    public Cat getById(long id) throws DAOException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Cat item = session.byId(Cat.class).load(id);
            session.close();

            return item;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}