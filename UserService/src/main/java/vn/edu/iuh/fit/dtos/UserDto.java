package vn.edu.iuh.fit.dtos;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String role;
}
