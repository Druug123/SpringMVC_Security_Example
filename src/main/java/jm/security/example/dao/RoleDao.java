package jm.security.example.dao;

import jm.security.example.model.Role;
import java.util.List;

public interface RoleDao {

    Role getRoleById(long id);

    List<Role> getAllRoles();

    void saveRole(Role role);
}
