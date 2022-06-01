package cz.cvut.fel.ear.lingo.repo.adapter;

import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardModel2FlashcardMapper;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.flashcarddeck.mapper.FlashcardDeckModel2FlashcardDeckMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.model.FlashcardDeckModel;
import cz.cvut.fel.ear.lingo.repo.domain.Repo;
import cz.cvut.fel.ear.lingo.repo.facade.RepoFacade;
import cz.cvut.fel.ear.lingo.repo.mapper.Repo2RepoModelMapper;
import cz.cvut.fel.ear.lingo.repo.model.RepoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RepoAdapter {

    private final RepoFacade repoFacade;
    private final Repo2RepoModelMapper repo2RepoModelMapper;
    private final FlashcardModel2FlashcardMapper flashcardModel2FlashcardMapper;
    private final FlashcardDeckModel2FlashcardDeckMapper flashcardDeckModel2FlashcardDeckMapper;

    public RepoModel findRepoById(long id) {
        Repo repo = repoFacade.findRepoById(id);
        return repo2RepoModelMapper.toRepoModel(repo);
    }

    public List<RepoModel> findAllRepos() {
        List<Repo> repos = repoFacade.findAllRepo();
        return repo2RepoModelMapper.toRepoModelList(repos);
    }

    public void addFlashcardToRepo(Long repoId, FlashcardModel flashcardModel) {
        var flashcard = flashcardModel2FlashcardMapper.toFlashcard(flashcardModel);
        repoFacade.addFlashcardToRepo(repoId, flashcard);
    }

    public void removeFlashcardFromRepo(Long repoId, FlashcardModel flashcardModel) {
        var flashcard = flashcardModel2FlashcardMapper.toFlashcard(flashcardModel);
        repoFacade.removeFlashcardToRepo(repoId, flashcard);
    }

    public void addFlashcardDeckToRepo(Long repoId, FlashcardDeckModel flashcardDeckModel) {
        var flashcardDeck = flashcardDeckModel2FlashcardDeckMapper.toFlashcardDeck(flashcardDeckModel);
        repoFacade.addFlashcardDeckToRepo(repoId, flashcardDeck);
    }

    public void removeFlashcardDecFromRepo(Long repoId, FlashcardDeckModel flashcardDeckModel) {
        var flashcardDeck = flashcardDeckModel2FlashcardDeckMapper.toFlashcardDeck(flashcardDeckModel);
        repoFacade.removeFlashcardDeckFromRepo(repoId, flashcardDeck);
    }

}