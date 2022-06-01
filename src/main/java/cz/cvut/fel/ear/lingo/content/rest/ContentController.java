package cz.cvut.fel.ear.lingo.content.rest;

import cz.cvut.fel.ear.lingo.content.adapter.ContentAdapter;
import cz.cvut.fel.ear.lingo.content.model.*;
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
@RequestMapping("/content")
@Validated
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
public class ContentController {

    private final ContentAdapter contentAdapter;

    @PostMapping(value = "/audio", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveAudio(@Valid @RequestBody AudioContentModel contentModel,
                                          @CurrentUser UserDetailsImpl userDetails) {
        contentAdapter.saveAudioContent(contentModel, userDetails.getUser());
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", contentModel.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/image", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveImage(@Valid @RequestBody ImageContentModel content,
                                          @CurrentUser UserDetailsImpl userDetails) {
        contentAdapter.saveImageContent(content, userDetails.getUser());
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", content.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/mnemonic", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveMnemonic(@Valid @RequestBody MnemonicContentModel content,
                                             @CurrentUser UserDetailsImpl userDetails) {
        contentAdapter.saveMnemonicContent(content, userDetails.getUser());
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", content.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PostMapping(value = "/context", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveContext(@Valid @RequestBody ContextSentenceContentModel content,
                                            @CurrentUser UserDetailsImpl userDetails) {
        contentAdapter.saveContextSentenceContent(content, userDetails.getUser());
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/{id}", content.getId());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ContentModel> findAll(@CurrentUser UserDetailsImpl userDetails) {
        return contentAdapter.findAllContents(userDetails.getUser().getRole().toString(), userDetails.getUser().getId());
    }

    @GetMapping(value = "/audio")
    public List<ContentModel> findAllAudio(@CurrentUser UserDetailsImpl userDetails) {
        return findAll(userDetails).stream().filter(s -> s.getType().equals("AUDIO")).toList();
    }

    @GetMapping(value = "/image")
    public List<ContentModel> findAllImage(@CurrentUser UserDetailsImpl userDetails) {
        return findAll(userDetails).stream().filter(s -> s.getType().equals("IMAGE")).toList();
    }

    @GetMapping(value = "/mnemonic")
    public List<ContentModel> findAllMnemonic(@CurrentUser UserDetailsImpl userDetails) {
        return findAll(userDetails).stream().filter(s -> s.getType().equals("MNEMONIC")).toList();
    }

    @GetMapping(value = "/context")
    public List<ContentModel> findAllContext(@CurrentUser UserDetailsImpl userDetails) {
        return findAll(userDetails).stream().filter(s -> s.getType().equals("SENTENCE")).toList();
    }

    @GetMapping(value = "/{id}")
    public ContentModel findById(@Valid @PathVariable Long id, @CurrentUser UserDetailsImpl userDetails) {
        return contentAdapter.getContentById(id, userDetails.getUser().getRole().toString(), userDetails.getUser().getId());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Long id) {
        contentAdapter.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}