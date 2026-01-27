package com.example.iot_server.service;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    private final MqttClient mqttClient;
    private volatile String lightState = "OFF";

    public MqttService(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    public void publish(String topic, String message) throws Exception {
        mqttClient.publish(topic, message.getBytes(), 0, false);
        System.out.println("📤 MQTT → " + topic + " = " + message);
    }

    @PostConstruct
    public void subscribe() throws Exception {
        mqttClient.subscribe("iot/light/status", (topic, msg) -> {
            String payload = new String(msg.getPayload());
            lightState = payload;
            System.out.println("📥 MQTT ← " + payload);
        });
    }

    public String getLightState() {
        return lightState;
    }
}
