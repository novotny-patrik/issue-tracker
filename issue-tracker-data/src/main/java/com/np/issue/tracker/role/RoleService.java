package com.np.issue.tracker.role;

import java.util.Optional;
import java.util.Set;

public interface RoleService {

    Optional<Role> findByName(String name);

    Set<Role> getRolesForNewPerson();

}
