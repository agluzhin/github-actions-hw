package agluzhin.github_actions_hw.controllers;

import agluzhin.github_actions_hw.entities.User;
import agluzhin.github_actions_hw.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
        Map<String, Integer> userTestData = Map.of(
                "Alex", 22,
                "Ben", 35,
                "John", 50
        );
        userTestData.forEach((name, age) -> {
                    User user = new User(name, age);
                    userRepository.addUser(user.getId(), user);
                }
        );
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userRepository.getAllUsers());
    }
}
