package com.example.demo.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "employee")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "last_name")
    private String lastName;

    //@NotBlank(message = "Birth_date is required") нельзя
    //@Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$",message = "Date should be valid(yyyy-mm-dd)") нельзя
    @Past(message = "incorrect date of birth")
    @Column(name = "birth_date")
    //@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date birthDate;

    @NotBlank(message = "Email is required")
    //@Email(message = "Email should be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone is required")
    @Size(min = 8, max = 19, message = "Phone number must contain at least 8 digits (no more than 18)")
    @Pattern(regexp = "^\\+[0-9]{8,19}$", message = "Phone should start with(+) and contain at least 8 digits")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Job_title is required")
    @Size(min = 2, max = 50, message = "Job title must be between 2 and 50 characters")
    @Column(name = "job_title")
    private String jobTitle;

    @NotNull(message = "Salary is required")
    @Positive(message = "Salary should be positive")
    @Column(name = "salary", nullable = true)
    private Integer salary;

    @NotNull(message = "Full_time is required")
    //@Pattern(regexp = "^(True|False)$") нельзя
    @Column(name = "full_time")
    private boolean fullTime;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public boolean isFullTime() {
        return fullTime;
    }

    public void setFullTime(boolean fullTime) {
        this.fullTime = fullTime;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Empolyee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", salary=" + salary +
                ", fullTime=" + fullTime +
                '}';
    }
}