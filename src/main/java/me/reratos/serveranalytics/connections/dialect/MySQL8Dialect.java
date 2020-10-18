package me.reratos.serveranalytics.connections.dialect;

import me.reratos.serveranalytics.connections.ServerDatabase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

public class MySQL8Dialect {
    public static EntityManagerFactory createEntityManagerFactory(ServerDatabase sd) {
        Properties properties = new Properties();

        properties.put("javax.persistence.jdbc.url",
                "jdbc:mysql://" + sd.getUrl() + ":" + sd.getPort() + "/" + sd.getSchema() +
                        "?useTimezone=true&serverTimezone=" + sd.getTimeZone());
        properties.put("javax.persistence.jdbc.user", sd.getUsername());
        properties.put("javax.persistence.jdbc.password", new String(Base64.getDecoder().decode(sd.getPasswordBase64())));

        return Persistence.createEntityManagerFactory(sd.getDialect(), properties);
    }
}
