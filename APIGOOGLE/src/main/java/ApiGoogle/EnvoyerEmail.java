package ApiGoogle;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoyerEmail {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("uti1.ent1@gmail.com", "Welcome1Welcome1");
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("uti1.ent1@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("ricohohoh@gmail.com"));
            message.setSubject("Sujet du message");
            message.setText("Corps du message");

            Transport.send(message);

            System.out.println("Message envoyé");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

