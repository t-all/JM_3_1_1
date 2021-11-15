package SpringBootCRUD.service;

import SpringBootCRUD.model.Role;

import java.util.List;

public interface RoleService {

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRoleById(Long id);

    List<Role> getAllRoles();

    Role getRoleByName(String role);
}
