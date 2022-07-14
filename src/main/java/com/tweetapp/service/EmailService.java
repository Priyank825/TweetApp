package com.tweetapp.service;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
  
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
  
/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


public class EmailService {

 void sendmail(String email, int otp) throws AddressException, MessagingException, IOException {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("Enter your email address here", "Enter your passcode here");
        }
    });
     Message message = new MimeMessage(session);
     message.setFrom(new InternetAddress("Enter your email address here"));
     message.setRecipients(
             Message.RecipientType.TO,
             InternetAddress.parse(email)
     );
     message.setSubject("OTP for Tweet App Password reset");
     message.setText("Dear User,"
             + "\n\n OTP : "+otp);
    Transport.send(message);
}
}