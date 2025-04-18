package vn.edu.iuh.fit.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.dtos.CreateUserRequest;
import vn.edu.iuh.fit.dtos.LoginRequest;
import vn.edu.iuh.fit.dtos.UserDto;
import vn.edu.iuh.fit.models.User;
import vn.edu.iuh.fit.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(CreateUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // nên mã hoá bằng BCrypt
        user.setRole(request.getRole());

        User saved = userRepository.save(user);
        return toDto(saved);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
    public boolean login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByUsername(request.getUsername());
        return userOpt.map(user -> user.getPassword().equals(request.getPassword())).orElse(false);

    }


}
