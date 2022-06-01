package cz.cvut.fel.ear.lingo.statistics.use_case;

import cz.cvut.fel.ear.lingo.statistics.adapter.StatisticRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClearStatisticUseCase {

    private final StatisticRepositoryAdapter adapter;

    public void execute(Long id) {
        adapter.clearStatistic(id);
    }
}
