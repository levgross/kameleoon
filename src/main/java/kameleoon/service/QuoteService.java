package kameleoon.service;

import kameleoon.dto.QuoteInDTO;
import kameleoon.dto.QuoteOutDTO;
import kameleoon.model.Quote;

import java.util.List;
import java.util.Optional;

public interface QuoteService {
    Quote create(QuoteInDTO quoteInDTO);
    boolean edit(Quote quote);
    boolean delete(int id);
    void setScore(int quoteId, boolean isPositive);
    Optional<QuoteOutDTO> findRandom();
    Optional<Quote> findById(int id);
    List<QuoteOutDTO> findBest10ByScore();
    List<QuoteOutDTO> findWorst10ByScore();
}
