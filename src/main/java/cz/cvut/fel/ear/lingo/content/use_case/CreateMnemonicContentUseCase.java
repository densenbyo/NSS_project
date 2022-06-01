package cz.cvut.fel.ear.lingo.content.use_case;

import cz.cvut.fel.ear.lingo.content.adapter.ContentRepositoryAdapter;
import cz.cvut.fel.ear.lingo.content.domain.MnemonicContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateMnemonicContentUseCase {

    private final ContentRepositoryAdapter adapter;

    public void execute(MnemonicContent mnemonicContent) {
        adapter.saveMnemonicContent(mnemonicContent);
    }
}
