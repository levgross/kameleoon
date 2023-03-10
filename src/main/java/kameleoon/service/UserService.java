package kameleoon.service;

import kameleoon.dto.UserDTO;
import kameleoon.model.User;

import java.util.Optional;

public interface UserService {
    User create(UserDTO userDTO);
    Optional<User> findById(int id);
}
