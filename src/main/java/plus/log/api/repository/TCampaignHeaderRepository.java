package plus.log.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import plus.log.api.entity.TCampaignHeaderEntity;
@Repository
public interface TCampaignHeaderRepository extends JpaRepository<TCampaignHeaderEntity, Long>, JpaSpecificationExecutor<TCampaignHeaderEntity> {
}
