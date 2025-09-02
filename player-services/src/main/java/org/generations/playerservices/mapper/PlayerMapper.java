package org.generations.playerservices.mapper;


import org.generations.playerservices.dto.PlayerDTO;
import org.generations.playerservices.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {

    //Exponemos al cliente, esto al uso es un objeto construido de tipo characterDTO
    //Es como si tuvieramos un CharacterDTO dto;
    public PlayerDTO mapToDTO (Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setRace(player.getRace());
        dto.setLevel(player.getLevel());
        dto.setJobId(player.getId());
        return  dto;
    }

    public Player mapToEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setName(playerDTO.getName());
        player.setRace(playerDTO.getRace());
        player.setLevel(playerDTO.getLevel());

        player.setJobId(playerDTO.getJobId());
        return player;
    }
}
