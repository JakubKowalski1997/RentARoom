package com.edu.agh.fis.RentARoom.security.user.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
/**
 * Email service used to send emails from application
 *
 *
 * @author  Jakub Kowalski
 * @version 1.0
 * @since   2020-05-30
 */
@Service
public class EmailServiceImpl implements EmailService {

    private final static Logger log = Logger.getLogger(EmailServiceImpl.class);

    @Autowired
    public JavaMailSender emailSender;

    /**
     * This method is used to send email with actual changed password.
     * @param to address which mail will be sent to
     * @param user  username
     * @param password  temporary generated password
     */
    @Async
    @Override
    public void sendTempPasswdMessage(String to, String user, String password){
        MimeMessage message = emailSender.createMimeMessage();
        String title = "[RentARoom] Remind your password";
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);

            helper.setSubject(title);
            String body = generateTempPasswdMessageBody( user,password);
            helper.setText(body, true);

        }
        catch(MessagingException e){
            log.trace("Exception in sendTempPasswdMessage: " + e.getMessage(), e);
        }

        emailSender.send(message);
    }

    /**
     * This method is used to send email to room owner
     * @param to address which mail will be sent to
     * @param messageToRoomOwner  body of message
     */
    @Async
    @Override
    public void sendMessageToRoomOwner(String to, String messageToRoomOwner) {
        MimeMessage message = emailSender.createMimeMessage();
        String title = "[RentARoom] You have got new message";
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);

            helper.setSubject(title);
            String body = generateMessageToRoomOwnerBody(messageToRoomOwner);
            helper.setText(body, true);

        }
        catch(MessagingException e){
            log.trace("Exception in sendMessageToRoomOwner: " + e.getMessage(), e);
        }

        emailSender.send(message);
    }

    private String generateTempPasswdMessageBody(String user, String password){

        return " <!DOCTYPE html> <html lang=\"en\"> <head> <meta charset=\"utf-8\" /> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" /> <meta name=\"theme-color\" content=\"#000000\" /> <link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,600,700,900|Montserrat\" rel=\"stylesheet\" /> <title>RentARoom</title> <style> * { margin: 0; padding: 0; box-sizing: border-box; } body { background-color: rgb(240, 240, 240); justify-content: center; } .mail { max-width: 60%; background-color: white; margin-top:" +
                " 30px; margin-left: 30px; } .mail header { background-image: url(\"./background.png\"); font-family: \"Montserrat\", sans-serif; font-size: 0; padding: 5px 10px; background-color: #151515; } .mail header span { color: rgb(255, 255, 0); font-weight: 600; font-size: 40px; max-width: 50%; } .mail header p { color: white; font-weight: 400; font-size: 40px; display: inline; } article { padding: 20px 20px; color: #4b4b4b; } article h1 { color: #151515; margin-top: 5px; margin-bottom: 20px; font-family: \"Montserrat\", sans-serif; font-size: 27px; text-align: left; }" +
                " article p { font-family: \"Montserrat\", sans-serif; font-size: 18px; } article p:nth-child(2) { line-height: 30px; } article p:nth-child(6) { line-height: 30px; } article a { margin-top: 30px; margin-bottom: 30px; display: inline-block; background-color: white; color: #4b4b4b; border: 2px solid #4b4b4b; padding: 10px 20px; font-family: \"Montserrat\", sans-serif; text-transform: uppercase; text-decoration: none; font-size: 13px; font-weight: 600; transition: 0.3s; } article a:hover { cursor: pointer; border: 2px solid rgb(255, 255, 0); color: rgb(255, 255, 0); } " +
                "footer { background-image: url(\"./background.png\"); font-family: \"Montserrat\", sans-serif; font-size: 15px; color: white; padding-top: 7px; padding-bottom: 7px; text-align: center; background-color: #151515; } </style> </head> <body> <div class=\"mail\"> <header> <span>RentARoom</span> <p>Site</p> </header> <article> <h1>Hi "+ user +"!</h1> <p> There was a request to change your password.<br> Your new password is: <strong>" +password + "</strong><br> If you didn't send this request, log in with this password and" +
                " please change it immediately. </p> <p>Best regards,</p> <p><strong>RentARoomTeam</strong></p> </article> <footer> <p>RentARoom official</p> </footer> </div> </body> </html> ";
    }

    private String generateMessageToRoomOwnerBody(String messageToRoomOwner){

        return " <!DOCTYPE html> <html lang=\"en\"> <head> <meta charset=\"utf-8\" /> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" /> <meta name=\"theme-color\" content=\"#000000\" /> <link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,600,700,900|Montserrat\" rel=\"stylesheet\" /> <title>RentARoom</title> <style> * { margin: 0; padding: 0; box-sizing: border-box; } body { background-color: rgb(240, 240, 240); justify-content: center; } .mail { max-width: 60%; background-color: white; margin-top:" +
                " 30px; margin-left: 30px; } .mail header { background-image: url(\"./background.png\"); font-family: \"Montserrat\", sans-serif; font-size: 0; padding: 5px 10px; background-color: #151515; } .mail header span { color: rgb(255, 255, 0); font-weight: 600; font-size: 40px; max-width: 50%; } .mail header p { color: white; font-weight: 400; font-size: 40px; display: inline; } article { padding: 20px 20px; color: #4b4b4b; } article h1 { color: #151515; margin-top: 5px; margin-bottom: 20px; font-family: \"Montserrat\", sans-serif; font-size: 27px; text-align: left; }" +
                " article p { font-family: \"Montserrat\", sans-serif; font-size: 18px; } article p:nth-child(2) { line-height: 30px; } article p:nth-child(6) { line-height: 30px; } article a { margin-top: 30px; margin-bottom: 30px; display: inline-block; background-color: white; color: #4b4b4b; border: 2px solid #4b4b4b; padding: 10px 20px; font-family: \"Montserrat\", sans-serif; text-transform: uppercase; text-decoration: none; font-size: 13px; font-weight: 600; transition: 0.3s; } article a:hover { cursor: pointer; border: 2px solid rgb(255, 255, 0); color: rgb(255, 255, 0); } " +
                "footer { background-image: url(\"./background.png\"); font-family: \"Montserrat\", sans-serif; font-size: 15px; color: white; padding-top: 7px; padding-bottom: 7px; text-align: center; background-color: #151515; } </style> </head> <body> <div class=\"mail\"> <header> <span>RentARoom</span> <p>Site</p> </header> <article> <h1>You have got new message to your room</h1><br><p style=\"border: 2px solid yellow;\"> " + messageToRoomOwner + "</p> <br> <p>Best regards,</p> <p><strong>RentARoomTeam</strong></p> </article> <footer> <p>RentARoom official</p> </footer> </div> </body> </html> ";
    }
}
