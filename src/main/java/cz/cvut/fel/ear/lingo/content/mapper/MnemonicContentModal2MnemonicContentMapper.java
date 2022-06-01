package cz.cvut.fel.ear.lingo.content.mapper;

import cz.cvut.fel.ear.lingo.content.domain.MnemonicContent;
import cz.cvut.fel.ear.lingo.content.model.MnemonicContentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MnemonicContentModal2MnemonicContentMapper {

    public MnemonicContent toMnemonicContent(MnemonicContentModel mnemonicContentModel) {
        if (mnemonicContentModel == null)
            return null;
        MnemonicContent mnemonicContent = new MnemonicContent(mnemonicContentModel.getId(), mnemonicContentModel.getCreatorId());
        mnemonicContent.setMnemonic(mnemonicContentModel.getMnemonic());
        return mnemonicContent;
    }
}
