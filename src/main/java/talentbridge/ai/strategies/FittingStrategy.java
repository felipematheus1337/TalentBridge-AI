package talentbridge.ai.strategies;

import org.springframework.stereotype.Component;
import talentbridge.ai.dto.JobDetails;
import talentbridge.ai.service.ICandidateService;
import talentbridge.ai.service.IJobService;

@Component
public class FittingStrategy implements TalentStrategy<JobDetails> {

    private final ICandidateService candidateService;
    private final IJobService jobService;

    public FittingStrategy(ICandidateService candidateService, IJobService jobService) {
        this.candidateService = candidateService;
        this.jobService = jobService;
    }


    @Override
    public JobDetails execute(String candidateId, String jobId) {
        return null;
    }
}
