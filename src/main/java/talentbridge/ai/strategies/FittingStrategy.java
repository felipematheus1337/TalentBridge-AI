package talentbridge.ai.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import talentbridge.ai.dto.JobDetails;
import talentbridge.ai.llm.LLMOpenAIClient;
import talentbridge.ai.service.ICandidateService;
import talentbridge.ai.service.IJobService;

@Component
@RequiredArgsConstructor
public class FittingStrategy implements TalentStrategy<JobDetails> {

    private final ICandidateService candidateService;
    private final IJobService jobService;
    private final LLMOpenAIClient llmOpenAIClient;

    @Override
    public JobDetails execute(String candidateId, String jobId) {
        var candidate = candidateService.getById(candidateId);
        var job = jobService.getById(jobId);
        return llmOpenAIClient.evaluateFitting(candidate.getResume(), job.getDescription());
    }
}
