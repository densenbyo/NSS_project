package cz.cvut.fel.ear.lingo.tag.adapter;

import cz.cvut.fel.ear.lingo.tag.domain.Tag;

import java.util.List;

public interface TagRepositoryAdapter {

    void createTag(Tag tag);

    Tag getTagById(Long id);

    List<Tag> getAllTags();

    Tag getTagByName(String name);

    void updateTag(Long id, Tag tag);

    void deleteTag(Long id);

    List<Tag> findSimilarTags(String name);

    void addFlashcardToTag(Long idTag, Long idFlashcard);

    void removeFlashcardFromTag(Long idTag, Long idFlashcard);
}
