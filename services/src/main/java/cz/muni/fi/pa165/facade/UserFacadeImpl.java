package cz.muni.fi.pa165.facade;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import cz.muni.fi.pa165.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.entity.Role;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.services.BeanMappingService;
import cz.muni.fi.pa165.dto.CreateUserDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.entity.Users;
import cz.muni.fi.pa165.services.RoleService;

import cz.muni.fi.pa165.services.UserService;

@Service
@Transactional
public class UserFacadeImpl implements UserFacade {
    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Inject
    private BeanMappingService mappingService;

    @Override
    public Long createUser(CreateUserDTO newUser) {
        Users mappedUser = mappingService.mapTo(newUser, Users.class);
        mappedUser.setRole(roleService.findById(newUser.getRoleId()));
        userService.create(mappedUser);
        return mappedUser.getId() ;

    }

    @Override
    public List<UserDTO> getAllUser() {
        return mappingService.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return mappingService.mapTo(userService.findById(id), UserDTO.class);
    }

    @Override
    public UserDTO getUserByName(String name) {
        return mappingService.mapTo(userService.findByName(name), UserDTO.class);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return mappingService.mapTo(userService.findByEmail(email), UserDTO.class);
    }


    @Override
    public void removeUser(Long id) {
        userService.remove(userService.findById(id));
    }

    @Override
    public void updateUser(UserDTO user) {
        userService.update(mappingService.mapTo(user, Users.class));
    }

    @Override
    public UserDTO authenticate(UserAuthenticateDTO user) {
        Users foundUser = userService.findByEmail(user.getEmail());
        if (foundUser == null) {
            return null;
        }
        if (userService.authenticate(foundUser, user.getPassword())) {
            return mappingService.mapTo(foundUser, UserDTO.class);
        }

        return null;
    }


    @Override
    public List<UserDTO> getAllUsersByRoleId(Long roleId) {
        Role role = roleService.findById(roleId);
        return mappingService.mapTo(userService.findAllByRole(role),  UserDTO.class);
    }


}
