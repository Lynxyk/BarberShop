package com.example.demo.SMTP;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailNotificationSender {
    Req req = new Req();
    public void sendEmailNotification(String recipientEmail) {


        String host = "smtp.mail.ru";
        int port = 465;
        String username = req.getSmtpLogin();
        String password = req.getSmtpPass();


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Уведомление о входе");
            message.setText("Вы вошли в свою учетную запись.");


            Transport.send(message);

            System.out.println("Уведомление отправлено успешно.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
