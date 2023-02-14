package kameleoon.service;

import kameleoon.model.Quote;
import kameleoon.model.User;
import kameleoon.model.Vote;
import kameleoon.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class SimpleVoteService implements VoteService {
    private final VoteRepository voteRepository;
    private final UserService userService;
    private final QuoteService quoteService;

    @Override
    @Transactional
    public Vote create(int userId, int quoteId, boolean positive) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(String.format("User with id %s is not found", userId)));
        Quote quote = quoteService.findById(quoteId)
                .orElseThrow(() -> new NoSuchElementException(String.format("Quote with id %s is not found", quoteId)));
        Vote vote = new Vote();
        vote.setQuote(quote);
        vote.setUser(user);
        vote.setPositive(positive);
        quoteService.setScore(quoteId, positive);
        quote.getVotes().add(vote);
        return voteRepository.save(vote);
    }
}
