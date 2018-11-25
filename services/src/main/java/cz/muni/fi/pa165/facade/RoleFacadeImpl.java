package cz.muni.fi.pa165.facade;

import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.services.BeanMappingService;
import cz.muni.fi.pa165.dto.CreateRoleDTO;
import cz.muni.fi.pa165.dto.RoleDTO;
import cz.muni.fi.pa165.entity.Role;
import cz.muni.fi.pa165.services.RoleService;


public class RoleFacadeImpl implements RoleFacade {
    @Inject
    private RoleService roleService;

    @Inject
    private BeanMappingService mappingService;

    @Override
    public Long createRole(CreateRoleDTO newRole) {
        Role mappedRole = mappingService.mapTo(newRole, Role.class);
        roleService.create(mappedRole);
        return mappedRole.getId();
    }

    @Override
    public List<RoleDTO> getAllRole() {
        return mappingService.mapTo(roleService.findAll(), RoleDTO.class);
    }

    @Override
    public RoleDTO getRoleById(Long id) {
        return mappingService.mapTo(roleService.findById(id), RoleDTO.class);
    }
/*
    @Override
    public RoleDTO getRoleByName(String name) {
        return mappingService.mapTo(roleService.findByName(name), RoleDTO.class);
    }*/

    @Override
    public void removeRole(RoleDTO role) {
        roleService.remove(mappingService.mapTo(role, Role.class));
    }

    @Override
    public void updateRole(RoleDTO role) {
        roleService.update(mappingService.mapTo(role, Role.class));
    }

}
