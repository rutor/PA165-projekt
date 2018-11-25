package cz.muni.fi.pa165.facade;

import java.util.List;

//import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.dto.CreateUserDTO;
import cz.muni.fi.pa165.dto.UserDTO;



public interface UserFacade {


    public Long createUser(CreateUserDTO newUser);

    public void removeUser(UserDTO role);

    public List<UserDTO> getAllUser();


    public UserDTO getUserById(Long id);

   // public UserDTO findByEmail(String email);

    public void updateUser(UserDTO role);


}