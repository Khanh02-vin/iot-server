package com.example.iot_server.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    @Bean
    public MqttClient mqttClient() throws Exception {
        String broker = "tcp://localhost:1883";
        String clientId = "java-backend";

        MqttClient client = new MqttClient(
                broker,
                clientId,
                new MemoryPersistence());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);

        client.connect(options);

        System.out.println("✅ Connected to MQTT broker");

        return client;
    }
}
