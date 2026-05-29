package com.example.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.entity.Employee;
import com.example.service.EmployeeService;

import lombok.AllArgsConstructor;

//@RestController
@Controller
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService service;

	//login -
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	//dashboard - getAll Employeee 
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		List<Employee> employees = service.getAll();
		model.addAttribute("employees", employees);
		return "dashboard";
	}
	
	
	//get allEmployeeList
		@GetMapping("/user/dashboard")
		public String getAllEmployees(Model model) {
		    model.addAttribute("employees", service.getAll());
		    return "dashboard";
		}

	//register
	@GetMapping("/public/register")
	public String registerPage(Model model) {
		model.addAttribute("employee", new Employee());
		return "register";
	}

	//submit register
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee emp) {
		service.registerEmployee(emp);
		return "redirect:/login";
	}

	//admin delete 
	@GetMapping("/admin/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
	    service.deleteUser(id);
	    return "redirect:/user/dashboard";
	}

	//get one employee
	@GetMapping("/public/getOne/{id}")
	public String getOneEmployee(@PathVariable Long id,
	                             Model model) {
	    Employee emp = service.getOne(id);
	    model.addAttribute("employee", emp);
	    return "employee-details";
	}

	//Update Page Method
	@GetMapping("/admin/updatePage/{id}")
	public String updatePage(@PathVariable Long id,
	                         Model model) {
	    Employee emp = service.getOne(id);
	    model.addAttribute("employee", emp);
	    return "update-employee";
	}
	
	//Update Form Submit
	@PostMapping("/admin/updateEmployee/{id}")
	public String updateEmployee(@PathVariable Long id,
	                             @ModelAttribute Employee emp) {
	    service.updateEmployee(emp, id);
	    return "redirect:/user/dashboard";
	}

}
