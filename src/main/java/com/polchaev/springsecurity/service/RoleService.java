package com.polchaev.springsecurity.service;



import com.polchaev.springsecurity.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> allRoles();

    void add (Role role);

    void edit (Role role);

    void delete (Role role);

    Role getById(Long id);

    Role getByName (String name);
}
