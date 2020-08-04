package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping("/departments")
    public String viewDepartments(Model model) {
        model.addAttribute("department", departmentRepository.findAll());
        return "departments";
    }

    @RequestMapping("/admin/departments")
    public String adminViewDepartments(Model model) {
        model.addAttribute("department", departmentRepository.findAll());
        return "adminViewDepartments";
    }

    @RequestMapping("/view")
    public String viewEmployeeAndDepartments(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "view";
    }

    @RequestMapping("/employeedetails/{id}")
    public String employeeDetails(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).get();
        model.addAttribute("employee", employee);
        return "employeeDetails";
    }

    @RequestMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "admin";
    }

    @RequestMapping("/admin/addDepartment")
        public String addDepartment(Model model){
        model.addAttribute("department", new Department());
        return "addDepartment";
    }

    @PostMapping("/admin/processDepartment")
    public String processDepartment(@ModelAttribute Department department){
        departmentRepository.save(department);
        return "redirect:/departments";
    }

    @RequestMapping("/admin/updateDepartment/{id}")
    public String updateDepartment(@PathVariable("id") Long id, Model model){
        model.addAttribute("department", departmentRepository.findById(id).get());
        return "addDepartment";
    }

    @GetMapping("/admin/addEmployee")
    public String addEmployee (Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "addEmployee";
    }

    @PostMapping("/admin/processAddEmployee")
    public String processRegistrationPage(
            @Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            employee.clearPassword();
            model.addAttribute("employee", employee);
            return "addEmployee";
        } else {
            model.addAttribute("employee", employee);
            model.addAttribute("message", "Changes completed Successfully");
            employeeRepository.save(employee);

            Role role = new Role(employee.getUsername(), "ROLE_USER");
            roleRepository.save(role);
            return "index";
        }
    }

    @RequestMapping("/admin/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") Long id, Model model){
        model.addAttribute("employee", employeeRepository.findById(id).get());
        model.addAttribute("departments", departmentRepository.findAll());
        return "addEmployee";
    }

}
