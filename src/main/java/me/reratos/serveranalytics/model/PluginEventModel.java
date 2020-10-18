package me.reratos.serveranalytics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pluginevent")
public class PluginEventModel extends BaseModel<Long>{

    @Column
    private String namePlugin;

    @Column
    private boolean enabled;

    /* Getters e Setters */
    public String getNamePlugin() {
        return namePlugin;
    }

    public void setNamePlugin(String namePlugin) {
        this.namePlugin = namePlugin;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
