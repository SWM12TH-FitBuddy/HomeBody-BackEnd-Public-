package tech.homebody.homebodyuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.homebody.homebodyuser.entity.Ranking;
import tech.homebody.homebodyuser.repository.custom.CustomRankRepository;

@Repository
public interface RankRepository extends JpaRepository<Ranking, Long>,
        CustomRankRepository {
    Ranking findByUserIdAndPart(String userId, String part);
}
