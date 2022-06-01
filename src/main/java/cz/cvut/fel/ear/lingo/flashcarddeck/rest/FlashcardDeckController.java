package cz.cvut.fel.ear.lingo.flashcarddeck.rest;

import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.flashcarddeck.adapter.FlashcardDeckAdapter;
import cz.cvut.fel.ear.lingo.flashcarddeck.model.FlashcardDeckModel;
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
@RequestMapping("/flashcardDeck")
@Validated
@RequiredArgsConstructor
public class FlashcardDeckController {

    private final FlashcardDeckAdapter flashcardDeckAdapter;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> crateFlashcardDeck(@RequestBody FlashcardDeckModel flashcardDeckModel,
                                                   @CurrentUser UserDetailsImpl userDetails) {
        flashcardDeckAdapter.saveFlashcardDeck(flashcardDeckModel, userDetails.getUser());
        log.debug("Created {} in user's repo with user id{}.", flashcardDeckModel, userDetails.getUser().getRepo().getId());
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", flashcardDeckModel.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FlashcardDeckModel> getFlashcardDecks(@CurrentUser UserDetailsImpl userDetails) {
        return flashcardDeckAdapter.findAllFlashcardDecks(userDetails.getUser().getRole());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public FlashcardDeckModel getFlashcardDeck(@Valid @PathVariable Long id,
                                               @CurrentUser UserDetailsImpl userDetails) {
        return flashcardDeckAdapter.findFlashcardDeckById(id, userDetails.getUser().getRole());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateFlashcardDeck(@Valid @PathVariable Long id,
                                                    @Valid @RequestBody FlashcardDeckModel flashcardDeckModel,
                                                    @CurrentUser UserDetailsImpl userDetails) {
        flashcardDeckAdapter.updateFlashcardDeck(id, userDetails.getUser(), flashcardDeckModel);
        log.info("User with id{} Updated {}.", userDetails.getId(), flashcardDeckModel);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteFlashcardDeck(@Valid @PathVariable Long id) {
        flashcardDeckAdapter.deleteFlashcardDeck(id);
        log.info("Removed flashcardDeck with id{}.", id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}/restore")
    public ResponseEntity<Void> restoreFlashcardDeck(@Valid @PathVariable Long id) {
        flashcardDeckAdapter.restoreFlashcardDeck(id);
        log.info("Restored flashcardDeck with id{}.", id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "/{id}/addFlashcard")
    public ResponseEntity<Void> addCard(@Valid @PathVariable Long id,
                                        @RequestBody FlashcardModel flashcardModel) {
        flashcardDeckAdapter.addFlashcardToFlashcardDeck(id, flashcardModel);
        log.debug("Card {} added into deck with id {}.", flashcardModel, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}/removeFlashcard")
    public ResponseEntity<Void> removeCard(@PathVariable Long id,
                                           @RequestBody FlashcardModel flashcardModel) {
        flashcardDeckAdapter.removeFlashcardToFlashcardDeck(id, flashcardModel);
        log.debug("Card {} removed from deck with id {}.", flashcardModel, id);
        return ResponseEntity.noContent().build();
    }
}