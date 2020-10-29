package me.reratos.serveranalytics.model;

import org.bukkit.World;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "world")
public class WorldModel extends BasicModel<Long> {

    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 10)
    private String environment;

    @Column(nullable = false, length = 10)
    private String difficulty;

    @Column(nullable = false)
    private Boolean hardcore;

    @Column(nullable = false)
    private Boolean autoSave;

    @Column(nullable = false)
    private Integer viewDistance;

    /* Constructors */
    public WorldModel() {
    }

    public WorldModel(String name) {
        this.name = name;
    }

    public WorldModel(World world) {
        this.name = world.getName();
    }

    /* Getters e Setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Boolean getHardcore() {
        return hardcore;
    }

    public void setHardcore(boolean hardcore) {
        this.hardcore = hardcore;
    }

    public Boolean getAutoSave() {
        return autoSave;
    }

    public void setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
    }

    public Integer getViewDistance() {
        return viewDistance;
    }

    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    }
}
