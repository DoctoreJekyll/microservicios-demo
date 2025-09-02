package org.generations.playerservices.service;

import org.generations.playerservices.dto.JobDTO;
import org.generations.playerservices.dto.PlayerDTO;
import org.generations.playerservices.dto.PlayerWithJobDTO;
import org.generations.playerservices.mapper.PlayerMapper;
import org.generations.playerservices.model.Player;
import org.generations.playerservices.reporitory.PlayerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final JobClient jobClient;


    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper, JobClient jobClient) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.jobClient = jobClient;
    }

    public PlayerWithJobDTO getPlayerWithJob(Integer id) {
        Player player = playerRepository.findById(id).orElse(null);
        JobDTO jobDTO = null;
        try {
            assert player != null;
            jobDTO = jobClient.findById(player.getJobId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return new PlayerWithJobDTO(player.getId(),player.getName(),player.getRace(), player.getLevel(), jobDTO);
    }


    public List<PlayerDTO> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(playerMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    public PlayerDTO findById(Integer id) {
        Player player = playerRepository.findById(id).orElseThrow(() -> new ExpressionException("Player not found"));
        return playerMapper.mapToDTO(player);
    }

    public PlayerDTO save(PlayerDTO playerDTO) {
        Player player = playerMapper.mapToEntity(playerDTO);
        Player playerSaved = playerRepository.save(player);
        return  playerMapper.mapToDTO(playerSaved);
    }

    public void deleteById(Integer id) {
        playerRepository.deleteById(id);
    }

    public boolean existById(Integer id) {
        return playerRepository.findById(id).isPresent();
    }

    public Page<PlayerDTO> findByJobIdIgnoreCaseContaining(String job, Pageable pageable) {
        return playerRepository.findByJobIdIgnoreCaseContaining(job, pageable)
                .map(playerMapper::mapToDTO);
    }

    public Page<PlayerDTO> findByJobId(Integer jobId, Pageable pageable) {
        return playerRepository.findByJobId(jobId, pageable)
                .map(playerMapper::mapToDTO);
    }

}
