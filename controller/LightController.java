package com.example.iot_server.controller;

import com.example.iot_server.mqtt.MqttService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/light")
public class LightController {

    private final MqttService mqttService;

    public LightController(MqttService mqttService) {
        this.mqttService = mqttService;
    }

    @PostMapping("/on")
    public String turnOn() throws MqttException {
        mqttService.publish("ON");
        return "💡 Light ON";
    }

    @PostMapping("/off")
    public String turnOff() throws MqttException {
        mqttService.publish("OFF");
        return "💡 Light OFF";
    }
}
