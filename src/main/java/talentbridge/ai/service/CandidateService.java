package talentbridge.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talentbridge.ai.model.Candidate;
import talentbridge.ai.repository.CandidateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public Candidate create(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    public List<Candidate> listAll() {
        return candidateRepository.findAll();
    }

    public Candidate getById(String id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + id));
    }
}
