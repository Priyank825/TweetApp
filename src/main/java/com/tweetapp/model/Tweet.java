package com.tweetapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Tweet {

	@Id
	@GeneratedValue
	private int id;
	private String tweetMsg;
	private String userName;
	private Date tweetTime;

	public Tweet() {
		super();
	}

	public Tweet(int id, String tweetMsg, String userName, Date tweetTime) {
		super();
		this.id = id;
		this.tweetMsg = tweetMsg;
		this.userName = userName;
		this.tweetTime = tweetTime;
	}

	public Tweet(String tweetMsg, String userName, Date tweetTime) {
		super();
		this.tweetMsg = tweetMsg;
		this.userName = userName;
		this.tweetTime = tweetTime;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", tweetMsg=" + tweetMsg + ", userName=" + userName + ", tweetTime=" + tweetTime
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTweetMsg() {
		return tweetMsg;
	}

	public void setTweetMsg(String tweetMsg) {
		this.tweetMsg = tweetMsg;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getTweetTime() {
		return tweetTime;
	}

	public void setTweetTime(Date tweetTime) {
		this.tweetTime = tweetTime;
	}

}