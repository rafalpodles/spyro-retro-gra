package com.example.application.views;

import com.example.application.SlackUtil;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
}
