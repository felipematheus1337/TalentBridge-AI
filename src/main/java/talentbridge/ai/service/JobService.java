package talentbridge.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import talentbridge.ai.model.Job;
import talentbridge.ai.repository.JobRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService implements IJobService {

    private final JobRepository jobRepository;

    @Override
    public Job create(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> listAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getById(String id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }
}
