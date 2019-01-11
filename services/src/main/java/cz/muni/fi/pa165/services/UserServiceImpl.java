package cz.muni.fi.pa165.services;

import java.math.BigInteger;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import cz.muni.fi.pa165.services.PasswordUtils;
import cz.muni.fi.pa165.entity.Role;
import cz.muni.fi.pa165.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.services.PasswordUtils;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import static com.lambdaworks.codec.Base64.*;
import static com.lambdaworks.crypto.PBKDF.pbkdf2;

import com.lambdaworks.crypto.SCryptUtil;

import java.util.regex.Pattern;
import java.io.IOException;

import java.lang.Exception;

import cz.muni.fi.pa165.enums.AuthenticateUserStatus;
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
        //try {
            // Exception ex;
            if (!PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
                throw new IllegalArgumentException("Password is not valid. Your password must contain at least one lowercase character, uppercase character and digit and be at least 8 characters. ");
            }
        /*} catch(Exception ex){
            System.out.println("Password is not valid. Your password must contain at least one lowercase character, uppercase character and digit and be at least 8 characters. ");
        }*/

        //hash Password
        user.setPassword(PasswordUtils.createHash(user.getPassword()));
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
    public List<Users> findAllByRole(Role role) { return userDao.findByRole(role);
    }

    @Override
    public Enum<AuthenticateUserStatus> authenticate(Users user) {

        if (user == null) {
            return AuthenticateUserStatus.UNKNOWN_USER;
        }
        Users userFromDatabaze= userDao.findByEmail(user.getEmail());
        if (userFromDatabaze == null) {
            return AuthenticateUserStatus.UNKNOWN_USER;
        }
        if (PasswordUtils.validatePassword(user.getPassword(), userFromDatabaze.getPassword())) {
            return AuthenticateUserStatus.OK;
        }

        return AuthenticateUserStatus.BAD_PASSWORD;

    }

    @Override
    public void update(Users user) {
        //control valid Password
        //try {
            // Exception ex;
            if (!PASSWORD_PATTERN.matcher(user.getPassword()).matches()) {
                throw new IllegalArgumentException("Password is not valid. Your password must contain at least one lowercase character, uppercase character and digit and be at least 8 characters. ");
            }
        /*} catch(Exception ex){
            System.out.println("Password is not valid. Your password must contain at least one lowercase character, uppercase character and digit and be at least 8 characters. ");
        }*/

        //hash Password
        user.setPassword(PasswordUtils.createHash(user.getPassword()));
        userDao.update(user);

    }


    @Override
    public boolean validatePassword(Users user) {
        Users userFromDao=userDao.findById(user.getId());
        //SCryptUtil.check(originalPassword,hashPassword)
        return SCryptUtil.check(user.getPassword(),userFromDao.getPassword());

    }

}
