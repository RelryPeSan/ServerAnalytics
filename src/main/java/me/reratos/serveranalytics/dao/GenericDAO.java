package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.ServerAnalytics;
import me.reratos.serveranalytics.connections.Database;
import me.reratos.serveranalytics.model.BaseModel;
import me.reratos.serveranalytics.model.GenericModel;

import javax.persistence.EntityManager;

public abstract class GenericDAO<T extends GenericModel, E> implements IDAO<T, E> {

    final EntityManager entityManager;
    protected Class<T> persistentClass;

    public GenericDAO(Class<T> persistentClass) {
        entityManager = Database.getConnection();
        this.persistentClass = persistentClass;
    }

    @Override
    public T find(T entity) {
        return (T) entityManager.find(entity.getClass(), entity.getId());
    }

    @Override
    public T findByID(E id) throws IllegalAccessException, InstantiationException {
        try {
            T t = persistentClass.newInstance();
            t.setId(id);
            return find(t);
        } catch (InstantiationException | IllegalAccessException e) {
            throw e;
        }
    }

    @Override
    public void save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public T update(T entity) {
        entityManager.getTransaction().begin();
        T t = entityManager.merge(entity);
        entityManager.getTransaction().commit();

        return t;
    }

    @Override
    public void saveOrUpdate(T t) {
        if(t.getId() == null) {
            save(t);
        } else {
            update(t);
        }
    }

    @Override
    public void delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.getReference(entity.getClass(), entity.getId()));
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(E id) throws IllegalAccessException, InstantiationException, IllegalArgumentException {
        try {
            T t = persistentClass.newInstance();
            t.setId(id);
            entityManager.getTransaction().begin();
            entityManager.remove(find(t));
            entityManager.getTransaction().commit();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

}
