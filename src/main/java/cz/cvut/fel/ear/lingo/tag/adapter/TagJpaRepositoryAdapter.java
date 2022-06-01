package cz.cvut.fel.ear.lingo.tag.adapter;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.flashcard.entity.repository.FlashcardEntityRepository;
import cz.cvut.fel.ear.lingo.flashcard.mapper.Flashcard2FlashcardEntityMapper;
import cz.cvut.fel.ear.lingo.tag.domain.Tag;
import cz.cvut.fel.ear.lingo.tag.entity.TagEntity;
import cz.cvut.fel.ear.lingo.tag.entity.repository.TagDao;
import cz.cvut.fel.ear.lingo.tag.mapper.TagEntityMapper;
import cz.cvut.fel.ear.lingo.user.entity.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class TagJpaRepositoryAdapter implements TagRepositoryAdapter {

    private final TagDao tagDao;
    private final FlashcardEntityRepository flashcardEntityRepository;
    private final Flashcard2FlashcardEntityMapper flashcard2FlashcardEntityMapper;
    private final UserEntityRepository userEntityRepository;
    private final TagEntityMapper tagEntityMapper;

    @Transactional
    @Override
    public void createTag(Tag tag) {
        List<FlashcardEntity> entityList = new ArrayList<>();
        for (Flashcard flashcard : tag.getFlashcards()) {
            var userEntityOptional = userEntityRepository.findById(flashcard.getCreatorId());
            if (userEntityOptional.isPresent()) {
                var flashcardEntity = flashcard2FlashcardEntityMapper
                        .toFlashcardEntity(flashcard, userEntityOptional.get());
                entityList.add(flashcardEntity);
            }
        }
        List<TagEntity> tagsList = new ArrayList<>();
        for (Long tagEntityId : tag.getRelatedTagsId()) {
            var tagEntity = tagDao.find(tagEntityId);
            tagsList.add(tagEntity);
        }
        TagEntity tagEntity = new TagEntity();
        tagEntity.setIsRemoved(tag.getIsRemoved());
        tagEntity.setName(tag.getName());
        tagEntity.setFlashcardEntities(entityList);
        tagEntity.setRelatedTags(tagsList);
        tagDao.persist(tagEntity);
    }

    @Transactional
    @Override
    public Tag getTagById(Long id) {
        var tagEntityDao = tagDao.find(id);
        if (tagEntityDao != null) {
            return tagEntityMapper.toTag(tagEntityDao);
        }
        log.info("Tag with id {} not found.", id);
        return null;
    }

    @Transactional
    @Override
    public List<Tag> getAllTags() {
        var tagEntities = tagDao.findAll();
        var tagEntitiesResult = tagEntities.stream().filter(tagEntity -> !tagEntity.getIsRemoved()).toList();
        return tagEntityMapper.toTagList(tagEntitiesResult);
    }

    @Transactional
    @Override
    public Tag getTagByName(String name) {
        var tagEntityDao = tagDao.findByName(name);
        if (tagEntityDao != null) {
            return tagEntityMapper.toTag(tagEntityDao);
        }
        log.info("Tag with name {} not found.", name);
        return null;
    }

    @Transactional
    @Override
    public void updateTag(Long id, Tag tag) {
        var tagEntityDao = tagDao.find(id);
        if (tagEntityDao != null) {
            List<TagEntity> tagsList = new ArrayList<>();
            for (Long tagEntityId : tag.getRelatedTagsId()) {
                var tagEntity = tagDao.find(tagEntityId);
                tagsList.add(tagEntity);
            }
            List<FlashcardEntity> entityList = new ArrayList<>();
            for (Flashcard flashcard : tag.getFlashcards()) {
                var userEntityOptional = userEntityRepository.findById(flashcard.getCreatorId());
                if (userEntityOptional.isPresent()) {
                    var flashcardEntity = flashcard2FlashcardEntityMapper
                            .toFlashcardEntity(flashcard, userEntityOptional.get());
                    entityList.add(flashcardEntity);
                }
            }
            tagEntityDao.setName(tag.getName());
            tagEntityDao.setIsRemoved(tag.getIsRemoved());
            tagEntityDao.setRelatedTags(tagsList);
            tagEntityDao.setFlashcardEntities(entityList);
            tagDao.update(tagEntityDao);
            log.info("Updates tag with id {}", id);
        } else {
            log.info("Tag with id {} not found.", id);
        }
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        var tagEntityDao = tagDao.find(id);
        if (tagEntityDao != null) {
            tagEntityDao.setIsRemoved(true);
            tagDao.update(tagEntityDao);
            log.info("Deleted tag with id {}", id);
        } else {
            log.info("Tag with id {} not found.", id);
        }
    }

    @Transactional
    @Override
    public List<Tag> findSimilarTags(String name) {
        var tagEntitiesDao = tagDao.findByName(name);
        if (tagEntitiesDao != null) {
            List<TagEntity> similarEntityTags = tagEntitiesDao.getRelatedTags();
            List<Tag> tags = new ArrayList<>();
            for (TagEntity entity : similarEntityTags) {
                tags.add(tagEntityMapper.toTag(entity));
            }
            return tags;
        } else {
            log.info("Tag with name {} not found.", name);
            return null;
        }
    }

    @Transactional
    @Override
    public void addFlashcardToTag(Long idTag, Long idFlashcard) {
        var tagEntityDao = tagDao.find(idTag);
        var flashcardEntityOptional = flashcardEntityRepository.findFlashcardEntityByIdAndRemovedIsFalse(idFlashcard);
        if (tagEntityDao == null) {
            log.info("Tag with id {} not found.", idTag);
        }
        if (flashcardEntityOptional.isEmpty()) {
            log.info("Flashcard with id {} not found.", idFlashcard);
        }
        if (tagEntityDao != null && flashcardEntityOptional.isPresent()) {
            var flashcardEntity = flashcardEntityOptional.get();
            tagEntityDao.addFlashcard(flashcardEntity);
            tagDao.update(tagEntityDao);
        }
    }

    @Transactional
    @Override
    public void removeFlashcardFromTag(Long idTag, Long idFlashcard) {
        var tagEntityDao = tagDao.find(idTag);
        var flashcardEntityOptional = flashcardEntityRepository.findFlashcardEntityByIdAndRemovedIsFalse(idFlashcard);
        if (tagEntityDao == null) {
            log.info("Tag with id {} not found.", idTag);
        }
        if (flashcardEntityOptional.isEmpty()) {
            log.info("Flashcard with id {} not found.", idFlashcard);
        }
        if (tagEntityDao != null && flashcardEntityOptional.isPresent()) {
            var flashcardEntity = flashcardEntityOptional.get();
            tagEntityDao.removeFlashcard(flashcardEntity);
            tagDao.update(tagEntityDao);
        }
    }

}