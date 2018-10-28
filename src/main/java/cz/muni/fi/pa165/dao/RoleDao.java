/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Robo
 */
package cz.muni.fi.pa165.dao;

import java.util.List;

import cz.muni.fi.pa165.entity.Role;

/**
 * Interface of Role dao
 * @author Robert Dudas
 */
public interface RoleDao {

    public void create(Role role);

    public void update(Role role);

    public void remove(Role role);

    public List<Role> findAll();

    public Role findById(Long id);


}
