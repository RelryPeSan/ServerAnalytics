package me.reratos.serveranalytics.model;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import javax.persistence.*;

@Entity
@Table (name = "entitydeathevent")
public class EntityDeathEventModel extends BaseModel<Long> {

    @Column(nullable = false, length = 30)
    private String entityDeadType;

    @Column(nullable = false)
    private int droppedExp;

    @Column
    private Double lastDamage;

    @Column(length = 30)
    private String lastDamageCause;

    @ManyToOne
    @JoinColumn(name = "fke_playerDead")
    private PlayerModel playerDead;

    @ManyToOne
    @JoinColumn(name = "fke_playerKiller")
    private PlayerModel playerKiller;

    /* Constructors */
    public EntityDeathEventModel(PlayerModel dead, PlayerModel killer) {
        this.entityDeadType = EntityType.PLAYER.name();
        this.playerDead = dead;

        this.playerKiller = killer;
    }

    public EntityDeathEventModel(org.bukkit.entity.Entity dead, PlayerModel killer) {
        this.entityDeadType = dead.getType().name();
        this.playerDead = null;

        this.playerKiller = killer;
    }

    public EntityDeathEventModel(PlayerModel dead) {
        this.entityDeadType = EntityType.PLAYER.name();
        this.playerDead = dead;

        this.playerKiller = null;
    }

    /* Getters e Setters */
    public String getEntityDeadType() {
        return entityDeadType;
    }

    public void setEntityDeadType(String entityDeadType) {
        this.entityDeadType = entityDeadType;
    }

    public int getDroppedExp() {
        return droppedExp;
    }

    public void setDroppedExp(int droppedExp) {
        this.droppedExp = droppedExp;
    }

    public Double getLastDamage() {
        return lastDamage;
    }

    public void setLastDamage(Double lastDamage) {
        this.lastDamage = lastDamage;
    }

    public String getLastDamageCause() {
        return lastDamageCause;
    }

    public void setLastDamageCause(String lastDamageCause) {
        this.lastDamageCause = lastDamageCause;
    }

    public PlayerModel getPlayerDead() {
        return playerDead;
    }

    public void setPlayerDead(PlayerModel playerDead) {
        this.playerDead = playerDead;
    }

    public PlayerModel getPlayerKiller() {
        return playerKiller;
    }

    public void setPlayerKiller(PlayerModel playerKiller) {
        this.playerKiller = playerKiller;
    }
}
