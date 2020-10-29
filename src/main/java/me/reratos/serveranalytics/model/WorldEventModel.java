package me.reratos.serveranalytics.model;

import javax.persistence.*;

@Entity
@Table(name = "worldevent")
public class WorldEventModel extends BasicModel<Long> {

    @ManyToOne
    @JoinColumn(name = "fke_world", nullable = false)
    private WorldModel worldModel;

    @Column(length = 30)
    private String eventType;

    /* Getters e Setters */
    public WorldModel getWorldModel() {
        return worldModel;
    }

    public void setWorldModel(WorldModel worldModel) {
        this.worldModel = worldModel;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
