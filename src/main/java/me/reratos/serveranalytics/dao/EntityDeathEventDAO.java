package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.EntityDeathEventModel;

public class EntityDeathEventDAO extends BasicDAO<EntityDeathEventModel, Long> {

    public EntityDeathEventDAO() {
        super(EntityDeathEventModel.class);
    }

}
