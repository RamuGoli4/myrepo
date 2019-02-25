package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.modal.Employee;
import com.spring.repository.EmployeeRepository;
import com.spring.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public Employee saveEmployee(@RequestBody Employee e) {
		System.out.println("saveEmployee called2 :");
		Employee employee = new Employee();
		employee.setCarrierCode(e.getCarrierCode());
		employee.setSubCarrierCode(e.getSubCarrierCode());
		employee.setGroupCode(e.getGroupCode());
		employee.setSearchKey(e.getSearchKey());
		Employee emp = employeeRepository.save(employee);
		return emp;
	}

	@RequestMapping(value = "/getMyEmployees/{carrierCode}", method = RequestMethod.GET)
	public List<Employee> getMyEmployeesByCarrierCode(@PathVariable String carrierCode) {
		System.out.println("getMyEmployeesByCarrierCode called");
		List<Employee> employees = null;
		employees = employeeService.findByCarrierCode(carrierCode);
		return employees;
	}

	@RequestMapping(value = "/getMyEmployees", method = RequestMethod.POST)
	public List<Employee> getMyEmployees(@RequestBody Employee filter) {
		System.out.println("getMyEmployees called2");
		List<Employee> employees = employeeService.findByCriteria(filter);
		System.out.println("Employees are :" + employees);
		return employees;
	}
	
	@RequestMapping(value = "/getMyEmployees2", method = RequestMethod.POST)
	public List<Employee> getMyEmployees2(@RequestBody Employee filter) {
		System.out.println("getMyEmployees called2");
		List<Employee> employees = employeeService.findByCriteria(filter);
		System.out.println("Employees are :" + employees);
		return employees;
	}


	@RequestMapping(value = "/getMyEmployees3", method = RequestMethod.POST)
	public List<Employee> getMyEmployees3(@RequestBody Employee filter) {
		System.out.println("getMyEmployees called2");
		List<Employee> employees = employeeService.findByPagingCriteria(filter, new PageRequest(0, 10));
		System.out.println("Employees are :" + employees);
		return employees;
	}

}
