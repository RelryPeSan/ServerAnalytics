package me.reratos.serveranalytics.connections;

import me.reratos.serveranalytics.connections.dialect.DialectSelector;

import javax.persistence.*;
import java.util.Properties;
import java.util.TimeZone;

public class Database {

    static EntityManager em = null;
    static EntityManagerFactory emf = null;

    static {
        Thread.currentThread().setContextClassLoader(Database.class.getClassLoader());
    }

    public static void initialize(String url, int port, String database, String username, String password) throws Exception {
        initialize("MySQL8Dialect", url, port, database, "auto", username, password);
    }

    public static void initialize(String dialect, String url, int port, String database, String timeZoneID,
                                  String username, String password) throws Exception {

        initialize(new ServerDatabase(dialect, url, port, database, username, password, timeZoneID));

    }

    public static void initialize(ServerDatabase serverDatabase) throws Exception {

        if(serverDatabase.getTimeZone().equalsIgnoreCase("auto")) {
            serverDatabase.setTimeZone(TimeZone.getDefault().getID());
        } else if(serverDatabase.getTimeZone().equalsIgnoreCase("default")) {
            serverDatabase.setTimeZone("UTC");
        }

        emf = DialectSelector.getEntityManagerFactory(serverDatabase);
        em = emf.createEntityManager();
    }

    public static EntityManager getConnection() {
        if(em != null) return em;

        Properties properties = new Properties();

        properties.put("javax.persistence.jdbc.url",
                "jdbc:mysql://localhost:3306/serveranalytics?useTimezone=true&serverTimezone=UTC");
        properties.put("javax.persistence.jdbc.user", "root");
        properties.put("javax.persistence.jdbc.password", "password");

        emf = Persistence.createEntityManagerFactory("MySQL8Dialect", properties);
        em = emf.createEntityManager();

        return em;
    }

    public static void closeConnection() {
        if(em != null && em.isOpen()) {
            em.close();
            em = null;
        }

        if(emf != null && emf.isOpen()) {
            emf.close();
            emf = null;
        }
    }

}
