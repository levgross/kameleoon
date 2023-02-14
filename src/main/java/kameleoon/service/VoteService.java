package kameleoon.service;

import kameleoon.model.Vote;

public interface VoteService {
    Vote create(int userId, int quoteId, boolean isPositive);
}
