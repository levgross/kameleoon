package kameleoon.controller;

import kameleoon.model.Vote;
import kameleoon.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vote")
@AllArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping("/{userId}/{quoteId}/{positive}")
    public Vote createVote(@PathVariable("userId") int userId,
                           @PathVariable("quoteId") int quoteId,
                           @PathVariable("positive") boolean positive) {
        return voteService.create(userId, quoteId, positive);
    }
}
