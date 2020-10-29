package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.ServerEventModel;

public class ServerEventDAO extends BasicDAO<ServerEventModel, Long> {

    public ServerEventDAO() {
        super(ServerEventModel.class);
    }

}
