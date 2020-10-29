package me.reratos.serveranalytics.model;

import me.reratos.serveranalytics.dao.IdGenericDAO;

import javax.persistence.*;

@Entity
@Table (name = "playerchangeworldevent")
public class PlayerChangedWorldEventModel extends IdGenericModel<Long> {

    @OneToOne
    @JoinColumn(name = "fke_playerevent", nullable = false)
    private PlayerEventModel playerEventModel;

    @ManyToOne
    @JoinColumn(name = "fke_worldTo", nullable = false)
    private WorldModel worldModelTo;

    @ManyToOne
    @JoinColumn(name = "fke_worldFrom", nullable = false)
    private WorldModel worldModelFrom;

    public PlayerChangedWorldEventModel() {

    }

    /* Getters e Setters */
    public PlayerEventModel getPlayerEventModel() {
        return playerEventModel;
    }

    public void setPlayerEventModel(PlayerEventModel playerEventModel) {
        this.playerEventModel = playerEventModel;
    }

    public WorldModel getWorldModelTo() {
        return worldModelTo;
    }

    public void setWorldModelTo(WorldModel worldModelTo) {
        this.worldModelTo = worldModelTo;
    }

    public WorldModel getWorldModelFrom() {
        return worldModelFrom;
    }

    public void setWorldModelFrom(WorldModel worldModelFrom) {
        this.worldModelFrom = worldModelFrom;
    }
}
