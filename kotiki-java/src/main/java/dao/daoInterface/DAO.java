package dao.daoInterface;

import dao.tools.DAOException;

import java.util.List;

public interface DAO<T> {
    List<T> findAll() throws DAOException;

    boolean refract(T object) throws DAOException;

    boolean add(T object) throws DAOException;

    boolean del(T object) throws DAOException;

    T getById(long id) throws DAOException;
}
