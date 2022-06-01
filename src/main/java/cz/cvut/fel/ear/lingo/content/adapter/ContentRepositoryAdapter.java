package cz.cvut.fel.ear.lingo.content.adapter;

import cz.cvut.fel.ear.lingo.content.domain.*;

import java.util.List;

public interface ContentRepositoryAdapter {

    void saveAudioContent(AudioContent audioContent);

    void saveImageContent(ImageContent imageContent);

    void saveMnemonicContent(MnemonicContent mnemonicContent);

    void saveContextSentenceContent(ContextSentenceContent contextContent);

    List<AbstractContent> getListOfContent();

    AbstractContent findById(Long id);

    void deleteContent(Long id);
}