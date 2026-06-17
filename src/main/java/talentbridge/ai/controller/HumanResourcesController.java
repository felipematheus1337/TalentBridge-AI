package talentbridge.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import talentbridge.ai.dto.JobCandidateRequest;
import talentbridge.ai.dto.JobDetails;
import talentbridge.ai.facade.LLMFacade;
import talentbridge.ai.utils.LLMUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr")
public class HumanResourcesController {

    private final LLMFacade facade;

    @PostMapping("/generate-resume")
    public String generateResume(@RequestBody JobCandidateRequest request) {
        return facade.execute(request.getIdCandidate(), request.getIdJob(), LLMUtils.RESUME_EVENT);
    }

    @PostMapping("/fitting")
    public JobDetails generateFitting(@RequestBody JobCandidateRequest request) {
        return facade.execute(request.getIdCandidate(), request.getIdJob(), LLMUtils.FITTING_EVENT);
    }


}
