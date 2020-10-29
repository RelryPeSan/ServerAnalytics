package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.ServerModel;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class ServerDAO extends GenericDAO<ServerModel, Long> {
    public ServerDAO() {
        super(ServerModel.class);
    }

    public ServerModel findByUuid(UUID uuid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ServerModel> query = cb.createQuery(ServerModel.class);
        Root<ServerModel> from = query.from(ServerModel.class);
        TypedQuery<ServerModel> typedQuery = entityManager.createQuery(
                query.select(from)
                        .where(
                                cb.equal(from.get("serverUUID"), uuid)
                        )
        );

        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
