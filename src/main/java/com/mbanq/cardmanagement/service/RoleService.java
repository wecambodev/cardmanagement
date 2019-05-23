package com.mbanq.cardmanagement.service;

import com.mbanq.cardmanagement.model.Role;
import com.mbanq.cardmanagement.model.RoleName;
import com.mbanq.cardmanagement.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;



    public Optional<Role>  findByName(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }
}
