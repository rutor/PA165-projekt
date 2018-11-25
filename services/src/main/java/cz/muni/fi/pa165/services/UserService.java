package cz.muni.fi.pa165.services;

import java.util.List;

import cz.muni.fi.pa165.entity.Users;

public interface UserService {
    public Long create(Users user);
    public Users findById(Long id);
    public List<Users> findAll();
    public Users findByName(String name);
    public Users findByEmail(String email);
    public void remove(Users user);
    public void update(Users user);
}