package com.nmtech.nmtech_v2.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @EventListener(ContextRefreshedEvent.class)
    public void updateDatasourceUrl() {
        try {
            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            String updatedUrl = datasourceUrl.replace("localhost", ipAddress);

            // Configurar la propiedad actualizada en el contexto
            System.setProperty("spring.datasource.url", updatedUrl);
            System.out.println("Datasource URL actualizado con IP: " + updatedUrl);
        } catch (UnknownHostException e) {
            System.err.println("No se pudo obtener la dirección IP local: " + e.getMessage());
        }
    }
}
