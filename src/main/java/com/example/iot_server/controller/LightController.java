package com.example.iot_server.controller;

import com.example.iot_server.service.MqttPublisherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/led")
public class LightController {

    private final MqttPublisherService mqtt;

    public LightController(MqttPublisherService mqtt) {
        this.mqtt = mqtt;
    }

    @RequestMapping(value = "/on", method = { RequestMethod.GET, RequestMethod.POST })
    public String ledOn() throws Exception {
        mqtt.publish("home/led", "ON");
        return "LED ON";
    }

    @RequestMapping(value = "/off", method = { RequestMethod.GET, RequestMethod.POST })
    public String ledOff() throws Exception {
        mqtt.publish("home/led", "OFF");
        return "LED OFF";
    }
}
