package rcp.com.automationdashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import rcp.com.automationdashboard.entity.TestCaseEntity;

@Component("rcp.com.automationdashboard.repository.TestCaseRepo")
@Repository
public interface TestCaseRepo extends JpaRepository<TestCaseEntity,Integer> {
   TestCaseEntity findByProjectId(int projectId);
}
