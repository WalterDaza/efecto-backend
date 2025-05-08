package com.efecto.bar_efecto_backend.dto;

import com.efecto.bar_efecto_backend.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String name;
    private String password;
    private Role role;
    private String email;
}
