package talentbridge.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talentbridge.ai.dto.JobCandidateRequest;
import talentbridge.ai.model.JobCandidate;
import talentbridge.ai.service.JobCandidateService;

import java.util.List;

@RestController
@RequestMapping("/job-candidates")
@RequiredArgsConstructor
public class JobCandidateController {

    private final JobCandidateService jobCandidateService;

    @PostMapping
    public ResponseEntity<JobCandidate> create(@RequestBody JobCandidateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobCandidateService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<JobCandidate>> getAll() {
        return ResponseEntity.ok(jobCandidateService.getAll());
    }
}
