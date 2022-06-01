package cz.cvut.fel.ear.lingo.flashcard.rest;

import cz.cvut.fel.ear.lingo.flashcard.adapter.FlashcardAdapter;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.repo.adapter.RepoAdapter;
import cz.cvut.fel.ear.lingo.rest.RestUtils;
import cz.cvut.fel.ear.lingo.user.security.CurrentUser;
import cz.cvut.fel.ear.lingo.user.security.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/flashcard")
@Validated
@RequiredArgsConstructor
public class FlashcardController {

    private final FlashcardAdapter flashcardAdapter;
    private final RepoAdapter repoAdapter;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> crateFlashcard(@Valid @RequestBody FlashcardModel flashcardModel,
                                               @CurrentUser UserDetailsImpl userDetails) {
        flashcardAdapter.createFlashcard(flashcardModel, userDetails.getUser());
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", flashcardModel.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FlashcardModel getFlashcard(@Valid @PathVariable Long id) {
        return flashcardAdapter.getFlashcardById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FlashcardModel> getFlashcards() {
        return flashcardAdapter.getListOfFlashcard();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@Valid @PathVariable Long id,
                                       @Valid @RequestBody FlashcardModel flashcardModel) {
        if (!id.equals(flashcardModel.getId())) {
            log.info("The flashcard id of the current flashcard does not match id {} .", id);
            return ResponseEntity.badRequest().build();
        }
        flashcardAdapter.updateFlashcard(flashcardModel);
        log.info("Updated {}.", flashcardModel);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id) {
        flashcardAdapter.deleteFlashcard(id);
        log.info("Removed flashcard with id {}.", id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}/restore")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> restoreFlashcard(@Valid @PathVariable Long id) {
        flashcardAdapter.restoreFlashcard(id);
        log.info("Restored  flashcard with id{}.", id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/find/{word}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FlashcardModel> findByWord(@PathVariable String word) {
        return flashcardAdapter.getFlashcardsByWord(word);
    }
}