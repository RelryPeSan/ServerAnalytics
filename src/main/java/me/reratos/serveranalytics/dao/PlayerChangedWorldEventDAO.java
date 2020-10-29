package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.PlayerChangedWorldEventModel;

public class PlayerChangedWorldEventDAO extends IdGenericDAO<PlayerChangedWorldEventModel, Long> {

    public PlayerChangedWorldEventDAO() {
        super(PlayerChangedWorldEventModel.class);
    }

}
