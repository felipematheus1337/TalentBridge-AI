package talentbridge.ai.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import talentbridge.ai.model.Candidate;

import java.util.List;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {

    List<Candidate> findBySkillsContaining(String skill);

    List<Candidate> findByNameContainingIgnoreCase(String name);
}
