package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.GenericModel;

import java.util.List;

public interface IDAO <T extends GenericModel, E> {
    public void save(T t);
    public T update(T t);
    public void saveOrUpdate(T t);
    public void delete(T t);
    public void delete(E id) throws IllegalAccessException, InstantiationException;
    public T find(T t);
    public T findByID(E id) throws IllegalAccessException, InstantiationException;
    public List<T> findAll();
}
