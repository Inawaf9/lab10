package com.nawaf.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Title required")
    @Size(min = 4)
    @Column(nullable = false)
    private String title;

    @NotEmpty(message = "Description required")
    @Column(nullable = false)
    private String description;

    @NotEmpty(message = "Location required")
    @Column(nullable = false)
    private String location;

    @NotEmpty(message = "salary required")
    @Positive(message = "Salary must be positive number")
    @Column(nullable = false)
    private Double salary;

    private Date postingDate;
}
