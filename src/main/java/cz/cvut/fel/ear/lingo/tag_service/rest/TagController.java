package cz.cvut.fel.ear.lingo.tag_service.rest;

import cz.cvut.fel.ear.lingo.flashcard_service.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.rest.RestUtils;
import cz.cvut.fel.ear.lingo.tag_service.adapter.TagAdapter;
import cz.cvut.fel.ear.lingo.tag_service.entity.TagEntity;
import cz.cvut.fel.ear.lingo.tag_service.model.TagModel;
import cz.cvut.fel.ear.lingo.user_service.security.CurrentUser;
import cz.cvut.fel.ear.lingo.user_service.security.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/tag")
@Validated
@RequiredArgsConstructor
public class TagController {

    private final TagAdapter tagAdapter;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTag(@Valid @RequestBody TagModel tagModel){
        tagAdapter.createTag(tagModel);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", tagModel.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TagModel getTag(@Valid @PathVariable Long id){
        return tagAdapter.findById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}/flashcards", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FlashcardEntity> getFlashcards(@PathVariable Long id){
        TagEntity tagEntity = getTag(id);
        if (tagEntity != null){
            return tagEntity.getFlashcardEntities().stream().filter(flashcard -> !flashcard.isRemoved()).collect(Collectors.toList());
        }
        log.info("Tag with id {} not found.", id);
        return null;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTag(@PathVariable Long id, @RequestBody TagEntity tagEntity){
        TagEntity originalTagEntity = getTag(id);
        tagService.update(originalTagEntity, tagEntity);
        log.info("{} updated.", tagEntity);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTag(@PathVariable Long id){
        TagEntity tagEntity = getTag(id);
        tagService.remove(tagEntity);
        log.info("{} deleted", tagEntity);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TagEntity> findAll(){
        return tagService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/find/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TagEntity findByName(@PathVariable String name){
        return tagService.getTagByName(name);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/findSimilarTags/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TagEntity> findSimilarTag(@PathVariable String name){
        TagEntity tagEntity = findByName(name);
        if (tagEntity != null)
            return tagEntity.getRelatedTagEntities();
        return null;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "/{id}/flashcard/{idCard}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addFlashcardToTag(@PathVariable Long id, @PathVariable Long idCard, @CurrentUser UserDetailsImpl userDetails){
        FlashcardEntity flashcardEntity = fService.findById(idCard);
        if (flashcardEntity.getCreator().getId().equals(userDetails.getId()) || userDetails.getUser().isAdmin()) {
            TagEntity tagEntity = getTag(id);
            tagService.addFlashcard(tagEntity, flashcardEntity);
            log.info("To flashcard {} was added new tag {}.", flashcardEntity, tagEntity);
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping(value = "/{id}/flashcard/{idCard}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeFlashcardToTag(@PathVariable Long id, @PathVariable Long idCard){
        FlashcardEntity flashcardEntity = fService.findById(idCard);
        TagEntity tagEntity = getTag(id);
        tagService.removeFlashcard(tagEntity, flashcardEntity);
        log.info("To flashcard {} was removed tag {}.", flashcardEntity, tagEntity);
    }
}