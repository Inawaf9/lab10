package com.nawaf.lab10.Controller;

import com.nawaf.lab10.Api.ApiResponse;
import com.nawaf.lab10.Model.JobApplication;
import com.nawaf.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/job-application")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getJobApplications(){
        return ResponseEntity.status(200).body(jobApplicationService.getJobApplications());
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyForJob(@Valid @RequestBody JobApplication jobApplication, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        int applyCase = jobApplicationService.applyForJob(jobApplication);

        return ResponseEntity.status(201).body(new ApiResponse("Apply For Job Successfully"));
    }

    @DeleteMapping("/withdraw/{id}")
    public ResponseEntity<?> withdrawJobApplication(@PathVariable Integer id){
        int withdrawCase = jobApplicationService.withdrawJobApplication(id);

        if(withdrawCase == 1) return ResponseEntity.status(400).body(new ApiResponse("Job Application not found"));

        return ResponseEntity.status(201).body(new ApiResponse("Withdraw Job Application Successfully"));
    }
}