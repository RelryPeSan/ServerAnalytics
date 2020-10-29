package me.reratos.serveranalytics.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "server")
public class ServerModel extends GenericModel<Long> {

    @Column(length = 40, nullable = false)
    private String name;

    @Type(type = "uuid-char")
    @Column(length = 36, nullable = false)
    private UUID serverUUID;

    @Column(length = 30, nullable = false)
    private String version;

    @Column(nullable = false)
    private Integer maxPlayers;

    @Column(nullable = false)
    private Boolean onlineMode;

    @Column(length = 40, nullable = false)
    private String hostName;

    @Column(length = 30, nullable = false)
    private String localAddress;

    @Column(nullable = false)
    private Integer port;

    @Column(length = 30, nullable = false)
    private String externalAddress;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public Boolean isOnlineMode() {
        return onlineMode;
    }

    public void setOnlineMode(boolean onlineMode) {
        this.onlineMode = onlineMode;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getExternalAddress() {
        return externalAddress;
    }

    public void setExternalAddress(String externalAddress) {
        this.externalAddress = externalAddress;
    }
}
