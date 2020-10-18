package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.ServerAnalytics;
import me.reratos.serveranalytics.model.BaseModel;

public abstract class BaseDAO<T extends BaseModel, E> extends GenericDAO<T, E>{
    public BaseDAO(Class<T> persistentClass) {
        super(persistentClass);
    }

    @Override
    public void save(T entity) {
        entity.setServerModel(ServerAnalytics.serverModel);
        super.save(entity);
    }
}
