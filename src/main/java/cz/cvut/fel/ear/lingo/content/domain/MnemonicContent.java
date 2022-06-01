package cz.cvut.fel.ear.lingo.content.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MnemonicContent extends AbstractContent{

    public MnemonicContent(Long id, Long creatorId) {
        super(id, creatorId);
    }

    private String mnemonic;
}
