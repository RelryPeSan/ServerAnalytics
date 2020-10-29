package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.PlayerEventModel;

public class PlayerEventDAO extends BasicDAO<PlayerEventModel, Long> {

    public PlayerEventDAO() {
        super(PlayerEventModel.class);
    }

}
