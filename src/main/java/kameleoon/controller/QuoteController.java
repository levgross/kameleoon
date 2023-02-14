package kameleoon.controller;

import kameleoon.dto.QuoteInDTO;
import kameleoon.dto.QuoteOutDTO;
import kameleoon.model.Quote;
import kameleoon.service.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/quote")
@AllArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @PostMapping("/")
    public Quote createQuote(@Valid @RequestBody QuoteInDTO quoteInDTO) {
        return quoteService.create(quoteInDTO);
    }

    @PutMapping("/")
    public ResponseEntity<Void> editQuote(@Valid @RequestBody Quote quote) {
        boolean status = quoteService.edit(quote);
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable int id) {
        boolean status = quoteService.delete(id);
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    @GetMapping(value = "random")
    public ResponseEntity<QuoteOutDTO> getRandom() {
        var quote = quoteService.findRandom()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Quote is not found! Check that there is at least one quote!"
                ));
               return ResponseEntity
                .ok(quote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getById(@PathVariable int id) {
        var quote = quoteService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Quote is not found! Check id!"
                ));
        return ResponseEntity
                .ok(quote);
    }

    @GetMapping(value = "best10")
    public List<QuoteOutDTO> getBest10() {
        return quoteService.findBest10ByScore();
    }

    @GetMapping(value = "worst10")
    public List<QuoteOutDTO> getWorst10() {
        return quoteService.findWorst10ByScore();
    }
}
