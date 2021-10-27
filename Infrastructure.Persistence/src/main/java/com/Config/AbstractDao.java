package com.Config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

//TODO need to do log and exception handling?
public abstract class AbstractDao<T> {

    @Autowired
    private SessionFactory sessionFactory;


    protected Cache<String, List<T>> cache = Caffeine.newBuilder()
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .maximumSize(1000)
            .build();


    protected StatelessSession statelessSession(){
        return sessionFactory.openStatelessSession();
    }

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    public T insert(T entity) {
        currentSession().save(entity);
        return entity;
    }

    public T saveOrUpdate(T entity) {
        currentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(T entity) {
        currentSession().update(entity);
        return entity;
    }

    public T remove(T entity) {
        currentSession().delete(entity);
        return entity;
    }

    protected int doTransaction(Consumer<Session> function) {
        Session session = null;
        Transaction txn = null;
        int status = 0;
        try {
            session = sessionFactory.withOptions().openSession();

            txn = session.getTransaction();
            txn.begin();
            function.accept(session);
            txn.commit();
            status = 0;
        } catch (Throwable e) {
            if (txn != null) txn.rollback();
            status = 1;
        } finally {
            if (session != null) {
                session.close();
            }
            return status;

        }
    }

}
