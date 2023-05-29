package com.example.application;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
//import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

import java.util.Date;

//import javax.mail.Message;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import javax.print.attribute.standard.Destination;

public class EmailUtil {
    static final String FROM = "spyroretrogra@onet.pl";
    static final String TO1 = "rpo@spyro-soft.com";
    static final String TO2 = "ann@spyro-soft.com";

//    // The configuration set to use for this email. If you do not want to use a
//    // configuration set, comment the following variable and the
//    // .withConfigurationSetName(CONFIGSET); argument below.
//    static final String CONFIGSET = "ConfigSet";
    public static void sendEmail(Session session, String subject, String body){



        try
        {
            MimeMessage msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("spyroretrogra@onet.pl", "Spyro gra"));

            msg.setReplyTo(InternetAddress.parse("spyroretrogra@onet.pl", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());


            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(String.join(",", TO1)));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("EMail Sent Successfully!!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            AmazonSimpleEmailService client =
//                    AmazonSimpleEmailServiceClientBuilder.standard()
//                            .withCredentials(AWSCredentialsProvider)
//                            // Replace US_WEST_2 with the AWS Region you're using for
//                            // Amazon SES.
//                            .withRegion(Regions.EU_CENTRAL_1).build();
//            SendEmailRequest request = new SendEmailRequest()
//                    .withDestination(
//                            new Destination().withToAddresses(TO1,TO2))
//                    .withMessage(new Message()
//                            .withBody(new Body()
//                                    .withText(new Content()
//                                            .withCharset("UTF-8").withData(body)))
//                            .withSubject(new Content()
//                                    .withCharset("UTF-8").withData(subject)))
//                    .withSource(FROM);
//                    // Comment or remove the next line if you are not using a
//                    // configuration set
////                    .withConfigurationSetName(CONFIGSET);
//            client.sendEmail(request);
//            System.out.println("Email sent!");
//        } catch (Exception ex) {
//            System.out.println("The email was not sent. Error message: "
//                    + ex.getMessage());
//        }
    }
}
