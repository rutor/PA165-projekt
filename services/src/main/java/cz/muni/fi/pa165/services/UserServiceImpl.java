package cz.muni.fi.pa165.services;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import static com.lambdaworks.codec.Base64.*;
import com.lambdaworks.crypto.SCryptUtil;

import java.util.regex.Pattern;
import java.io.IOException;

import java.lang.Exception;


import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.Users;



@Service
public class UserServiceImpl implements UserService{

    private final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");


    @Inject
    private UserService userService;

    @Inject
    private UserDao userDao;

    @Override
    public Long create(Users user) {

        //control valid Password
        try {
            // Exception ex;
            if (!PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
                throw new Exception("Password is not valid. Your password must contain at least one lowercase character, uppercase character and digit and be at least 8 characters. ");
            }
        }catch(Exception ex){
            System.out.println("Password is not valid. Your password must contain at least one lowercase character, uppercase character and digit and be at least 8 characters. ");
        }

        //hash Password use Scrypt
        user.setPassword(SCryptUtil.scrypt(user.getPassword(), 16, 16, 16));
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
        //control valid Password
        try {
            // Exception ex;
            if (!PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
                throw new Exception("Password is not valid. Your password must contain at least one lowercase character, uppercase character and digit and be at least 8 characters. ");
            }
        }catch(Exception ex){
            System.out.println("Password is not valid. Your password must contain at least one lowercase character, uppercase character and digit and be at least 8 characters. ");
        }

        //hash Password use Scrypt
        user.setPassword(SCryptUtil.scrypt(user.getPassword(), 16, 16, 16));
        userDao.update(user);

    }

    @Override
    public boolean validatePassword(Users user) {
        Users userFromDao=userDao.findById(user.getId());
        //SCryptUtil.check(originalPassword,hashPassword)
        return SCryptUtil.check(user.getPassword(),userFromDao.getPassword());

    }

}
