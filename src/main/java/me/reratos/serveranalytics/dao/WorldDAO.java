package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.WorldModel;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class WorldDAO extends BasicDAO<WorldModel, Long> {

    public WorldDAO() {
        super(WorldModel.class);
    }

    public WorldModel findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<WorldModel> query = cb.createQuery(WorldModel.class);
        Root<WorldModel> from = query.from(WorldModel.class);
        TypedQuery<WorldModel> typedQuery = entityManager.createQuery(
                query.select(from)
                .where(
                        cb.equal(from.get("name"), name)
                )
        );

        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
