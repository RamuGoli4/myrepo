package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.spring.modal.Employee;
import com.spring.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public List<Employee> findByCarrierCode(String carrierCode) {
		System.out.println("findByCarrierCode called");
		List<Employee> employees = employeeRepository.findByCarrierCode(carrierCode);
		return employees;
	}

	public List<Employee> retrieveEmployees(Employee filter) {
		System.out.println("retrieveEmployees called");
		List<Employee> employees = employeeRepository.findAll(new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				System.out.println("toPredicate called");
				List<Predicate> predicates = new ArrayList<>();

				if (filter.getCarrierCode() != null) {
					predicates.add(cb.equal(root.get("carrierCode"), filter.getCarrierCode()));
				}
				if (filter.getSubCarrierCode() != null) {
					predicates.add(cb.equal(root.get("subCarrierCode"), filter.getSubCarrierCode()));
				}

				if (filter.getGroupCode() != null) {
					predicates.add(cb.equal(root.get("groupCode"), filter.getGroupCode()));
				}
				if (filter.getSearchKey() != null) {
					predicates.add(cb.equal(root.get("searchKey"), filter.getSearchKey()));
				}

				return cb.and(predicates.toArray(new Predicate[0]));
			}
		});
		return employees;
	}

	public List<Employee> findByCriteria(Employee filter) {
		return employeeRepository.findAll(new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();

				if (filter.getCarrierCode() != null) {
					predicates.add(cb.equal(root.get("carrierCode"), filter.getCarrierCode()));
				}
				if (filter.getSubCarrierCode() != null) {
					predicates.add(cb.equal(root.get("subCarrierCode"), filter.getSubCarrierCode()));
				}

				if (filter.getGroupCode() != null) {
					predicates.add(cb.equal(root.get("groupCode"), filter.getGroupCode()));
				}
				if (filter.getSearchKey() != null) {
					predicates.add(cb.equal(root.get("searchKey"), filter.getSearchKey()));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

	public List<Employee> findByPagingCriteria(Employee filter, Pageable pageable) {
		Page page = employeeRepository.findAll(new Specification<Employee>() {
			@Override
			public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				if (filter.getCarrierCode() != null) {
					predicates.add(cb.equal(root.get("carrierCode"), filter.getCarrierCode()));
				}
				if (filter.getSubCarrierCode() != null) {
					predicates.add(cb.equal(root.get("subCarrierCode"), filter.getSubCarrierCode()));
				}

				if (filter.getGroupCode() != null) {
					predicates.add(cb.equal(root.get("groupCode"), filter.getGroupCode()));
				}
				if (filter.getSearchKey() != null) {
					predicates.add(cb.equal(root.get("searchKey"), filter.getSearchKey()));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		page.getTotalElements(); // get total elements
		page.getTotalPages(); // get total pages
		return page.getContent(); // get List of Employee
	}

}