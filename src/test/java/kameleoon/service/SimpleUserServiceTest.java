package kameleoon.service;

import kameleoon.DTO.UserDTO;
import kameleoon.model.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SimpleUserServiceTest {
    @Autowired
    private UserService userService;
    private UserDTO userDTO;

    @BeforeEach
    public void init() {
        userDTO = new UserDTO("Name", "pwd", "email");
    }

    @Test
    public void whenCreateUserThenSameFieldsAndId() {
        userService.create(userDTO);
        User user = userService.findById(1).get();
        assertThat(user.getId()).isEqualTo(1);
        assertThat(user.getName()).isEqualTo(userDTO.getName());
        assertThat(user.getPassword()).isEqualTo(userDTO.getPassword());
        assertThat(user.getEmail()).isEqualTo(userDTO.getEmail());
    }
}