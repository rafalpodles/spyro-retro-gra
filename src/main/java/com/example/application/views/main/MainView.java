package com.example.application.views.main;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {

    public MainView() {
        Text intro = new Text("Witaj na stronie odkrywców. Zeskanuj kod QR, aby dowiedzie się, gdzie jest aktualnie nasz przyjaciel.");
        add(intro);
    }

}
