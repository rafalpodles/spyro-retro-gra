package com.example.application.views;

import com.example.application.SlackUtil;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@PageTitle("Intro")
@Route(value = "intro")
public class IntroView extends VerticalLayout {
    public IntroView() {

        Text text = new Text("Wprowadź nazwę drużyny i zatwierdź.");
        TextField inputField = new TextField("Nazwa drużyny");
        Button submitButton = new Button("Zatwierdź", event -> {
            String inputValue = inputField.getValue();
            try{
                sendSlack(inputValue);
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

    private void sendSlack(String teamName) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = LocalTime.now().format(formatter);

        String body = "Wystartował zespół " + teamName + ". Czas startu: " + timeString;
        SlackUtil.sendToChannel(body);
    }

}
