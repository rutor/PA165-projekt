package cz.muni.fi.pa165.services;

import java.util.List;

import cz.muni.fi.pa165.entity.Users;
import cz.muni.fi.pa165.entity.Role;

public interface UserService {
    public Long create(Users user) ;
    public Users findById(Long id);
    public List<Users> findAll();
    public Users findByName(String name);
    public Users findByEmail(String email);
    public void remove(Users user);
    public boolean validatePassword(Users user);
    public void update(Users user);
    public List<Users> findAllByRole(Role role);
    public boolean authenticate(Users user, String plainPassword);
    public boolean isAdmin(Users user);
    public void create(Users user, String plainPassword);



}