package jm.security.example.dao;

import jm.security.example.model.Role;
import jm.security.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;
    private BCryptPasswordEncoder passwordEncoder;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> index() {
        return entityManager.createQuery("SELECT user from User user").getResultList();
    }


    @Override
    public void create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.merge(user);
    }

    @Override
    public User show(long id) {
        return (User) entityManager.createQuery("SELECT user from User user where user.id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.merge(user);
    }

    @Override
    public void delete(long id) {
        entityManager.createQuery(
                        "DELETE FROM User user WHERE user.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public User getUserByName(String name) {
        return (User) entityManager.createQuery("SELECT user from User user where user.name=:name")
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public Role getRoleById(long id) {
        return entityManager.find(Role.class, id);
    }
}

