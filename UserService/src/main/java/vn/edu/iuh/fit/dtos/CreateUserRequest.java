package vn.edu.iuh.fit.dtos;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String username;
    private String email;
    private String password;
    private String role;
}
