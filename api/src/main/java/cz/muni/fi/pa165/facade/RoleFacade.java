package cz.muni.fi.pa165.facade;

import java.util.List;


import cz.muni.fi.pa165.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dto.RoleDTO;



import java.util.List;

public interface RoleFacade {


    public Long createRole(CreateRoleDTO newRole);

    public void removeRole(Long id);

    public List<RoleDTO> getAllRole();

    public  RoleDTO getRoleById(Long id);

    public RoleDTO getRoleByName(String name);

    public void updateRole(RoleDTO role);


}