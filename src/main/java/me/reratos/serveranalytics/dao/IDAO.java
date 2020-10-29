package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.IdGenericModel;

public interface IDAO<T extends IdGenericModel, E> {
    public T save(T t);
    public T update(T t);
    public T saveOrUpdate(T t);
    public void delete(T t);
    public void delete(E id) throws IllegalAccessException, InstantiationException;
    public T find(T t);
    public T findByID(E id) throws IllegalAccessException, InstantiationException;
}
