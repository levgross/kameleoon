package kameleoon.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserDTO {
    @EqualsAndHashCode.Include
    @NotBlank(message =  "Name must be not empty.")
    private String name;
    @EqualsAndHashCode.Include
    @Size(min = 3, max = 20, message = "Password must be 3-20 characters size.")
    private String password;
    @Email(message = "Email is not correct.")
    private String email;
}
