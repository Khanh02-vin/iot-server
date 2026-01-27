package com.example.iot_server.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisherService {

    private MqttClient client;

    public MqttPublisherService() throws MqttException {
        client = new MqttClient(
                "tcp://localhost:1883",
                MqttClient.generateClientId());
        client.connect();
        System.out.println("✅ MQTT connected");
    }

    public void publish(String topic, String payload) throws MqttException {
        client.publish(topic, new MqttMessage(payload.getBytes()));
    }
}
