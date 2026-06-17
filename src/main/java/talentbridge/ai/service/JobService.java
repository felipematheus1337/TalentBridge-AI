package talentbridge.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talentbridge.ai.model.Job;
import talentbridge.ai.repository.JobRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Job create(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> listAll() {
        return jobRepository.findAll();
    }

    public Job getById(String id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }
}
