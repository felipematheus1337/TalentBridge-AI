package talentbridge.ai.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import talentbridge.ai.model.JobCandidate;

import java.util.List;

@Repository
public interface JobCandidateRepository extends MongoRepository<JobCandidate, String> {

    List<JobCandidate> findByIdCandidate(String idCandidate);

    List<JobCandidate> findByIdJob(String idJob);
}
