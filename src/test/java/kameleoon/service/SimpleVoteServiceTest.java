package kameleoon.service;

import kameleoon.dto.QuoteInDTO;
import kameleoon.dto.UserDTO;
import kameleoon.model.Quote;
import kameleoon.model.User;
import kameleoon.model.Vote;
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
class SimpleVoteServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private VoteService voteService;

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
    public void whenCreateVoteThenScoreChanges() {
        Quote quote = quoteService.create(quoteInDTO);
        voteService.create(1, 1, true);
        voteService.create(1, 1, false);
        voteService.create(1, 1, true);
        assertThat(quote.getScore()).isEqualTo(1);
    }

    @Test
    public void whenCreateVoteThenAddToVotesList() {
        Quote quote = quoteService.create(quoteInDTO);
        Vote vote = voteService.create(1, 1, true);
        assertThat(quote.getVotes().get(0)).isEqualTo(vote);
    }
}