package org.generations.authservices.repository;

import org.generations.authservices.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Integer> {
    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
