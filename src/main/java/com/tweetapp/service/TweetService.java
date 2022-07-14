package com.tweetapp.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tweetapp.DAO.TweetDao;
import com.tweetapp.model.Tweet;

public class TweetService {

	TweetDao td = new TweetDao();
	public void saveTweet(Session session, Tweet obj) {
		// TODO Auto-generated method stub
		td.saveTweet(session,obj);
		
	}
	public List<Tweet> getallbyusername(Session session,String userName) {
		// TODO Auto-generated method stub
		List<Tweet> mytweets= td.findbyUsername(session,userName);
		return mytweets;
	}
	public List<Tweet> getAlltweets(Session session) {
		// TODO Auto-generated method stub
		return td.getAll(session);
		
	}

}
