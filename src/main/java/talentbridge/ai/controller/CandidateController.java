package talentbridge.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talentbridge.ai.model.Candidate;
import talentbridge.ai.service.CandidateService;

import java.util.List;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<Candidate> create(@RequestBody Candidate candidate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.create(candidate));
    }

    @GetMapping
    public ResponseEntity<List<Candidate>> listAll() {
        return ResponseEntity.ok(candidateService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getById(@PathVariable String id) {
        return ResponseEntity.ok(candidateService.getById(id));
    }
}
