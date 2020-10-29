package me.reratos.serveranalytics.model;

import javax.persistence.*;

@Entity
@Table (name = "chatevent")
public class ChatEventModel extends IdGenericModel<Long> {

    @OneToOne
    @JoinColumn(name = "fke_playerevent")
    private PlayerEventModel playerEventModel;

    @OneToOne
    @JoinColumn(name = "fke_serverevent")
    private ServerEventModel serverEventModel;

    @Lob
    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Boolean isCommand;

    public ChatEventModel() {

    }

    /* Getters e Setters */
    public PlayerEventModel getPlayerEventModel() {
        return playerEventModel;
    }

    public void setPlayerEventModel(PlayerEventModel playerEventModel) {
        this.playerEventModel = playerEventModel;
    }

    public ServerEventModel getServerEventModel() {
        return serverEventModel;
    }

    public void setServerEventModel(ServerEventModel serverEventModel) {
        this.serverEventModel = serverEventModel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean isCommand() {
        return isCommand;
    }

    public void setCommand(boolean isCommand) {
        this.isCommand = isCommand;
    }
}
