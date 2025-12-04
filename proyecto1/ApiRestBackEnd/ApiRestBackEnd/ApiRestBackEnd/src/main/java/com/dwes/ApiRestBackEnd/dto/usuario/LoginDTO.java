package com.dwes.ApiRestBackEnd.dto.usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String nombre;
    private String contrasena;
}
