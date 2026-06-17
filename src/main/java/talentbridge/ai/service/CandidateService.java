package talentbridge.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talentbridge.ai.model.Candidate;
import talentbridge.ai.repository.CandidateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateService implements ICandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public Candidate create(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    @Override
    public List<Candidate> listAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Candidate getById(String id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with id: " + id));
    }
}
