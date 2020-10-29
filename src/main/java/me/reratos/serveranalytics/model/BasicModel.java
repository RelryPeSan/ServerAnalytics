package me.reratos.serveranalytics.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasicModel<I> extends GenericModel<I> {

    @ManyToOne
    @JoinColumn(name = "fke_server", nullable = false)
    private ServerModel serverModel;

    public BasicModel(){}

    /* Getters e Setters */
    public ServerModel getServerModel() {
        return serverModel;
    }

    public void setServerModel(ServerModel serverModel) {
        this.serverModel = serverModel;
    }

}
