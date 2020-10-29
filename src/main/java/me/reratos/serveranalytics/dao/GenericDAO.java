package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.GenericModel;

public class GenericDAO<T extends GenericModel, E> extends IdGenericDAO<T, E> implements IDAO<T, E> {

    public GenericDAO(Class<T> persistentClass) {
        super(persistentClass);
    }
}
