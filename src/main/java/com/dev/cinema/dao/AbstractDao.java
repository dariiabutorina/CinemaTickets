package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DatabaseDataExchangeException;
import com.dev.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected static final String CREATE_MESSAGE = "Failed to create the entity ";
    protected static final String GET_ALL_MESSAGE =
            "Failed to retrieve the list of all entities - ";
    protected final SessionFactory factory;

    protected AbstractDao() {
        this.factory = HibernateUtil.getSessionFactory();
    }

    public T create(T entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DatabaseDataExchangeException(CREATE_MESSAGE + entity, exception);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
