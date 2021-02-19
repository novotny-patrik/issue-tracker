package com.np.issue.tracker.role;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Set<Role> getRolesForNewPerson() {
        return findByName(Role.ROLE_USER).map(Set::of).orElseGet(Set::of);
    }
}
