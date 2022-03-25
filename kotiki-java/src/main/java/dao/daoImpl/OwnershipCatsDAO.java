package dao.daoImpl;

import dao.daoInterface.DAO;
import dao.entities.OwnershipCats;
import dao.tools.DAOException;
import dao.tools.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class OwnershipCatsDAO implements DAO<OwnershipCats> {
    @Override
    public List<OwnershipCats> findAll() throws DAOException {
        try {
            List<OwnershipCats> objects;
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            objects = session.createQuery("select e from OwnershipCats e order by e.id", OwnershipCats.class)
                    .getResultList();
            session.getTransaction().commit();
            session.close();

            return objects;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }

    @Override
    public boolean refract(OwnershipCats object) throws DAOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(object);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean add(OwnershipCats object) throws DAOException {
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
    public boolean del(OwnershipCats object) throws DAOException {
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
    public OwnershipCats getById(long id) throws DAOException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            OwnershipCats item = session.byId(OwnershipCats.class).load(id);
            session.close();

            return item;
        } catch (HibernateException e) {
            throw new DAOException(e.getMessage(), e);
        }
    }
}
