package cz.cvut.fel.ear.lingo.dao;

import cz.cvut.fel.ear.lingo.exception.NotFoundException;
import cz.cvut.fel.ear.lingo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }


    @Override
    public List<User> findAll() {
        try {
            return em.createQuery("SELECT u FROM User u WHERE NOT u.isRemoved", User.class)
                    .getResultList();
        } catch (NotFoundException e) {
            throw new NoResultException("Not Found");
        }
    }

    public User findByMail(String mail) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> u = cq.from(User.class);
            cq.where(
                    cb.like(
                            u.get("mail"),
                            mail
                    )
            );
            return em.createQuery(cq).getSingleResult();
        } catch (NotFoundException e) {
            throw new NoResultException("Not Found");
        }
    }

    public User findByUsername(String username) {
        try {
            return em.createNamedQuery("User.findByUsername", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NotFoundException e) {
            throw new NoResultException("Not Found");
        }
    }

    public User findById(Long id){
        try {
            return em.createQuery("SELECT u FROM User u WHERE NOT u.isRemoved AND u.id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NotFoundException e) {
            throw new NoResultException("Not Found");
        }
    }

    public List<User> findByAchievement(String achievement) {
        try {
            return em.createQuery(
                            "SELECT u FROM User u LEFT JOIN u.statistic.achievements a WHERE NOT u.isRemoved AND a = :achievement", User.class)
                    .setParameter("achievement", achievement)
                    .getResultList();
        } catch (NotFoundException e) {
            throw new NoResultException("Not Found");
        }
    }
}