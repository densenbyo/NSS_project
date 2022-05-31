package cz.cvut.fel.ear.lingo.tag_service.rest;

import cz.cvut.fel.ear.lingo.flashcard_service.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.rest.RestUtils;
import cz.cvut.fel.ear.lingo.tag_service.adapter.TagAdapter;
import cz.cvut.fel.ear.lingo.tag_service.model.TagModel;
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
@RequestMapping("/tag")
@Validated
@RequiredArgsConstructor
public class TagController {

    private final TagAdapter tagAdapter;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTag(@Valid @RequestBody TagModel tagModel) {
        tagAdapter.createTag(tagModel);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", tagModel.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TagModel getTag(@Valid @PathVariable Long id) {
        return tagAdapter.getTagById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/find/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TagModel findByName(@PathVariable String name) {
        return tagAdapter.getTagByName(name);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TagModel> findAll() {
        return tagAdapter.findAllTags();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}/flashcards", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FlashcardModel> getFlashcards(@Valid @PathVariable Long id) {
        return getTag(id) != null ? getTag(id).getFlashcardModels() : null;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updateTag(@Valid @PathVariable Long id, @Valid @RequestBody TagModel tagModel) {
        tagAdapter.updateTag(id, tagModel);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTag(@Valid @PathVariable Long id) {
        tagAdapter.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/findSimilarTags/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TagModel> findSimilarTag(@PathVariable String name) {
        return tagAdapter.findSimilarTag(name);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "/{id}/flashcard/{idCard}")
    public ResponseEntity<Void> addFlashcardToTag(@Valid @PathVariable Long id,
                                                  @Valid @PathVariable Long idCard) {
        tagAdapter.addFlashcardToTag(id, idCard);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}/flashcard/{idCard}")
    public ResponseEntity<Void> removeFlashcardToTag(@Valid @PathVariable Long id,
                                                     @Valid @PathVariable Long idCard) {
        tagAdapter.removeFlashcardFromTag(id, idCard);
        return ResponseEntity.noContent().build();
    }
}