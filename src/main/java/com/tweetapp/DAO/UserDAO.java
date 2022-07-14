package com.tweetapp.DAO;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

public class UserDAO {

	
   public User findByUsername(Session session,String username) {
	   User user=null;
	   try {
      Transaction t = session.beginTransaction();
      Query<User> query=session.createQuery("from User where userName= :uname1", User.class);
      query.setParameter("uname1",username);
       user = query.getSingleResult();
      t.commit();
      return user;
	   }catch(Exception E) {
		   
	   }
	   return user;
   }

public void saveUser(Session session, User u1) {
	
	 Transaction t = session.beginTransaction();
     session.save(u1);
     t.commit();
}

public List<User> getAll(Session session) {
	// TODO Auto-generated method stub
	Transaction t = session.beginTransaction();
	Query<User> query=session.createQuery("from User", User.class);
     List<User> allusers=query.getResultList();
     t.commit();
	return allusers;
}

public void resetPasswd(Session session, User ud) {
	// TODO Auto-generated method stub
	Transaction t = session.beginTransaction();
	session.saveOrUpdate(ud);
    
     t.commit();
}


	
}
