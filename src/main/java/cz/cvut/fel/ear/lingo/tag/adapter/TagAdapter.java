package cz.cvut.fel.ear.lingo.tag.adapter;

import cz.cvut.fel.ear.lingo.tag.domain.Tag;
import cz.cvut.fel.ear.lingo.tag.facade.TagFacade;
import cz.cvut.fel.ear.lingo.tag.mapper.Tag2TagModelMapper;
import cz.cvut.fel.ear.lingo.tag.mapper.TagModel2TagMapper;
import cz.cvut.fel.ear.lingo.tag.model.TagModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class TagAdapter {

    private final TagFacade tagFacade;
    private final TagModel2TagMapper tagModel2TagMapper;
    private final Tag2TagModelMapper tag2TagModelMapper;

    public void createTag(TagModel tagModel) {
        Tag tag = tagModel2TagMapper.toTag(tagModel);
        tagFacade.createTag(tag);
    }

    public TagModel getTagById(Long id){
        Tag tag = tagFacade.findTagById(id);
        return tag2TagModelMapper.toTagModel(tag);
    }

    public List<TagModel> findAllTags() {
        List<Tag> tags = tagFacade.findAllTags();
        return tag2TagModelMapper.toTagModelList(tags);
    }

    public TagModel getTagByName(String name) {
        Tag tag = tagFacade.findTagByName(name);
        return tag2TagModelMapper.toTagModel(tag);
    }

    public void updateTag(Long tagId, TagModel tagModel){
        Tag tag = tagModel2TagMapper.toTag(tagModel);
        tagFacade.updateTag(tagId, tag);
    }

    public void deleteTag(Long id) {
        tagFacade.deleteTag(id);
    }

    public List<TagModel> findSimilarTag(String name) {
        List<Tag> tags = tagFacade.findSimilarTag(name);
        return tag2TagModelMapper.toTagModelList(tags);
    }

    public void addFlashcardToTag(Long idTag, Long idFlashcard) {
        tagFacade.addFlashcardToTag(idTag, idFlashcard);
    }

    public void removeFlashcardFromTag(Long idTag, Long idFlashcard) {
        tagFacade.removeFlashcardFromTag(idTag, idFlashcard);
    }
}
