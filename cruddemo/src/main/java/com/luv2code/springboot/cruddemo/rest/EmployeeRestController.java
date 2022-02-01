package com.luv2code.springboot.cruddemo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	// ALL CRUD OPERATIONS GO IN HERE.
	// USES SERVICE TO CONNECT WITH DATA ACCESS OBJECTS. 
	// THIS USES HIBERNATE TO INTERACT WITH DB.

	private EmployeeService theEmployeeService;
	//quick n dirty inject DAO
	@Autowired
	public EmployeeRestController(EmployeeService employeeService) {
		theEmployeeService=employeeService;
	}


	//expose "/employees" and return list of employees
	@CrossOrigin(origins = {"http://localhost:8080","http://127.0.0.1:5500/"})
	@GetMapping("/employee")
	public List<Employee> findAll(){
		return theEmployeeService.findAll();
	}


	// add mapping for @GetMapping ("/employee/{employeeId}")
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping ("/employee/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Optional<Employee> employee = Optional.of( theEmployeeService.findById(employeeId));
		if(employee.isPresent()) {
			return employee.get();
		}
		else{throw new RuntimeException();}
	}

	// add mapping for @GetMapping ("/employee/{employeeId}")
	@PostMapping ("/employee")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		theEmployee.setId(0);
		theEmployeeService.save(theEmployee);
		return theEmployee;

	}
	
	// add mapping for PUT /employees - update existing employee
	
		@PutMapping("/employee")
		public Employee updateEmployee(@RequestBody Employee theEmployee) {
			
			theEmployeeService.save(theEmployee);
			
			return theEmployee;
		}
		
		// add mapping for DELETE /employees/{employeeId} - delete employee
		
		@DeleteMapping("/employee/{employeeId}")
		public String deleteEmployee(@PathVariable int employeeId) {
			
			Employee tempEmployee = theEmployeeService.findById(employeeId);
			
			// throw exception if null
			
			if (tempEmployee == null) {
				throw new RuntimeException("Employee id not found - " + employeeId);
			}
			
			theEmployeeService.deleteById(employeeId);
			
			return "Deleted employee id - " + employeeId;
		}



}
