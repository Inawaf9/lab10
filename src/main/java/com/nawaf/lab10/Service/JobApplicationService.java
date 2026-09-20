package com.nawaf.lab10.Service;

import com.nawaf.lab10.Model.JobApplication;
import com.nawaf.lab10.Repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public List<JobApplication> getJobApplications(){
        return jobApplicationRepository.findAll();
    }

    public int applyForJob(JobApplication jobApplication){
        jobApplicationRepository.save(jobApplication);
        return 0;
    }

    public int withdrawJobApplication(Integer id){
        JobApplication foundJobApplication =
                jobApplicationRepository.findJobApplicationById(id);

        if(foundJobApplication == null) return 1;

        jobApplicationRepository.delete(foundJobApplication);
        return 0;
    }
}