package de.bamberg.voting;

import de.bamberg.voting.Model.Voter;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class MailSender {

    public boolean sendMailTo(Voter voter){
        final String username = "nazariazargul@gmail.com";
        final String password = "yourEmailPassword";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            String html = "<b>Dear student</b>,<br> We have an election for the choosing the university head, please do your vote by clicking the <a href='www.facebook.com'>click here</a><br> <b>Thank You</b>";

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("nazariazargul@gmail.com"));

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(voter.getEmail())
            );
            message.setSubject("University Voting Time");
            message.setContent(html, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Done");

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
