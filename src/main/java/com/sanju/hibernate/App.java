package com.sanju.hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App 
{
	private static SessionFactory factory;
    public static void main( String[] args )
    {
        
        Configuration con = new Configuration().configure().addAnnotatedClass(Employee.class);
        
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
        
        factory = con.buildSessionFactory(reg);
        
        addEmployees();
        
        listEmployees();
        
        listEmployeeWithGreaterSalary();

    }
    
    public static void addEmployees() {
        
        Session session = factory.openSession();
        session.beginTransaction();
        Random r = new Random();
        
        for (int i = 0; i < 50; i++) {
        	Employee e = new Employee();
        	e.setName("Employee" + i);
        	e.setSalary(r.nextInt(1000));
        }
        session.close();
    }
    
    public static void listEmployees() {
    	Session session = factory.openSession();
    	session.beginTransaction();
    	Criteria c = session.createCriteria(Employee.class);
    	List employees = c.list();
    	for (Iterator i = employees.iterator(); i.hasNext();) {
    		Employee employee = (Employee)i.next();
    		System.out.print("Name: " + employee.getName());
    		System.out.println("Salary: " + employee.getSalary());
    	}
    	session.close();
    }
    
    public static void listEmployeeWithGreaterSalary() {
    	Session session = factory.openSession();
        Criteria c = session.createCriteria(Employee.class);
        c.add(Restrictions.gt("salary", 200));
        List employees = c.list();
    	for (Iterator i = employees.iterator(); i.hasNext();) {
    		Employee employee = (Employee)i.next();
    		System.out.print("Name: " + employee.getName());
    		System.out.println("Salary: " + employee.getSalary());
    	}
        session.close();
    }
}
