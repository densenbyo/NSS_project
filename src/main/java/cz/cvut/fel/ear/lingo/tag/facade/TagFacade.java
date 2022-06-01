package cz.cvut.fel.ear.lingo.tag.facade;

import cz.cvut.fel.ear.lingo.tag.domain.Tag;
import cz.cvut.fel.ear.lingo.tag.use_case.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagFacade {

    private final CreateTagUseCase createTagUseCase;
    private final GetTagByIdUseCase getTagByIdUseCase;
    private final GetListTagsUseCase getListTagsUseCase;
    private final GetTagByNameUseCase getTagByNameUseCase;
    private final UpdateTagUseCase updateTagUseCase;
    private final DeleteTagUseCase deleteTagUseCase;
    private final GetSimilarTagsUseCase getSimilarTagsUseCase;
    private final AddFlashcardToTagUseCase addFlashcardToTagUseCase;
    private final RemoveFlashcardFromTagUseCase removeFlashcardFromTagUseCase;

    public void createTag(Tag tag) {
        createTagUseCase.execute(tag);
    }

    public Tag findTagById(Long id) {
        return getTagByIdUseCase.execute(id);
    }

    public List<Tag> findAllTags() {
        return getListTagsUseCase.execute();
    }

    public Tag findTagByName(String name) {
        return getTagByNameUseCase.execute(name);
    }

    public void updateTag(Long id, Tag tag) {
        updateTagUseCase.execute(id, tag);
    }

    public void deleteTag(Long id) {
        deleteTagUseCase.execute(id);
    }

    public List<Tag> findSimilarTag(String name) {
        return getSimilarTagsUseCase.execute(name);
    }

    public void addFlashcardToTag(Long idTag, Long idFlashcard) {
        addFlashcardToTagUseCase.execute(idTag, idFlashcard);
    }

    public void removeFlashcardFromTag(Long idTag, Long idFlashcard) {
        removeFlashcardFromTagUseCase.execute(idTag, idFlashcard);
    }
}
