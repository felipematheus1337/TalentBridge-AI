package talentbridge.ai.service;

import talentbridge.ai.model.Job;

import java.util.List;

public interface IJobService {
    Job create(Job job);

    List<Job> listAll();

    Job getById(String id);
}
