package cz.muni.fi.pa165.services;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.dao.RoleDao;
import cz.muni.fi.pa165.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {

    @Inject
    private RoleDao roleDao;

    @Override
    public Long create(Role role) {
        roleDao.create(role);
        return role.getId();
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public void remove(Role role) {
        roleDao.remove(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

}
