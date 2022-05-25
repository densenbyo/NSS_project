package cz.cvut.fel.ear.lingo.rest;

import cz.cvut.fel.ear.lingo.model.Flashcard;
import cz.cvut.fel.ear.lingo.model.FlashcardDeck;
import cz.cvut.fel.ear.lingo.model.Repo;
import cz.cvut.fel.ear.lingo.model.User;
import cz.cvut.fel.ear.lingo.security.CurrentUser;
import cz.cvut.fel.ear.lingo.security.model.UserDetailsImpl;
import cz.cvut.fel.ear.lingo.services.interfaces.RepoService;
import cz.cvut.fel.ear.lingo.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/repo")
@Validated
public class RepoController {

    private final RepoService rService;
    private final UserService userService;

    @Autowired
    public RepoController(RepoService rService, UserService userService){
        this.rService = rService;
        this.userService = userService;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Repo getRepo(@PathVariable Long id, @CurrentUser UserDetailsImpl userDetails){
        User user = userService.findById(id);
        if (user == null) {
            log.info("User with id {} not found.", id);
            return null;
        }
        if (userDetails.getUser().isUser() && !userDetails.getUser().getId().equals(id)) {
            log.info("The repository id of the authenticated user does not match id {}.", id);
            return null;
        }
        return user.getRepo();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Repo> allRepo(){
        return rService.findAll();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "{id}/flashcards", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flashcard> allCardsInRepo(@PathVariable Long id, @CurrentUser UserDetailsImpl userDetails){
        Repo repo = getRepo(id, userDetails);
        if (repo == null) return null;
        return repo.getFlashcards().stream().filter(flashcard -> !flashcard.isRemoved()).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "/{id}/flashcards")
    public void addFlashcard(@PathVariable Long id, @RequestBody Flashcard flashcard, @CurrentUser UserDetailsImpl userDetails){
        Repo repo = getRepo(id, userDetails);
        rService.addFlashcard(repo, flashcard);
        log.info("Card {} added to Repo {}.", flashcard, id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping(value = "/{id}/flashcards")
    public void removeFlashcard(@PathVariable Long id, @RequestBody Flashcard flashcard, @CurrentUser UserDetailsImpl userDetails ){
        Repo repo = getRepo(id, userDetails);
        rService.removeFlashcard(repo, flashcard);
        log.info("Card {} deleted from Repo : {}.", flashcard, id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}/flashcardDecks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FlashcardDeck> allFlashcardDecks(@PathVariable Long id, @CurrentUser UserDetailsImpl userDetails) {
        Repo repo = getRepo(id, userDetails);
        if (repo == null) return null;
        if (userDetails.getUser().isUser())
            return repo.getFlashcardDecks().stream().filter(FlashcardDeck::getIsPublic).collect(Collectors.toList());
        return repo.getFlashcardDecks().stream().filter(flashcardDeck -> !flashcardDeck.getIsRemoved()).collect(Collectors.toList());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "/{id}/flashcardDecks")
    public void addFlashcardDeck(@PathVariable Long id, @RequestBody FlashcardDeck flashcardDeck,
                                 @CurrentUser UserDetailsImpl userDetails){
        Repo repo = getRepo(id, userDetails);
        rService.addFlashcardDeck(repo, flashcardDeck);
        log.info("Deck {} added to Repo {}.", flashcardDeck, id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping(value = "/{id}/flashcardDecks")
    public void removeFlashcardDeck(@PathVariable Long id, @RequestBody FlashcardDeck flashcardDeck,
                                    @CurrentUser UserDetailsImpl userDetails ){
        Repo repo = getRepo(id, userDetails);
        rService.removeFlashcardDeck(repo, flashcardDeck);
        log.info("Deck {} deleted from Repo : {}.", flashcardDeck, id);
    }
}