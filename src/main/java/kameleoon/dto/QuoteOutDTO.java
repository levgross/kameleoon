package kameleoon.dto;

import kameleoon.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteOutDTO {
    @NotBlank(message = "Text must be not empty.")
    @Size(max = 1000, message = "Text is too long.")
    private String content;
    private int score;
    private User userPosted;
}
