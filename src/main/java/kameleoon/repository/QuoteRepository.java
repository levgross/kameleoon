package kameleoon.repository;

import kameleoon.model.Quote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {
    List<Quote> findTop10ByOrderByScoreAsc();
    List<Quote> findTop10ByOrderByScoreDesc();
}
