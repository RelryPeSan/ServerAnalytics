package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.EntityDeathEventModel;

import java.util.List;

public class EntityDeathEventDAO extends GenericDAO<EntityDeathEventModel, Long> {

    public EntityDeathEventDAO() {
        super(EntityDeathEventModel.class);
    }

    @Override
    public List<EntityDeathEventModel> findAll() {
        return null;
    }
}
