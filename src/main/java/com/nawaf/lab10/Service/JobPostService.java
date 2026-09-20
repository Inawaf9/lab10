package com.nawaf.lab10.Service;

import com.nawaf.lab10.Model.JobPost;
import com.nawaf.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getJobsPosts(){
        return jobPostRepository.findAll();
    }

    public int newJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
        return 0;
    }

    public int updateJobPost(Integer id, JobPost jobPost){
        JobPost foundJobPost = jobPostRepository.getJobPostById(id);

        if(foundJobPost == null) return 1;

        foundJobPost.setTitle(jobPost.getTitle());
        foundJobPost.setDescription(jobPost.getDescription());
        foundJobPost.setLocation(jobPost.getLocation());
        foundJobPost.setSalary(jobPost.getSalary());
        foundJobPost.setPostingDate(jobPost.getPostingDate());

        jobPostRepository.save(foundJobPost);
        return 0;
    }

    public int deleteJobPost(Integer id){
        JobPost foundJobPost = jobPostRepository.getJobPostById(id);

        if(foundJobPost == null) return 1;

        jobPostRepository.delete(foundJobPost);
        return 0;
    }
}
