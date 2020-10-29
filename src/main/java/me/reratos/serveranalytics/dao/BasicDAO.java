package me.reratos.serveranalytics.dao;

import me.reratos.serveranalytics.ServerAnalytics;
import me.reratos.serveranalytics.model.BasicModel;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BasicDAO<T extends BasicModel, E> extends GenericDAO<T, E>{
    public BasicDAO(Class<T> persistentClass) {
        super(persistentClass);
    }

    @Override
    public T save(T entity) {
        entity.setServerModel(ServerAnalytics.serverModel);
        return super.save(entity);
    }

    public List<T> findAllByServerUUID() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(persistentClass);
        Root<T> from = query.from(persistentClass);
        TypedQuery<T> typedQuery = entityManager.createQuery(
                query.select(from)
                        .where(
                                cb.equal(from.get("fke_server"), ServerAnalytics.serverUUID)
                        )
        );

        try {
            return typedQuery.getResultList();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
