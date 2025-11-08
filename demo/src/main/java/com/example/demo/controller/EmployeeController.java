package com.example.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Value("${job_Title}")
    private List<String> jobTitle;

    // Potrebujeme injektovať našu Service pre zamestnancov
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmloyyes(Model model) {

        // Získať zamestnancov zo služby
        List<Employee> employees = employeeService.findAll();

        // Pridať zamestnancov do modelu
        model.addAttribute("employees", employees);

        return "employee/list";
    }

    @GetMapping("/view")
    public String viewEmployee(@RequestParam("employeeId") int id, Model model) {

        // Získať zamestnanca zo služby
        Employee employee = employeeService.findById(id);

        // Nastaviť zamestnanca ako model atribút
        model.addAttribute("employee", employee);

        // Poslať na náš view
        return "employee/view";
    }

    @GetMapping("/form/add")
    public String showFormForAdd(Model model) {

        // Vytvoriť model atribút pre viazanie údajov z formulára
        Employee employee= new Employee();

        model.addAttribute("employee", employee);

        model.addAttribute("jobTitle", jobTitle);

        return "employee/form";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult bindingResult,
            Model model) {

        model.addAttribute("jobTitle", jobTitle);

        if (bindingResult.hasErrors()) {
            return "employee/form";
        }
        // Uložiť zamestnanca pomocou našej služby
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/form/update")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {
        model.addAttribute("jobTitle", jobTitle);
        // Získať zamestnanca zo služby
        Employee employee = employeeService.findById(id);

        // Nastaviť zamestnanca ako model atribút pre predvyplnenie formulára
        model.addAttribute("employee", employee);


        // Poslať na náš formulár
        return "employee/form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
       employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
}