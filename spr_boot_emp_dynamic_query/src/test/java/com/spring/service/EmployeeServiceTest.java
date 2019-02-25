package com.spring.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.modal.Employee;
import com.spring.repository.EmployeeRepository;

import ch.qos.logback.core.joran.conditional.ThenAction;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;

	@Mock
	private EmployeeRepository employeeRepositoryMock;

	@Mock
	private Predicate predicate;

	@Mock
	private CriteriaBuilder criteriaBuilderMock;

	@Mock
	private CriteriaQuery criteriaQueryMock;

	@Mock
	private Root<Employee> employeeRootMock;
	
	@Mock
	private Specification<Employee> specification;
	
	@Mock
	private Pageable pageable;

	@Mock
	private Page page;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
		employeeService = null;
	}

	@Test
	public void testFindByCarrierCodeSuccess() {
		Employee employee = new Employee();
		List<Employee> list = new ArrayList<>();
		list.add(employee);
		when(employeeRepositoryMock.findByCarrierCode("carrierCode")).thenReturn(list);
		employeeService.findByCarrierCode("carrierCode");
	}
	
	@Test
	public void testRetrieveEmployeesSuccess() {
		Employee employee = new Employee();
		List<Employee> list = new ArrayList<>();
		list.add(employee);
		
		when(employeeRepositoryMock.findAll(Mockito.<Specification>any())).thenAnswer(new Answer() {
	         public Object answer(InvocationOnMock invocation) {
	             Object[] args = invocation.getArguments();
	             Specification arg = (Specification)args[0];
	             return arg.toPredicate(employeeRootMock, criteriaQueryMock, criteriaBuilderMock);
	         }
	     });     
	    
		when(employeeRepositoryMock.findAll(specification)).thenReturn(list);
		employeeService.retrieveEmployees(employee);
	}
	
	@Test
	public void testFindByPagingCriteriaSuccess() {
		Employee employee = new Employee();
		List<Employee> expected = new ArrayList<>();
		Page foundPage = new PageImpl<Employee>(expected);

//		list.add(employee);
//		when(employeeRepositoryMock.findAll(any(Predicate.class), any(Pageable.class))).thenReturn(foundPage);

//		ArgumentCaptor<Pageable> pageSpecificationArgument = ArgumentCaptor.forClass(Pageable.class);
//		verify(employeeRepositoryMock, times(1)).findAll(any(Predicate.class), pageSpecificationArgument.capture());
//        verifyNoMoreInteractions(employeeRepositoryMock);

//		Pageable pageSpecification = pageSpecificationArgument.getValue();

		
		when(employeeRepositoryMock.findAll(Mockito.<Specification>any())).thenAnswer(new Answer() {
	         public Object answer(InvocationOnMock invocation) {
	             Object[] args = invocation.getArguments();
	             Specification arg = (Specification)args[0];
	             return arg.toPredicate(employeeRootMock, criteriaQueryMock, criteriaBuilderMock);
	         }
	     });     
	    
		when(employeeRepositoryMock.findAll(specification, pageable)).thenReturn(foundPage);
		employeeService.findByPagingCriteria(employee, pageable);
	}

}
