package com.tweetapp.Main;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;

import com.tweetapp.TableUi.TableList;
import com.tweetapp.config.HibernateConfigure;
import com.tweetapp.model.Tweet;
import com.tweetapp.model.User;
import com.tweetapp.service.TweetService;
import com.tweetapp.service.UserService;

public class Main {

	static UserService us = new UserService();

	static TweetService ts = new TweetService();
	//colors of font in console
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static void main(String[] args)  {
		
		Session session = HibernateConfigure.getSessionFactory().openSession();
		User u;
		Scanner sc = new Scanner(System.in);
		while(true){
		System.out.println("Enter your choice \n1.Login\n2.Register\n3.Forgot password");
		int ch = sc.nextInt();
		switch(ch) {
		case 1: 
			 System.out.println("Enter Username:");
			 String username= sc.next();
			 System.out.println("Enter Password:");
			 String password= sc.next();	
			 u= us.login(session,username,password);
			 
			 int ch1=0;
			 if(u!=null)
			 {
				 System.out.println(ANSI_GREEN+"User is logged in Succesfully!!"+ANSI_RESET);
				do{
				ch1=0;
					System.out.println("Enter choice \n1. Post a tweet\r\n"
							+ "\r\n"
							+ "2. View my tweets\r\n"
							+ "\r\n"
							+ "3. View all tweets\r\n"
							+ "\r\n"
							+ "4. View All Users\r\n"
							+ "\r\n"
							+ "5. Reset Password\r\n"
							+ "\r\n"
							+ "6. Logout");
					 ch1 = sc.nextInt();
					switch(ch1) {
					case 1: //post a tweet
						Tweet obj = new Tweet();
						
						System.out.println("Enter message you want to tweet");
						 sc.nextLine();
						String tweetmsg = sc.nextLine();
						obj.setTweetMsg(tweetmsg);
						obj.setUserName(u.getUserName());
						ts.saveTweet(session,obj);
						break;
						
					case 2: //view my tweets
						List<Tweet> mytweets=ts.getallbyusername(session,u.getUserName());
						//mytweets.forEach(tweet->System.out.println(ANSI_CYAN+tweet+ANSI_RESET));
						TableList tl = new TableList(4, "ID", "Tweet","Time", "Username");
						// from a list
						mytweets.forEach(element -> tl.addRow(String.valueOf(element.getId()), element.getTweetMsg(),(element.getTweetTime()).toString(), element.getUserName()));
						
						tl.print();
					
						break;
						
						
					case 3: //view all tweets
						List<Tweet> alltweets= ts.getAlltweets(session);
						TableList t2 = new TableList(4, "ID", "Tweet","Time", "Username");
						// from a list
						alltweets.forEach(element -> t2.addRow(String.valueOf(element.getId()), element.getTweetMsg(),(element.getTweetTime()).toString(), element.getUserName()));
						
						t2.print();
						break;
						
					case 4: //view all users
						List<User> allusers = us.getAllUsers(session);
						//allusers.forEach(user->System.out.println(ANSI_CYAN+user+ANSI_RESET));
						TableList t3 = new TableList(4, "FirstName", "LastName","EmailId", "Gender");
						// from a list
						allusers.forEach(element -> t3.addRow(element.getFirstName(), element.getLastName(),element.getUserName(), element.getGender()));
						
						t3.print();
						
						break;
						
						
					case 5://Reset Password
							System.out.println("Enter your old password");
							String old=sc.next();
							if(old.equals(u.getPasswd())) {
							System.out.println("Enter your New password");
							String new1=sc.next();
						
							System.out.println("Confirm new password");
							String confirm=sc.next();
							if(new1.equals(confirm))
							{
								//call service to set passwd function;
								u.setPasswd(confirm);
								us.resetPasswd(session,u);
								System.out.println(ANSI_GREEN+"password reset successfully"+ANSI_RESET);
							}
							else {
								System.out.println(ANSI_RED+"Confirm password is not matching!!"+ANSI_RESET);
							}
							}else {
								System.out.println(ANSI_RED+"old password is wrong!!"+ANSI_RESET);
							}
							
					
						break;
					
					case 6://logout
						u=null;
						System.out.println(u);
						System.out.println(ANSI_GREEN+"logged out successfully"+ANSI_RESET);
						break;
							
					}
					
				
			 }while(ch1!=6);
			 }else {
				 System.out.println(ANSI_RED+"Username Or password is wrong!!"+ANSI_RESET);
			 }
				break;
		case 2: System.out.println("Enter First Name:");
				String fname= sc.next();
				System.out.println("Enter Last Name:");
				String lname= sc.next();	
				System.out.println("Enter Gender:");
				String gender= sc.next();	
				System.out.println("Enter Username:");
				String uname="";
				while(true)
				{
					 uname= sc.next();
					 Pattern pattern = Pattern.compile("^[A-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[A-Z0-9_!#$%&'*+/=?`{|}~^-]+↵\n" +
								")*@[A-Z0-9-]+(?:\\.[A-Z0-9-]+)*$", Pattern.CASE_INSENSITIVE);
						Matcher matcher = pattern.matcher(uname);	
						if(!matcher.find())
						 {
							 System.out.println(ANSI_RED+"Please enter valid email address"+ANSI_RESET);
						}
						else
						{
							break;
						}
				}
				
					 
				 System.out.println("Enter Password:");
				 String passwd= sc.next();
				User u1 = new User();
				u1.setFirstName(fname);
				u1.setLastName(lname);
				u1.setGender(gender);
				u1.setPasswd(passwd);
				u1.setUserName(uname);
				us.register(session,u1);
				break;
				
		case 3:System.out.println("Enter Email address:");
		       String email=sc.next();
		       String regex = "^(.+)@(.+)$";  
		       Pattern pattern = Pattern.compile(regex);   
		            Matcher matcher = pattern.matcher(email);  
		        if(matcher.matches()) {  
		         
		       boolean b=us.forgotPassword(session,email);
		       if(b) {
		       System.out.println("Enter OTP:");
		       String otp=sc.next();
		      boolean bool= us.verifyOtp(session,email,otp);
		      if(bool)
		      {
		    	  System.out.println("Enter your New password");
					String new1=sc.next();
				
					System.out.println("Confirm new password");
					String confirm=sc.next();
					if(new1.equals(confirm))
					{
						//call service to set passwd function;
					us.changepasswd(session,email,new1);
						System.out.println(ANSI_GREEN+"password reset successfully"+ANSI_RESET);
					}
					else {
						System.out.println(ANSI_RED+"Confirm password is not matching!!"+ANSI_RESET);
					}
		      }
		       }
		        }else {
		        	System.out.println("Email adreess is wrong");
		        }
	    

		 default:
			 System.out.println("Enter correct choice");
			 break;
		}
		}
		

	
	}

}
