package cz.cvut.fel.ear.lingo.repo_service.rest;

import cz.cvut.fel.ear.lingo.flashcard_service.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.flashcarddeck_service.model.FlashcardDeckModel;
import cz.cvut.fel.ear.lingo.repo_service.adapter.RepoAdapter;
import cz.cvut.fel.ear.lingo.repo_service.model.RepoModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/repo")
@Validated
@RequiredArgsConstructor
public class RepoController {

    private final RepoAdapter repoAdapter;

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RepoModel getRepo(@Valid @PathVariable Long id) {
        return repoAdapter.findRepoById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RepoModel> allRepo() {
        return repoAdapter.findAllRepos();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "{id}/flashcards", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FlashcardModel> allCardsInRepo(@Valid @PathVariable Long id) {
        var flashcardModels = getRepo(id).getFlashcardsModel();
        if (flashcardModels != null) {
            return flashcardModels.stream().filter(flashcardModel -> !flashcardModel.isRemoved()).toList();
        }
        return null;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}/flashcardDecks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FlashcardDeckModel> allFlashcardDecks(@Valid @PathVariable Long id) {
        var flashcardDecksModels = getRepo(id).getFlashcardDecksModel();
        if (flashcardDecksModels != null) {
            return flashcardDecksModels.stream().filter(flashcardDeckModel -> !flashcardDeckModel.getIsRemoved()).toList();
        }
        return null;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "/{id}/flashcards")
    public ResponseEntity<Void> addFlashcard(@Valid @PathVariable Long id,
                                             @RequestBody FlashcardModel flashcardModel) {
        repoAdapter.addFlashcardToRepo(id, flashcardModel);
        log.info("Card {} added to Repo {}.", flashcardModel, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping(value = "/{id}/flashcards")
    public ResponseEntity<Void> removeFlashcard(@PathVariable Long id,
                                @RequestBody FlashcardModel flashcardModel) {
        repoAdapter.removeFlashcardFromRepo(id, flashcardModel);
        log.info("Card {} deleted from Repo : {}.", flashcardModel, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "/{id}/flashcardDecks")
    public ResponseEntity<Void> addFlashcardDeck(@PathVariable Long id,
                                 @RequestBody FlashcardDeckModel flashcardDeckModel) {
        repoAdapter.addFlashcardDeckToRepo(id, flashcardDeckModel);
        log.info("Deck {} added to Repo {}.", flashcardDeckModel, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping(value = "/{id}/flashcardDecks")
    public ResponseEntity<Void> removeFlashcardDeck(@PathVariable Long id,
                                    @RequestBody FlashcardDeckModel flashcardDeckModel) {
        repoAdapter.removeFlashcardDecFromRepo(id, flashcardDeckModel);
        log.info("Deck {} deleted from Repo : {}.", flashcardDeckModel, id);
        return ResponseEntity.noContent().build();
    }
}