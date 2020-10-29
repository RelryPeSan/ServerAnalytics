package me.reratos.serveranalytics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "plugin")
public class PluginModel extends BasicModel<Long> {

    @Column(length = 30, nullable = false, unique = true)
    private String name;

    @Column(length = 30, nullable = false)
    private String version;

    @Column(length = 50, nullable = false)
    private String main;

    @Column(length = 50, nullable = false)
    private String authors;

    @Column
    private String description;

    @Column(length = 30)
    private String apiVersion;

    @Lob
    private String contentConfig;

    /* Constructors */
    public PluginModel() {
    }

    public PluginModel(String name) {
        this.name = name;
    }

    public PluginModel(String name, String version, String main, String authors) {
        this.name = name;
        this.version = version;
        this.main = main;
        this.authors = authors;
    }

    /* Getters e Setters */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getContentConfig() {
        return contentConfig;
    }

    public void setContentConfig(String contentConfig) {
        this.contentConfig = contentConfig;
    }
}
