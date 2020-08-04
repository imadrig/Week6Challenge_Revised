package com.example.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee_table")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username")
    @NotEmpty
    @NotNull
    private String username;

    @Column(name = "email")
    @NotEmpty
    @NotNull
    private String email;

    @Column(name = "password")
    @Size(min = 5)
    private String password;

    @Column(name = "first_name")
    @NotEmpty
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    @NotNull
    private String lastName;

    @Column(name = "job_title")
    @NotEmpty
    @NotNull
    private String jobTitle;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne
    private Department department;

    public Employee() {
    }

    public Employee(@NotEmpty @NotNull String username, @NotEmpty @NotNull String email, @Size(min = 5) String password, @NotEmpty @NotNull String firstName, @NotEmpty @NotNull String lastName, @NotEmpty @NotNull String jobTitle, boolean enabled) {
        this.username = username;
        this.email = email;
        this.setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.enabled = enabled;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void clearPassword(){
        this.password ="";
    }

}