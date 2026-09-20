package com.nawaf.lab10.Controller;

import com.nawaf.lab10.Api.ApiResponse;
import com.nawaf.lab10.Model.User;
import com.nawaf.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/new")
    public ResponseEntity<?> newUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        int createState = userService.newUser(user);

        return ResponseEntity.status(201).body(new ApiResponse("Create new User Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()) return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));

        int updateCase = userService.updateUser(id, user);

        if(updateCase == 1) return ResponseEntity.status(400).body(new ApiResponse("User not found"));

        if(updateCase == 2) return ResponseEntity.status(400).body(new ApiResponse("Email already exists"));

        return ResponseEntity.status(201).body(new ApiResponse("Update User Successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        int deleteCase = userService.deleteUser(id);

        if(deleteCase == 1) return ResponseEntity.status(400).body(new ApiResponse("User not found"));

        return ResponseEntity.status(201).body(new ApiResponse("Delete User Successfully"));
    }
}