package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository

public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> findAll() {
        return (Set<Role>) entityManager.createQuery("select role from Role role", Role.class).getResultList();
    }

    @Override
    public Set<Role> findAllId (List<Long> ids) {
                String jpql = "select r from Role r where r.id in :ids";
                return new HashSet<>(entityManager.createQuery(jpql, Role.class)
                .setParameter("ids", ids)
                .getResultList());
    }
    @Override
    public void save (Role role) {
        entityManager.persist(role);
    }
    @Override
    public Role getById (Long id) {
        return entityManager.find(Role.class, id);
    }
}
