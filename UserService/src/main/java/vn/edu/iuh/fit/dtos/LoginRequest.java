package vn.edu.iuh.fit.dtos;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
