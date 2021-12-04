package tech.homebody.homebodyuser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.homebody.homebodyuser.entity.PoseInfo;

@Repository
public interface PoseInfoRepository extends JpaRepository<PoseInfo, String>{
    PoseInfo findByName(String name);
}
