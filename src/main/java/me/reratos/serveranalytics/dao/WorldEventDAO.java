package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.WorldEventModel;

public class WorldEventDAO extends BasicDAO<WorldEventModel, Long> {

    public WorldEventDAO() {
        super(WorldEventModel.class);
    }

}
