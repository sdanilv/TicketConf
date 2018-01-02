package ua.my.dao;

import org.springframework.stereotype.Repository;
import ua.my.model.Conference;
import ua.my.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ConferenceDAOImpl implements ConferenceDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Conference conference) {
        entityManager.merge(conference);
    }

    @Override
    public Conference findConferenceForId(long id) {
        return entityManager.find(Conference.class, id);
    }

    @Override
    public void delete(long id) {
        Conference c;

            c = entityManager.getReference(Conference.class, id);
            entityManager.remove(c);

    }

    @Override
    public List<Conference> list(Group group, int start, int count) {
        TypedQuery<Conference> query;

        if (group != null) {
            query = entityManager.createQuery("SELECT c FROM Conference c WHERE c.group = :group ORDER BY c.id DESC", Conference.class);
            query.setParameter("group", group);
        } else {
            query = entityManager.createQuery("SELECT c FROM Conference c ORDER BY c.id DESC", Conference.class);
        }

        if (start >= 0) {
            query.setFirstResult(start);
            query.setMaxResults(count);
        }

        return query.getResultList();
    }

    @Override
    public List<Conference> list(String pattern) {
        TypedQuery<Conference> query = entityManager.createQuery("SELECT c FROM Conference c WHERE c.name LIKE :pattern", Conference.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM Conference c", Long.class);
        return query.getSingleResult();
    }
}