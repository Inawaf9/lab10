package com.nawaf.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Id required")
    @Size(min = 4, max = 25, message = "Name must be between 4 and 25 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Must contain letters only")
    @Column(nullable = false, length = 25)
    private String name;

    @NotEmpty(message = "Email required")
    @Email(message = "Enter valid email")
    @Column(nullable = false, unique = true)
    private String email;


    @NotEmpty(message = "Password required")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "age required")
    @Min(value = 21, message = "Age must be at least 21 years old")
    @Positive(message = "Age must be positive number")
    private Integer age;

    @NotNull(message = "Role Required")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$", message = "Role must be either JOB_SEEKER or EMPLOYER")
    @Column(
            nullable = false,
            check = @CheckConstraint(
                    constraint = "role IN ('JOB_SEEKER', 'EMPLOYER')"
            )
    )
    private String role;
}
