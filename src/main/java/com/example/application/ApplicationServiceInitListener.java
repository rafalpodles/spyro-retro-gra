package com.example.application;

import com.example.application.views.TemplateView;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationServiceInitListener
        implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {

        List<String> allEndpoints = DataUtil.getAllEndpoints();
        RouteConfiguration configuration = RouteConfiguration.forApplicationScope();
        for(String endpoint : allEndpoints){
            configuration.setRoute(endpoint+"/Dupa", TemplateView.class);
        }

    }
}
