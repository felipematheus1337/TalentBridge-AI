package talentbridge.ai.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import talentbridge.ai.model.Job;

import java.util.List;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {

    List<Job> findByCompanyNameContainingIgnoreCase(String companyName);

    List<Job> findByDescriptionContainingIgnoreCase(String keyword);
}
