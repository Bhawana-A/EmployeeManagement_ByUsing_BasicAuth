package com.example.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

	private EmployeeRepo repo;
	private PasswordEncoder encoder;
	
	public Employee registerEmployee(Employee emp) {
		if(repo.findByEmail(emp.getEmail()).isPresent()) {
			throw new RuntimeException("Employee already exits..");
		}
		
		String hashPW = encoder.encode(emp.getPassword());
		emp.setPassword(hashPW);
		return repo.save(emp);
	}
	
	
	public List<Employee> getAll(){
		return repo.findAll();
	}
	
	public void deleteUser(Long id) {
		repo.deleteById(id);
	}
	
	public Employee getOne(Long id) {
		return repo.findById(id).orElseThrow(null);
	}
	
	public Employee updateEmployee(Employee emp , Long id) {
		return repo.findById(id).map((e)->{
			e.setName(emp.getName());
			e.setEmail(emp.getEmail());
			e.setPassword(encoder.encode(emp.getPassword()));
			e.setRole(emp.getRole());
			e.setSalary(emp.getSalary());
			e.setDepartmentName(emp.getDepartmentName());
			return repo.save(e);
		}).orElseThrow(()->new RuntimeException("User not found"));
	}
	
}
