package me.reratos.serveranalytics.model;

import org.bukkit.entity.Player;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name = "player")
public class PlayerModel extends BaseModel<Long> {

    @Type(type = "uuid-char")
    @Column(unique = true, nullable = false, length = 36)
    private UUID uuid;

    @Column(unique = true, nullable = false, length = 30)
    private String name;

    public PlayerModel(Player player) {
        this.name = player.getName();
        this.uuid = player.getUniqueId();
    }

    /* Getters e Setters */
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
