package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    public void run (String...args){
        Department department1 = new Department();
        department1.setDepartmentName("Operations");
        departmentRepository.save(department1);

        Department department2 = new Department();
        department2.setDepartmentName("Accounting");
        departmentRepository.save(department2);

        Department department3 = new Department();
        department3.setDepartmentName("IT");
        departmentRepository.save(department3);

        Department department4 = new Department();
        department4.setDepartmentName("Sales");
        departmentRepository.save(department4);

        Department department5 = new Department();
        department5.setDepartmentName("Human Resources");
        departmentRepository.save(department5);

        Department department6 = new Department();
        department6.setDepartmentName("Marketing");
        departmentRepository.save(department6);

        Employee admin = new Employee(
                "admin",
                "admin@sterlingsolutions.com",
                "admin",
                "Admin",
                "Administrator",
                "Operations Manager",
                true);
        admin.setDepartment(department1);
        Role adminRole = new Role("admin","ROLE_ADMIN");
        employeeRepository.save(admin);
        roleRepository.save(adminRole);


        Employee mc = new Employee(
                "mc",
                "mc@sterlingsolutions.com",
                "mc",
                "MC",
                "User",
                "Accounting Specialist",
                true);
        mc.setDepartment(department2);
        Role mcRole = new Role("mc", "ROLE_USER");
        employeeRepository.save(mc);
        roleRepository.save(mcRole);

    }


}