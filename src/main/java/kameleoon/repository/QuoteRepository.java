package kameleoon.repository;

import kameleoon.model.Quote;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {
    @EntityGraph(attributePaths = "userPosted")
    List<Quote> findTop10ByOrderByScoreAsc();

    @EntityGraph(attributePaths = "userPosted")
    List<Quote> findTop10ByOrderByScoreDesc();
}
