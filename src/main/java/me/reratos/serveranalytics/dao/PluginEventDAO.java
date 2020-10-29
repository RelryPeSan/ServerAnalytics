package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.PluginEventModel;

public class PluginEventDAO extends IdGenericDAO<PluginEventModel, Long> {

    public PluginEventDAO() {
        super(PluginEventModel.class);
    }

}
