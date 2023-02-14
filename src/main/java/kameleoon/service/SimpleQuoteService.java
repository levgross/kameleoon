package kameleoon.service;

import kameleoon.dto.QuoteInDTO;
import kameleoon.dto.QuoteOutDTO;
import kameleoon.model.Quote;
import kameleoon.repository.QuoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SimpleQuoteService implements QuoteService {
    private final QuoteRepository quoteRepository;

    @Override
    @Transactional
    public Quote create(QuoteInDTO quoteInDTO) {
        Quote quote = new Quote();
        quote.setContent(quoteInDTO.getContent());
        quote.setUserPosted(quoteInDTO.getUserPosted());
        return quoteRepository.save(quote);
    }

    @Override
    @Transactional
    public boolean edit(Quote quote) {
        Optional<Quote> quoteOpt = quoteRepository.findById(quote.getId());
        if (quoteOpt.isEmpty()) {
            return false;
        } else {
            Quote quoteEdited = quoteOpt.get();
            quoteEdited.setContent(quote.getContent());
            quoteEdited.setDateOfUpdate(LocalDateTime.now());
            quoteRepository.save(quoteEdited);
            return true;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        if (quoteRepository.existsById(id)) {
            quoteRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setScore(int quoteId, boolean positive) {
        Quote quote = findById(quoteId)
                .orElseThrow(() -> new NoSuchElementException(String.format("Quote with id %s is not found", quoteId)));
        AtomicInteger score = new AtomicInteger(quote.getScore());
        if (positive) {
            quote.setScore(score.incrementAndGet());
        } else {
            quote.setScore(score.decrementAndGet());
        }
    }

    @Override
    public Optional<QuoteOutDTO> findRandom() {
        Random random = new Random();
        int size = (int) quoteRepository.count();
        Optional<Quote> quoteOpt = findById(random.nextInt(1, size + 1));
        return quoteOpt.map(quote -> new QuoteOutDTO(
                quote.getContent(),
                quote.getScore(),
                quote.getUserPosted()
        ));
    }

    @Override
    public Optional<Quote> findById(int id) {
        return quoteRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuoteOutDTO> findBest10ByScore() {
        return quoteRepository.findTop10ByOrderByScoreDesc().stream()
                .map((quote -> new QuoteOutDTO(
                        quote.getContent(),
                        quote.getScore(),
                        quote.getUserPosted())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuoteOutDTO> findWorst10ByScore() {
        return quoteRepository.findTop10ByOrderByScoreAsc().stream()
                .map((quote -> new QuoteOutDTO(
                        quote.getContent(),
                        quote.getScore(),
                        quote.getUserPosted())))
                .collect(Collectors.toList());
    }
}
