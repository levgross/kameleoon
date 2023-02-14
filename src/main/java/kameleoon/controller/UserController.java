package kameleoon.controller;

import kameleoon.dto.UserDTO;
import kameleoon.model.User;
import kameleoon.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public User createUser(@Valid @RequestBody UserDTO userDTO) {

        return userService.create(userDTO);
    }
}
