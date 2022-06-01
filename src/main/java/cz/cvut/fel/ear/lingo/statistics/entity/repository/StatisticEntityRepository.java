package cz.cvut.fel.ear.lingo.statistics.entity.repository;

import cz.cvut.fel.ear.lingo.statistics.entity.StatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticEntityRepository extends JpaRepository<StatisticEntity, Long> {
}