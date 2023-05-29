package com.example.application.views;

import com.example.application.EmailUtil;
import com.example.application.SlackUtil;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
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

    public BonusView() {
        Text text = new Text("Super, znaleźliście bonus. Otrzymujecie dodatkowy punkt! Podaj nazwę zespołu i zatwierdź.");
        TextField inputField = new TextField();
        Button submitButton = new Button("Zatwierdź", event -> {
            try {
                sendSlack(inputField.getValue());
            } catch (Exception e) {
                e.printStackTrace();
                Notification.show("Krza twarz, coś poszło nie tak. Wyślij zdjęcie, gdzie znalazłeś bonus do Rafała.");
            }
            UI.getCurrent().navigate(MainView.class);
        });
        add(text, inputField, submitButton);

    }

    private void sendSlack(String teamName) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = LocalTime.now().format(formatter);
        String body = "Bonus dla teamu " + teamName + ". Czas osiągnięcia bonusu: " + timeString;
        SlackUtil.sendToChannel(body);
    }

    private void sendEmail(String teamName) {
        String subject = "Bonus dla teamu: " + teamName;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = LocalTime.now().format(formatter);

        String body = "Bonus dla teamu " + teamName + ". Czas osiągnięcia bonusu: " + timeString;

//        final String fromEmail = "spyroretrogra@onet.pl";
//        final String password = "DupaDupa2@";
        final String fromEmail = "AKIAWQCAKF4VN72PA3XM";
        final String password = "BGeoZjjYtcOWEJZoBMrBtBqgta/kVrrnYZ0w/3lH7uLW";

        System.out.println("SSLEmail Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "email-smtp.eu-central-1.amazonaws.com");
//            props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.port", "587");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

//            Session session = Session.getDefaultInstance(props, auth);
        Session session = Session.getDefaultInstance(props);
        System.out.println("Session created");
        EmailUtil.sendEmail(session, subject, body);

    }
}
