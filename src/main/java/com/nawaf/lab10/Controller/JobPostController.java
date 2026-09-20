package com.nawaf.lab10.Controller;

import com.nawaf.lab10.Api.ApiResponse;
import com.nawaf.lab10.Model.JobPost;
import com.nawaf.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-post")
@RequiredArgsConstructor
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getJobPosts(){
        return ResponseEntity.status(200).body(jobPostService.getJobsPosts());
    }

    @PostMapping("/new")
    public ResponseEntity<?> newJobPost(@Valid @RequestBody JobPost jobPost, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        int createState = jobPostService.newJobPost(jobPost);

        return ResponseEntity.status(201).body(new ApiResponse("Create new Job Post Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateJobPost(@PathVariable Integer id, @Valid @RequestBody JobPost jobPost, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        int updateCase = jobPostService.updateJobPost(id, jobPost);

        if(updateCase == 1) return ResponseEntity.status(400).body(new ApiResponse("Job Post not found"));

        return ResponseEntity.status(201).body(new ApiResponse("Update Job Post Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteJobPost(@PathVariable Integer id){
        int deleteCase = jobPostService.deleteJobPost(id);

        if(deleteCase == 1) return ResponseEntity.status(400).body(new ApiResponse("Job Post not found"));

        return ResponseEntity.status(201).body(new ApiResponse("Delete Job Post Successfully"));
    }
}