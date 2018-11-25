package cz.muni.fi.pa165.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.Users;

@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;


    @Override
    public Long create(Users user) {
        userDao.create(BcryptHashing(user));
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
    public void remove(Users user) {
        userDao.remove(user);
    }

    @Override
    public void update(Users user) {
        // change password
        if (authenticate(user,findById(user.getId()))){
                //then hashing password
            BcryptHashing(user);}
        userDao.update(user);
    }

    public boolean  authenticate(Users originalUser,Users  generatedSecuredUser){
        //boolean matched = BCrypt.checkpw(originalPassword, generatedSecuredPasswordHash);
        //boolean semaphore = BCrypt.checkpw(oldUser.getPassword(), newUser.getPassword());
        return  BCrypt.checkpw(originalUser.getPassword(), generatedSecuredUser.getPassword());
    }

    public Users  BcryptHashing(Users user){
        //BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));
        return user;

    }


}
