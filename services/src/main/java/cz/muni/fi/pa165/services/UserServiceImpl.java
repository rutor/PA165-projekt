package cz.muni.fi.pa165.services;

import java.util.List;


import javax.inject.Inject;

import org.springframework.stereotype.Service;



import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.Users;


@Service
public class UserServiceImpl implements UserService{


    @Inject
    private UserService userService;

    @Inject
    private UserDao userDao;

    @Override
    public Long create(Users user) {
        userDao.create(user);
        return user.getId();
    }


    @Override
    public Users findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<Users> findAll() {
        return userDao.findAll();
    }

    @Override
    public Users findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public Users findByEmail(String email) {
        return userDao.findByEmail(email);
    }

   @Override
    public void remove(Users user) {
        userDao.remove(user);
    }

    @Override
    public void update(Users user) {
        userDao.update((user));
    }





}
