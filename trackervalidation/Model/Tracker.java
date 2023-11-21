package com.example.trackervalidation.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Tracker {
    @NotEmpty(message = "id cannot be null")
    @Size(min =3 ,message = "id length must be more than 2" )
    private String id;


    @Size(min =9 ,message = "title length must be more than 8")
    @NotEmpty(message = "title cannot be null")
    private String title;


    @Size(min =16 ,message = "description length must be more than 15")
    @NotEmpty(message = "description cannot be null")
    private String description;


   @NotEmpty(message = "status cannot be null")
   @Pattern(regexp = "^[Started -inProgress - Completed]{1}$" ,message = "The status must be Started, In Progress, or Complete")
    private String status;


    @Size(min =7 ,message = "company name length must be more than 6")
    @NotEmpty(message = "company name cannot be null")
    private String companyName;
}
