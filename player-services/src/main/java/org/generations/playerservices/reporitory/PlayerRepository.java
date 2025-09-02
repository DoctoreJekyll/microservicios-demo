package org.generations.playerservices.reporitory;

import org.generations.playerservices.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
    Page<Player> findByJobIdIgnoreCaseContaining(String Job, Pageable pageable);
    Page<Player> findByJobId(Integer jobId, Pageable pageable);
}
