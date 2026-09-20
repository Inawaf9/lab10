package com.nawaf.lab10.Repository;

import com.nawaf.lab10.Model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Integer> {

    JobPost getJobPostById(Integer id);
}
