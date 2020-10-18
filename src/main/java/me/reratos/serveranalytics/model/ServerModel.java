package me.reratos.serveranalytics.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "server")
public class ServerModel extends GenericModel<Long> {

    @Column(length = 40, nullable = false, unique = true)
    private String name;

    @Type(type = "uuid-char")
    @Column(length = 36, nullable = false)
    private UUID serverUUID;

    /* Constructors */

    /* Getters e Setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getServerUUID() {
        return serverUUID;
    }

    public void setServerUUID(UUID serverUUID) {
        this.serverUUID = serverUUID;
    }
}
