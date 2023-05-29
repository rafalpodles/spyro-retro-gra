package com.example.application.views;

import com.example.application.EmailUtil;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

@PageTitle("Intro")
@Route(value = "intro")
public class IntroView extends VerticalLayout {
    public IntroView() {

        Text text = new Text("Wprowadź nazwę drużyny i zatwierdź.");
        TextField inputField = new TextField("Nazwa drużyny");
        Button submitButton = new Button("Zatwierdź", event -> {
            String inputValue = inputField.getValue();
            try{
                sendEmail(inputValue);
                Text emailSentText = new Text("Czas wystartował!");
                removeAll();
                add(emailSentText);
                UI.getCurrent().navigate(TemplateView.class,"init");
            } catch (Exception e){
                removeAll();
                add(new Text("Coś poszło nie tak. Zapisz aktualną godzinę na mapie."));
            }

        });
        add(text, inputField, submitButton);

    }

    private void sendEmail(String teamName) {
        String subject = "Wystartował team " + teamName;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = LocalTime.now().format(formatter);

        String body = "Wystartował zespół " + teamName + ". Czas startu: " + timeString;

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
        EmailUtil.sendEmail(session,subject, body);
    }

}
