package Model;

import javax.mail.internet.*;
import javax.mail.*;
import java.util.Properties;

public class EmailSender
{

    public static void sendEmail(String toAddress, String subject, String msg)
    {
        // SMTP server details
        String host = "smtp.gmail.com";
        int port = 587;
        String username = "khanhhn.hoang@gmail.com";
        String password = "rmjgjdgtziwhvmai";

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create a session object
        Session s = Session.getInstance(props, new Authenticator()
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(username, password);
            }
        });

        try
        {
            // Create a Mimemessage object
            MimeMessage message = new MimeMessage(s);

            // Set sender and recipient
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));

            // Set content
            message.setSubject(subject);
            message.setText(msg);

            // Send email
            Transport.send(message);
            System.out.println("Email sent successfully to" + toAddress);
        }
        catch (MessagingException e)
        {
            System.out.println("sendEmail: " + e.getMessage());
        }
    }
}
