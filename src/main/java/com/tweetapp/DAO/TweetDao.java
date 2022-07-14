package com.tweetapp.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;

public class TweetDao {

	public List<Tweet> findbyUsername(Session session,String userName) {
		// TODO Auto-generated method stub
		Transaction t = session.beginTransaction();
	      Query<Tweet> query=session.createQuery("from Tweet where userName= :uname1", Tweet.class);
	      query.setParameter("uname1",userName);
	      List<Tweet> lst = query.getResultList();
	      t.commit();
	      return lst;
	}

	public void saveTweet(Session session, Tweet obj) {
		// TODO Auto-generated method stub
		java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
		 Transaction t = session.beginTransaction();
	     System.out.println("User dao"+obj);
	     obj.setTweetTime(date);
	     session.save(obj);
	     t.commit();
	}

	public List<Tweet> getAll(Session session) {
		// TODO Auto-generated method stub
		Transaction t = session.beginTransaction();
		Query<Tweet> query=session.createQuery("from Tweet", Tweet.class);
	     List<Tweet> alltweets=query.getResultList();
	     t.commit();
		return alltweets;
	}

}
