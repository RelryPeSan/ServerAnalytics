package me.reratos.serveranalytics.connections.dialect;

import me.reratos.serveranalytics.connections.ServerDatabase;
import me.reratos.serveranalytics.connections.dialect.MySQL8Dialect;

import javax.persistence.EntityManagerFactory;

import static me.reratos.serveranalytics.utils.ConfigConstants.DIALECT_MYSQL8DIALECT;

public class DialectSelector {

    public static EntityManagerFactory getEntityManagerFactory(ServerDatabase sd) throws Exception {

        switch (sd.getDialect()) {
            case DIALECT_MYSQL8DIALECT:
                return MySQL8Dialect.createEntityManagerFactory(sd);

            default:
                throw new Exception("Dialect '" + sd.getDialect() + "' not exist");
        }

    }

}
