package com.medilabosolutions.dto;

import java.time.LocalDate;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PatientDto {

    @Null(message = "you don't need to specify a id")
    private Long id;

    @NotBlank(message = "lastname of patient must be not null or blank")
    @Length(max = 25, message = "lastname of patient must contain at most 25 characters")
    private String lastName;

    @NotBlank(message = "firstname of patient must be not null or blank")
    @Length(max = 25, message = "firstname of patient must contain at most 25 characters")
    private String firstName;

    @DateTimeFormat(iso = ISO.DATE) // TODO create a custom Date validator to throw a validException instead BAdRequest
    @NotNull(message = "Date must not be null")
    @Past(message = "Date input is invalid, must be before today")
    private LocalDate dateOfBirth;

    @NotBlank(message = "genre of patient must be not null or blank")
    @Length(max = 1, message = "invalid input, choose between 'M' or 'F'")
    private String genre;

    private String address;

    @Pattern(regexp = "^$|^\\d{3}-\\d{3}-\\d{4}$", message = "invalid phone number, example : 333-444-5555")
    private String phoneNumber;
}
