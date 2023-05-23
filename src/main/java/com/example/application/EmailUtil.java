package com.example.application;

import java.util.Date;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
    public static void sendEmail(Session session, String[] recipients, String subject, String body){
        try
        {
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("spyroretrogra@onet.pl", "Spyro gra"));

            msg.setReplyTo(InternetAddress.parse("spyroretrogra@onet.pl", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());


            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", recipients)));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
