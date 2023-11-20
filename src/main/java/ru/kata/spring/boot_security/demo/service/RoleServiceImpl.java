package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao, UserDao userDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public Set<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
        roleDao.save(role);
    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return roleDao.getById(id);
    }

    @Override
    @Transactional
    public Set<Role> findAllRoleId(List<Long> ids) {
        return roleDao.findAllId(ids);
    }
}