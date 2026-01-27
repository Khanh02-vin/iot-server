#include <stdio.h>
#include <string.h>

#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "freertos/event_groups.h"

#include "esp_wifi.h"
#include "esp_event.h"
#include "nvs_flash.h"
#include "esp_log.h"

#include "mqtt_client.h"
#include "driver/gpio.h"

#define WIFI_SSID "Van Khanh Bin"
#define WIFI_PASS "mitxoai024"

#define LED_GPIO GPIO_NUM_2   // LED onboard ESP32

static const char *TAG = "ESP32_MQTT";

/* ================= LED ================= */
static void led_init() {
    gpio_reset_pin(LED_GPIO);
    gpio_set_direction(LED_GPIO, GPIO_MODE_OUTPUT);
    gpio_set_level(LED_GPIO, 0);
}

/* ================= MQTT EVENT ================= */
static void mqtt_event_handler(void *handler_args,
                               esp_event_base_t base,
                               int32_t event_id,
                               void *event_data) {
    esp_mqtt_event_handle_t event = event_data;

    if (event->event_id == MQTT_EVENT_DATA) {
        char data[event->data_len + 1];
        memcpy(data, event->data, event->data_len);
        data[event->data_len] = 0;

        ESP_LOGI(TAG, "MQTT DATA: %s", data);

        if (strcmp(data, "ON") == 0) {
            gpio_set_level(LED_GPIO, 1);
        } else if (strcmp(data, "OFF") == 0) {
            gpio_set_level(LED_GPIO, 0);
        }
    }
}

/* ================= MQTT START ================= */
static void mqtt_start() {
    esp_mqtt_client_config_t mqtt_cfg = {
        .broker.address.uri = "mqtt://broker.emqx.io:1883",
    };

    esp_mqtt_client_handle_t client = esp_mqtt_client_init(&mqtt_cfg);
    esp_mqtt_client_register_event(
        client, ESP_EVENT_ANY_ID, mqtt_event_handler, NULL);
    esp_mqtt_client_start(client);

    esp_mqtt_client_subscribe(client, "esp32/led", 0);
}

/* ================= WIFI ================= */
static void wifi_init() {
    esp_netif_init();
    esp_event_loop_create_default();
    esp_netif_create_default_wifi_sta();

    wifi_init_config_t cfg = WIFI_INIT_CONFIG_DEFAULT();
    esp_wifi_init(&cfg);

    wifi_config_t wifi_config = {
        .sta = {
            .ssid = WIFI_SSID,
            .password = WIFI_PASS,
        },
    };

    esp_wifi_set_mode(WIFI_MODE_STA);
    esp_wifi_set_config(WIFI_IF_STA, &wifi_config);
    esp_wifi_start();
    esp_wifi_connect();
}

/* ================= MAIN ================= */
void app_main(void) {
    nvs_flash_init();
    led_init();
    wifi_init();

    vTaskDelay(pdMS_TO_TICKS(5000)); // đợi wifi
    mqtt_start();
}
