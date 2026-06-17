package talentbridge.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import talentbridge.ai.facade.LLMFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hr")
public class HumanResourcesController {

    private final LLMFacade facade;

    @PostMapping("/generate-resume")
    public String generateResume(@RequestParam("candidateId") String id,
                                 @RequestParam("jobId") String jobId) {
        return facade.toGenerateResume(id, jobId);
    }


}
