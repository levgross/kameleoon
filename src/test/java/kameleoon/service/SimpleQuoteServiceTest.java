package kameleoon.service;

import kameleoon.dto.QuoteInDTO;
import kameleoon.dto.UserDTO;
import kameleoon.model.Quote;
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
class SimpleQuoteServiceTest {
    @Autowired
    QuoteService quoteService;
    @Autowired
    UserService userService;

    private UserDTO userDTO;
    private QuoteInDTO quoteInDTO;

    @BeforeEach
    public void init() {
        userDTO = new UserDTO("Name", "pwd", "email");
        userService.create(userDTO);
        User user = userService.findById(1).get();
        quoteInDTO = new QuoteInDTO("text", user);
    }

    @Test
    public void whenCreateQuoteThenSameFieldsAndId() {
        Quote quote = quoteService.create(quoteInDTO);
        assertThat(quote.getId()).isEqualTo(1);
        assertThat(quote.getContent()).isEqualTo(quoteInDTO.getContent());
        assertThat(quote.getUserPosted()).isEqualTo(quoteInDTO.getUserPosted());
    }

    @Test
    public void whenEditQuoteThenNewContent() {
        Quote quote = quoteService.create(quoteInDTO);
        quote.setContent("edited");
        assertThat(quoteService.edit(quote)).isTrue();
        assertThat(quote.getId()).isEqualTo(1);
        assertThat(quote.getContent()).isEqualTo("edited");
        assertThat(quote.getUserPosted()).isEqualTo(quoteInDTO.getUserPosted());
    }

    @Test
    public void whenDeleteQuoteThenIsEmpty() {
        Quote quote = quoteService.create(quoteInDTO);
        assertThat(quoteService.delete(quote.getId())).isTrue();
        assertThat(quoteService.findById(quote.getId())).isEmpty();
    }
}