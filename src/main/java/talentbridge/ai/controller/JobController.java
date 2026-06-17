package talentbridge.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talentbridge.ai.model.Job;
import talentbridge.ai.service.JobService;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<Job> create(@RequestBody Job job) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jobService.create(job));
    }

    @GetMapping
    public ResponseEntity<List<Job>> listAll() {
        return ResponseEntity.ok(jobService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getById(@PathVariable String id) {
        return ResponseEntity.ok(jobService.getById(id));
    }
}
