package talentbridge.ai.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import talentbridge.ai.service.CandidateService;
import talentbridge.ai.service.JobService;

@Component
@RequiredArgsConstructor
public class LLMFacade {

    private final CandidateService candidateService;
    private final JobService jobService;

    public String toGenerateResume(String candidateId,
                                   String jobId) {
    }
}
