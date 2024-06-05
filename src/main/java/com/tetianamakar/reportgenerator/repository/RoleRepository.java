package com.tetianamakar.reportgenerator.repository;

import com.tetianamakar.reportgenerator.entity.ERole;
import com.tetianamakar.reportgenerator.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);
}
