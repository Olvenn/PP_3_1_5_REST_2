package springSecurity.service;

import springSecurity.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> getRoles();

    void saveRole(Role role);

    void deleteRole(Long id);

    Optional<Role> getRoleByName(String name);
}
