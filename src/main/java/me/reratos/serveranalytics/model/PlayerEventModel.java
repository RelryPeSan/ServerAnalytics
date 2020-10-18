package me.reratos.serveranalytics.model;

import javax.persistence.*;

@Entity
@Table (name = "playerevent")
public class PlayerEventModel extends BaseModel<Long> {

    @ManyToOne
    @JoinColumn(name = "fke_player")
    private PlayerModel playerModel;

    @Column(length = 30)
    private String eventType;

    public PlayerEventModel() {
        super();
    }

    /* Getters e Setters */
    public PlayerModel getPlayer() {
        return playerModel;
    }

    public void setPlayer(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

}
