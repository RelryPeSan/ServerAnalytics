package me.reratos.serveranalytics.model;

import javax.persistence.*;

@Entity
@Table(name = "pluginevent")
public class PluginEventModel extends IdGenericModel<Long> {

    @ManyToOne
    @JoinColumn(name = "fke_plugin", nullable = false)
    private PluginModel pluginModel;

    @OneToOne
    @JoinColumn(name = "fke_serverevent", nullable = false)
    private ServerEventModel serverEventModel;

    @Column(nullable = false)
    private Boolean enabled;

    /* Getters e Setters */
    public PluginModel getPluginModel() {
        return pluginModel;
    }

    public void setPluginModel(PluginModel pluginModel) {
        this.pluginModel = pluginModel;
    }

    public ServerEventModel getServerEventModel() {
        return serverEventModel;
    }

    public void setServerEventModel(ServerEventModel serverEventModel) {
        this.serverEventModel = serverEventModel;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
