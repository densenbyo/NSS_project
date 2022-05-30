package cz.cvut.fel.ear.lingo.statistic_service.rest;

import cz.cvut.fel.ear.lingo.statistic_service.adapter.StatisticAdapter;
import cz.cvut.fel.ear.lingo.statistic_service.model.StatisticModel;
import cz.cvut.fel.ear.lingo.user_service.security.CurrentUser;
import cz.cvut.fel.ear.lingo.user_service.security.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/statistics")
@Validated
@RequiredArgsConstructor
public class StatisticsController {

    private final StatisticAdapter statisticAdapter;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StatisticModel> allStatistics(){
        return statisticAdapter.findAllStatistics();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> clearStatistics(@PathVariable Long id, @CurrentUser UserDetailsImpl userDetails) {
        statisticAdapter.clearStatistic(id, userDetails.getUser().getStatisticEntity().getId(), userDetails.getUser().getRole());
        return ResponseEntity.noContent().build();
    }
}