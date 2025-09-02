package org.generations.playerservices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerWithJobDTO {
    Integer id;
    String name;
    String race;
    Integer level;

    JobDTO job;
}
