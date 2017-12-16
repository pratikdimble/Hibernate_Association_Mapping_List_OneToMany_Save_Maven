package com.pratik.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pratik.domain.PhoneNumber;
import com.pratik.domain.User;
import com.pratik.utility.HibernateUtil;

public class OTM_DAOImpl implements OTM_DAO {
 
	@Override
	public void SaveDataUsingParents() {
		Session ses=null;
		User user=null;
		PhoneNumber ph1=null,ph2=null;
		Set<PhoneNumber> childs=null;
		//get Session
		ses=HibernateUtil.getSession();
		
		//parent obj
		 user=new User();
		 //user.setUserId(1001);
		 user.setFirstName("pratik");
		//child objs
		 ph1=new PhoneNumber();
		ph1.setPhone(999999999);
		ph1.setNumberType("office");
		ph1.setOperator("Idea");

		 ph2=new PhoneNumber();
		ph2.setPhone(88888888);
		ph2.setNumberType("home");
		ph2.setOperator("Airtel");

		childs=new HashSet<PhoneNumber>();
		childs.add(ph1); childs.add(ph2);
		//set childs to parent
		user.setPhones(childs);

		//save objs (parent to child)
		Transaction tx=null;
		try{
		 tx=ses.beginTransaction();
		   ses.persist(user);
		 tx.commit();
		 System.out.println("Objects are saved....");
		  }//try
		  catch(Exception e){
		    tx.rollback();
		    }
	}//method

	@Override
	public void loadDataUsingParent() {
		Session ses=null;
		Query query=null;
		List<User> list=null;
		Set<PhoneNumber> childs=null;
		
		//get Session
		ses=HibernateUtil.getSession();
		//create Query obj
		query=ses.createQuery("from User");
		//execute Query
		list=query.list();
		//process the Results
		for(User user:list){
			System.out.println("parent---->"+user);
			//get all childs of each parent
			childs=user.getPhones();
			System.out.println(childs.size());
			for(PhoneNumber ph:childs){
				System.out.println("child--->"+ph);
			}//for
		
			}//for
	}//method
	
	
	
	@Override
	public void addingNewChildToExistingParent() {
		Session ses=null;
		User user=null;
		Set<PhoneNumber> childs=null;
		Transaction tx=null;
		PhoneNumber ph3=null;
	   //get Session
		ses=HibernateUtil.getSession();
		//Load parent object(Existing)
		user=(User)ses.get(User.class,1001);
		//get Existing childs of a parent
		childs=user.getPhones();
		//create new Child
		ph3=new PhoneNumber();
		ph3.setPhone(77777777);
		ph3.setNumberType("personal");
		try{
			tx=ses.beginTransaction();
			  childs.add(ph3);
			tx.commit();
			System.out.println("new PhoneNumber is added to existing phoneNumbers");
		}//try
		catch(Exception e){
			tx.rollback();
		}
	}//method
	
}//class
