package cz.cvut.fel.ear.lingo.repo;

import cz.cvut.fel.ear.lingo.repo.dao.BaseDao;
import cz.cvut.fel.ear.lingo.exception.NotFoundException;
import cz.cvut.fel.ear.lingo.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

@Repository
public class TagDao extends BaseDao<Tag> {

    protected TagDao() {
        super(Tag.class);
    }

    @Override
    public Tag find(Long id) {
        try {
            return em.createQuery("SELECT t FROM Tag t WHERE t.id = :id", Tag.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NotFoundException e) {
            throw new NoResultException("Not Found");
        }
    }

    @Override
    public List<Tag> findAll() {
        try {
            return em.createQuery("SELECT t FROM Tag t", Tag.class)
                    .getResultList();
        } catch (NotFoundException e) {
            throw new NoResultException("Not Found");
        }
    }

    public Tag findByName(String name) {
        Objects.requireNonNull(name);
        try {
            return em.createQuery("SELECT t FROM Tag t WHERE t.name = :name", Tag.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NotFoundException e) {
            throw new NoResultException("Not Found");
        }
    }

    public List<Tag> findAllRelatedTag(Long id) {
        Objects.requireNonNull(id);
        try {
            return em.createQuery(
                            "SELECT t FROM Tag t JOIN t.relatedTags r WHERE r.id = :id", Tag.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (NotFoundException e) {
            throw new NoResultException("Not Found");
        }
    }
}