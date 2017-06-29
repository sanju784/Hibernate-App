package com.sanju.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainApp {

	private static SessionFactory factory;

	public static void main(String[] args) {
		
		factory = new Configuration().configure().buildSessionFactory();
		
		MainApp m = new MainApp();
		m.addEmployee("sanju", 15);
		m.addEmployee("Boka", 20);

	}
	
	public void addEmployee(String name, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();
		Employee e = new Employee(name, salary);
		tx.commit();
		
	}

}
