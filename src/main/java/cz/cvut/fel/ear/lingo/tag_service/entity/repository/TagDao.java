package cz.cvut.fel.ear.lingo.tag_service.entity.repository;

import cz.cvut.fel.ear.lingo.tag_service.entity.TagEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Objects;

@Repository
public class TagDao extends BaseDao<TagEntity> {

    protected TagDao() {
        super(TagEntity.class);
    }

    @Override
    public TagEntity find(Long id) {
        try {
            return em.createQuery("SELECT t FROM TagEntity t WHERE t.id = :id", TagEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    @Override
    public List<TagEntity> findAll() {
        try {
            return em.createQuery("SELECT t FROM TagEntity t", TagEntity.class)
                    .getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public TagEntity findByName(String name) {
        Objects.requireNonNull(name);
        try {
            return em.createQuery("SELECT t FROM TagEntity t WHERE t.name = :name", TagEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }

    public List<TagEntity> findAllRelatedTag(Long id) {
        Objects.requireNonNull(id);
        try {
            return em.createQuery(
                            "SELECT t FROM TagEntity t JOIN t.relatedTagEntities r WHERE r.id = :id", TagEntity.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (RuntimeException e) {
            throw new PersistenceException(e);
        }
    }
}