package com.polchaev.springsecurity.dao;

import com.polchaev.springsecurity.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> allRoles() {
        return (List<Role>) entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void edit(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void delete(Role role) {
        entityManager.remove(role);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Role getById(Long id) {
        return entityManager.createQuery("from Role where id = :id", Role.class).setParameter("id", id).getSingleResult();//find(Role.class, id);
    }

    @Override
    public Role getByName(String role) {
        return entityManager.find(Role.class, role);
    }
}
