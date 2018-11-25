package cz.muni.fi.pa165.services;

import java.util.List;

import cz.muni.fi.pa165.entity.Role;

public interface RoleService {
    public Long create(Role role);
    public Role findById(Long id);
    public List<Role> findAll();
    public Role findByName(String name);
    public void remove(Role role);
    public void update(Role role);
}