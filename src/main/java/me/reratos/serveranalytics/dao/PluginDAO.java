package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.model.PluginModel;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PluginDAO extends BasicDAO<PluginModel, Long> {

    public PluginDAO() {
        super(PluginModel.class);
    }

    public PluginModel findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PluginModel> query = cb.createQuery(PluginModel.class);
        Root<PluginModel> from = query.from(PluginModel.class);
        TypedQuery<PluginModel> typedQuery = entityManager.createQuery(
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
