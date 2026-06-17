package talentbridge.ai.service;

import talentbridge.ai.model.Candidate;

import java.util.List;

public interface ICandidateService {
    Candidate create(Candidate candidate);

    List<Candidate> listAll();

    Candidate getById(String id);
}
