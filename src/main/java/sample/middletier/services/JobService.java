package sample.middletier.services;

import org.springframework.stereotype.Service;
import sample.middletier.entities.Job;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService {
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    public List<Job> getAllJobs() {
        return jobs;
    }

    public Job getJobById(Long id) {
        return jobs.stream()
                .filter(job -> job.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Job createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
        return job;
    }

    public Job updateJob(Long id, Job updatedJob) {
        Job existingJob = getJobById(id);
        if (existingJob == null) {
            return null;
        }
        existingJob.setPosition(updatedJob.getPosition());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setLocation(updatedJob.getLocation());
        return existingJob;
    }

    public boolean deleteJob(Long id) {
        Job jobToRemove = getJobById(id);
        if (jobToRemove == null) {
            return false;
        }
        jobs.remove(jobToRemove);
        return true;
    }
}
