package com.example.eventvaildation.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
@Data
@AllArgsConstructor

public class Event {

  @NotEmpty(message = "id cannot be null")
  @Size(min =3 ,message = "id length must be more than 2" )
    private String id;

  @Size(min =16 ,message = "description length must be more than 15")
 @NotEmpty(message = "description cannot be null")
    private String description;


@NotNull(message = "capacity cannot be null")
@Min(value = 26 ,message = "capacity length must be more than 25")
@Pattern(regexp = "[0-9]+", message="the capacity must be number")
    private int capacity;


    @Future(message = " the start date must be in the present or future")
    @JsonFormat(pattern = "yyyy-MM-dd" )
    private Date startDate;

   @FutureOrPresent(message = "the end date must be in the present or future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

}
