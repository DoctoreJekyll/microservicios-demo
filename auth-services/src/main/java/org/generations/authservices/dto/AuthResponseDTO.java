package org.generations.authservices.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponseDTO {
    private String token;
    private long expiresTime;
    private String username;
    private Set<String> roles;
}
