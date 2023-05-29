package com.example.application.views;

import com.example.application.Data;
import com.example.application.DataUtil;
import com.example.application.views.main.MainView;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;

import java.util.Arrays;

@Route(value = ":url")
public class TemplateView extends VerticalLayout implements RouterLayout, HasUrlParameter<String> {

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        String path = null;
        if (parameter != null) {
            path = parameter;
        } else if (event.getRouteParameters().get("url").isPresent()) {
            path = event.getRouteParameters().get("url").get();
        }
        Data data = null;
        try {
            data = DataUtil.getByEndpoint(path);
        } catch (Exception e) {
            UI.getCurrent().navigate(MainView.class);
            Text t = new Text("Nic tu nie ma");
            add(t);
        }

        if (data != null) {
            if (data.getEndpoint().equals("bonus")) {
                UI.getCurrent().navigate(BonusView.class);
            } else {
                Data finalData = data;
                Text text = new Text(data.getMessage());
                TextField passwordField = new TextField("Hasło");
                Button submitButton = new Button("Zatwierdź hasło", e -> {
                    String enteredPassword = passwordField.getValue();
                    String[] passwords = finalData.getPassword().split(";");
                    if (Arrays.stream(passwords).map(String::toLowerCase).toList().contains(enteredPassword.toLowerCase().trim())) {
                        passwordCorrect(finalData);
                    } else {
                        Notification.show("Złe hasło!");
                    }
                });
                add(text, passwordField, submitButton);
            }
        }

    }

    public final void passwordCorrect(Data data) {
        Image image = new Image(data.getImage(), data.getAlt());
        Text hint = new Text(data.getHint());
        add(image, hint);
    }


    public TemplateView() {
    }


}


