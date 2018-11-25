package cz.muni.fi.pa165.facade;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.services.BeanMappingService;
import cz.muni.fi.pa165.dto.CreateUserDTO;
import cz.muni.fi.pa165.dto.UserDTO;
import cz.muni.fi.pa165.entity.Users;
import cz.muni.fi.pa165.services.UserService;


public class UserFacadeImpl implements UserFacade {
    @Inject
    private UserService userService;

    @Inject
    private BeanMappingService mappingService;

    @Override
    public Long createUser(CreateUserDTO newUser) {
        Users mappedUser = mappingService.mapTo(newUser, Users.class);
        userService.create(mappedUser);
        return mappedUser.getId();
    }

    @Override
    public List<UserDTO> getAllUser() {
        return mappingService.mapTo(userService.findAll(), UserDTO.class);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return mappingService.mapTo(userService.findById(id), UserDTO.class);
    }
/*
    @Override
    public UserDTO getUserByName(String name) {
        return mappingService.mapTo(userService.findByName(name), UserDTO.class);
    }*/

    @Override
    public void removeUser(UserDTO user) {
        userService.remove(mappingService.mapTo(user, Users.class));
    }

    @Override
    public void updateUser(UserDTO user) {
        userService.update(mappingService.mapTo(user, Users.class));
    }

}
