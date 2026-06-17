package talentbridge.ai.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import talentbridge.ai.service.ICandidateService;
import talentbridge.ai.service.IJobService;

@Component
@RequiredArgsConstructor
public class ResumeStrategy implements TalentStrategy<String> {

    private final ICandidateService candidateService;
    private final IJobService jobService;

    @Override
    public String execute(String candidateId, String jobId) {

        var candidate = candidateService.getById(candidateId);
        var job = jobService.getById(jobId);

        return "mocked-resume";
    }
}
