package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.spring.modal.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
	List<Employee> findByCarrierCode(String carrierCode);
}