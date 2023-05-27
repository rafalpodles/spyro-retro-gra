package com.example.application.views;

import com.example.application.EmailUtil;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
@Route("bonus")
public class BonusView extends VerticalLayout {
    public BonusView(){
        Text text = new Text("Super, znaleźliście bonus. Otrzymujecie dodatkowy punkt! Podaj nazwę zespołu i zatwierdź.");
        TextField inputField = new TextField();
        Button submitButton = new Button("Zatwierdź", event -> {
            sendEmail(inputField.getValue());
            UI.getCurrent().navigate(MainView.class);
        });
        add(text, inputField,submitButton);

    }
    private void sendEmail(String teamName){
            String[] recipients = {"rpo@spyro-soft.com","ann@spyro-soft.com"};
            String subject = "Bonus dla teamu: " + teamName;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = LocalTime.now().format(formatter);

            String body = "Bonus dla teamu " + teamName + ". Czas osiągnięcia bonusu: " + timeString;

            final String fromEmail = "spyroretrogra@onet.pl";
            final String password = "DupaDupa2@";

            System.out.println("SSLEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.poczta.onet.pl");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Authenticator auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };

            Session session = Session.getDefaultInstance(props, auth);
            System.out.println("Session created");
            EmailUtil.sendEmail(session, recipients,subject, body);

    }
}
