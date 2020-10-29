package me.reratos.serveranalytics.model;

import javax.persistence.*;

@Entity
@Table (name = "serverevent")
public class ServerEventModel extends BasicModel<Long> {

    @Column(length = 30)
    private String eventType;

    @Column
    private Boolean asynchronous;

    public ServerEventModel() {

    }

    /* Getters e Setters */
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Boolean getAsynchronous() {
        return asynchronous;
    }

    public void setAsynchronous(Boolean asynchronous) {
        this.asynchronous = asynchronous;
    }
}
