package me.reratos.serveranalytics.model;

import javax.persistence.*;

@Entity
@Table (name = "serverevent")
public class ServerEventModel extends BaseModel<Long> {

    @Column(length = 30)
    private String eventType;

    public ServerEventModel() {
        super();
    }

    /* Getters e Setters */
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

}
