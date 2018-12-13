package cz.muni.fi.pa165.services;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.entity.Genre;
import cz.muni.fi.pa165.entity.Role;
import cz.muni.fi.pa165.entity.Show;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import static com.lambdaworks.codec.Base64.*;
import com.lambdaworks.crypto.SCryptUtil;

import java.util.regex.Pattern;
import java.io.IOException;

import java.lang.Exception;



import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;




import cz.muni.fi.pa165.dao.UserDao;
import cz.muni.fi.pa165.entity.Users;



@Service
public class   UserServiceImpl implements UserService{

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
    public void create(Users user, String plainPassword) {
        if (plainPassword == null) {
            throw new NullPointerException("Plain password cannot be null");
        }
        if (plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        user.setPassword(createHash(plainPassword));
        userDao.create(user);
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
    public boolean isAdmin(Users user) {
        try {
            return findById(user.getId()).getRole().getName().equals("ADMIN");
        } catch (NullPointerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new PersistenceException(ex.getMessage());
        }
    }

    @Override
    public boolean validatePassword(Users user) {
        Users userFromDao=userDao.findById(user.getId());
        //SCryptUtil.check(originalPassword,hashPassword)
        return SCryptUtil.check(user.getPassword(),userFromDao.getPassword());

    }

    private static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }

    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public boolean authenticate(Users user, String plainPassword) {
        if (plainPassword == null) {
            throw new NullPointerException("Password cannot be null");
        }
        if (plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        return validatePassword(plainPassword, user.getPassword());
    }

    @Override
    public List<Users> findAllByRole(Role role) { return userDao.findByRole(role);
    }

}
