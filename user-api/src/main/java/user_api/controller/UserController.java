package user_api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_api.dto.LoginRequest;
import user_api.entity.User;
import user_api.service.UserService;
import user_api.dto.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {


    private final UserService userService;
    private final Environment env;

    public UserController(UserService userService, Environment env) {
        this.userService = userService;
        this.env = env;
    }

    private String getAppName() {
        String name = env.getProperty("spring.application.name");
        return name != null ? name : "application";
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user, HttpServletRequest request) {
        User created = userService.createUser(user);
        ApiResponse<User> resp = new ApiResponse<>(true, "User created", created, getAppName(), request.getRequestURI());
        return new ResponseEntity<>(resp, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<User> getUserByNameAndPassword(@PathVariable String username) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/by-username/{username}")
    public ResponseEntity<Void> deleteUserByName(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {

        log.info("Login Request: {}", loginRequest);
        return userService
                .login(loginRequest.getEmail(), loginRequest.getPassword())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

}
