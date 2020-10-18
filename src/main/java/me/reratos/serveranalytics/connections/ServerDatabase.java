package me.reratos.serveranalytics.connections;

public class ServerDatabase {
    private String dialect;
    private String url;
    private int port;
    private String schema;
    private String username;
    private String passwordBase64;
    private String timeZone;

    public ServerDatabase() {}
    public ServerDatabase(String dialect, String url, int port, String schema, String username, String passwordBase64, String timeZone) {
        this.dialect = dialect;
        this.url = url;
        this.port = port;
        this.schema = schema;
        this.username = username;
        this.passwordBase64 = passwordBase64;
        this.timeZone = timeZone;
    }

    public ServerDatabase(String url, int port, String schema, String username, String passwordBase64, String timeZone) {
        this.url = url;
        this.port = port;
        this.schema = schema;
        this.username = username;
        this.passwordBase64 = passwordBase64;
        this.timeZone = timeZone;
    }

    public ServerDatabase(String url, int port, String schema, String username, String passwordBase64) {
        this.url = url;
        this.port = port;
        this.schema = schema;
        this.username = username;
        this.passwordBase64 = passwordBase64;
    }

    public ServerDatabase(String url, String schema, String username, String passwordBase64) {
        this.url = url;
        this.schema = schema;
        this.username = username;
        this.passwordBase64 = passwordBase64;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordBase64() {
        return passwordBase64;
    }

    public void setPasswordBase64(String passwordBase64) {
        this.passwordBase64 = passwordBase64;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
