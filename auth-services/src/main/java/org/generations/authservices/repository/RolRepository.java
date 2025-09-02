package org.generations.authservices.repository;

import org.generations.authservices.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String nombre);
}
