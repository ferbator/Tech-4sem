package dao.daoImpl;

import dao.daoInterface.DAO;
import dao.entities.Cats;
import dao.tools.DAOException;
import dao.tools.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class CatsDAO implements DAO<Cats> {
    @Override
    public List<Cats> findAll() throws DAOException {
        try {
            List<Cats> objects;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            objects = session.createQuery("select e from Cats e order by e.id", Cats.class)
                    .getResultList();
            session.getTransaction().commit();
            session.close();

            return objects;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public boolean refract(Cats object) throws DAOException {
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
    public boolean add(Cats object) throws DAOException {
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
    public boolean del(Cats object) throws DAOException {
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
    public Cats getById(long id) throws DAOException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Cats item = session.byId(Cats.class).load(id);
            session.close();

            return item;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}