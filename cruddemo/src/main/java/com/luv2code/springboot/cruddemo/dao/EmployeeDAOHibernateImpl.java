package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;
@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	
	private EntityManager entityMan;
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		entityMan = entityManager;
	}

	
	//@Transactional
	@Override
	public List<Employee> findAll() {
		
		//getHiberSession
		Session currSession = entityMan.unwrap(Session.class);
		
		
		// create a query
		Query<Employee> theQuery = currSession.createQuery("from Employee",Employee.class);
		
		
		List<Employee> employees = theQuery.getResultList();
		
		
		return employees;
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		
		//getHiberSession
				Session currSession = entityMan.unwrap(Session.class);
				
		//Get Employee
				Employee employee  = currSession.get(Employee.class, id);
				
		return employee;
	}

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		
		//getHiberSession
		Session currSession = entityMan.unwrap(Session.class);
		
		//save the session adding the employee input to DB
		currSession.saveOrUpdate(employee);
		
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		//getHiberSession
		Session currSession = entityMan.unwrap(Session.class);
		
		// delete obj using PK
		Query theQuery = 
				currSession.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId",id);
		
		theQuery.executeUpdate();
				
		
	}

	
}
