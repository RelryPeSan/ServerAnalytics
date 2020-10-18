package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.PlayerEventModel;

import java.util.List;

public class PlayerEventDAO extends BaseDAO<PlayerEventModel, Long> {

    public PlayerEventDAO() {
        super(PlayerEventModel.class);
    }

    @Override
    public List<PlayerEventModel> findAll() {
        return null;
    }
}
