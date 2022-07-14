package com.tweetapp.service;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.hibernate.Session;

import com.tweetapp.DAO.UserDAO;
import com.tweetapp.model.User;

public class UserService {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	
	
	UserDAO ud = new UserDAO();
	EmailService emailservice = new EmailService();

	public User login(Session session,String username, String password) {
		// TODO Auto-generated method stub
		User u = ud.findByUsername(session,username);
		if(u==null) {
			return null;
		}
		else if (u.getPasswd().equals(password))
		{
			return u;
		}
		else {
			return null;
		}
	}

	public void register(Session session, User u1) {
		// TODO Auto-generated method stub
	List<User> user=ud.getAll(session);
	boolean flag=false;
	for(int i=0;i<user.size();i++)
	{
		if(user.get(i).getUserName().equals(u1.getUserName()))
		{
			flag=true;
			break;
		}
	}
	if(!flag)
	{
		ud.saveUser(session,u1);
		System.out.println(ANSI_GREEN+"User already exist!!"+ANSI_RESET);
	}
	
	else
		System.out.println(ANSI_RED+"User already exist!!"+ANSI_RESET);
	}

	public List<User> getAllUsers(Session session) {
		// TODO Auto-generated method stub
		return ud.getAll(session);
	}

	public void resetPasswd(Session session, User u) {
		// TODO Auto-generated method stub
		ud.resetPasswd(session,u);
		
	}

	public boolean forgotPassword(Session session,String email) {
		// TODO Auto-generated method stub
		//Optional<User> userInfo =Optional.of(ud.findByUsername(session, email));
		User u = ud.findByUsername(session, email);
        if(u!=null){
          
            Random random =new Random();
            int otp = random.nextInt(999999);
            try {
				emailservice.sendmail(email,otp);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            u.setOtp(String.valueOf(otp));
            ud.saveUser(session, u);
            return true;
        }else {
        	System.out.println(ANSI_RED+"No user found for this email."+ANSI_RESET);
        	return false;
        }
	}

	public boolean verifyOtp(Session session,String email, String otp) {
		// TODO Auto-generated method stu
		User user=null;
		try {
	         user =  ud.findByUsername(session, email);
		}
		catch(Exception e)
		{
			System.out.println(ANSI_RED+"No user found with this id"+ANSI_RESET);
		}
	        if(user!=null) {
	        	if(user.getOtp().equalsIgnoreCase(otp))
	        	{
	        		return true;
	        	}
	        }
	        else {
	            user.setOtp(null);
	            ud.saveUser(session, user);
	            return false;
	        }
	        return false;
	    }

	public boolean changepasswd(Session session,String email, String new1) {
		// TODO Auto-generated method stub
		User user =  ud.findByUsername(session, email);
		if(user!=null) {
        	user.setPasswd(new1);
        	ud.saveUser(session, user);
        }
        else {
            
            return false;
        }
		return false;
	}
	


}
