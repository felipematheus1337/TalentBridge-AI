package talentbridge.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talentbridge.ai.dto.JobCandidateRequest;
import talentbridge.ai.model.JobCandidate;
import talentbridge.ai.repository.JobCandidateRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobCandidateService {

    private final JobCandidateRepository jobCandidateRepository;
    private final CandidateService candidateService;
    private final JobService jobService;

    public JobCandidate create(JobCandidateRequest request) {
        candidateService.getById(request.getIdCandidate());
        jobService.getById(request.getIdJob());

        JobCandidate jobCandidate = JobCandidate.builder()
                .idCandidate(request.getIdCandidate())
                .idJob(request.getIdJob())
                .build();

        return jobCandidateRepository.save(jobCandidate);
    }

    public List<JobCandidate> getAll() {
        return jobCandidateRepository.findAll();
    }
}
