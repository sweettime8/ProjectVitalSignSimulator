/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.vitalsign.mqtt;

import com.mrd.vitalsign.utils.NumberUtil;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/**
 *
 * @author Admin
 */
public class PublisherBP implements Runnable {

    private static final int QOS = 1;
    private static final String MQTT_URL = "tcp://103.21.151.182:1883";

//    public static void main(String[] args) {
//        new Thread(new PublisherTemp()).start();
//    }
    private static void send(MqttClient mqttClient) throws MqttException, IOException, InterruptedException {
        MqttTopic topic = mqttClient.getTopic("RES_TRANSMIT_DATA_NIBP_4eb2459ae05f004c");
        MqttMessage message;
        String data;
        while (!Thread.currentThread().isInterrupted()) {
            data = "{\n"
                    + "    \"gate_id\": \"ELCGW-001-00000000649af267\",\n"
                    + "    \"display_id\": \"4eb2459ae05f004c\",\n"
                    + "    \"sensor_id\": \"bcbf91bd-c648-46fd-8bac-a0ee0b71d33b\",\n"
                    + "    \"measure_id\": \"asacdasdas\",\n"
                    + "    \"ts\":" + System.currentTimeMillis() / 1000 + ",\n"
                    + "    \"dia\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                    + "    \"sys\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                    + "    \"map\":" + NumberUtil.generateIntegerFromRange(0, 100) + ",\n"
                    + "    \"pr\": " + NumberUtil.generateIntegerFromRange(0, 100) + "\n"
                    + "}";

            message = new MqttMessage(data.getBytes());
            message.setQos(QOS);
            message.setRetained(false);
            topic.publish(message);
            System.out.println("Publish data to [RES_TRANSMIT_DATA_NIBP_4eb2459ae05f004c] success!");

            Thread.sleep(100);
        }
    }

    private static void send1(MqttClient mqttClient) throws MqttException, IOException, InterruptedException {
        String[] topicName = {
            "RES/TRANSMIT/DATA/NIBP/25f97b784dc3780a",
            "RES/TRANSMIT/DATA/NIBP/4eb2459ae05f004c",
            "RES/TRANSMIT/DATA/NIBP/25f97b784dc3780a",
            "RES/TRANSMIT/DATA/NIBP/4eb2459ae05f004c"
        };

        String[] displayId = {
            "25f97b784dc3780a",
            "4eb2459ae05f004c",
            "25f97b784dc3780a",
            "4eb2459ae05f004c"
        };
        String[] gateId = {
            "ELCGW-001-000000006bcbd964",
            "ELCGW-001-00000000649af267",
            "ELCGW-001-000000006bcbd964",
            "ELCGW-001-00000000649af267"
        };
        String[] sensorId = {
            "bcbf91bd-c648-46fd-8bac-a0ee0b71d33b",
            "bcbf91bd-c648-46fd-8bac-a0ee0bdpnm1",
            "bcbf91bd-c648-46fd-8bac-a0ee0b71d33f",
            "bcbf91bd-c648-46fd-8bac-a0ee0bdtnm1"
        };

        while (!Thread.currentThread().isInterrupted()) {
            for (int i = 0; i < topicName.length; i++) {
                MqttTopic topic = mqttClient.getTopic(topicName[i]);
                MqttMessage message;
                String data;
                try {
                    data = "{\n"
                            + "   \"gate_id\":\"" + gateId[i] + "\",\n"
                            + "   \"display_id\":\"" + displayId[i] + "\",\n"
                            + "    \"sensor_id\":\"" + sensorId[i] + "\",\n"
                            + "    \"measure_id\": \"asacdasdas\",\n"
                            + "    \"ts\":" + System.currentTimeMillis() / 1000 + ",\n"
                            + "    \"dia\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                            + "    \"sys\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                            + "    \"map\":" + NumberUtil.generateIntegerFromRange(0, 100) + ",\n"
                            + "    \"pr\": " + NumberUtil.generateIntegerFromRange(0, 100) + "\n"
                            + "}";

                    message = new MqttMessage(data.getBytes());
                    message.setQos(QOS);
                    message.setRetained(false);
                    topic.publish(message);
                    System.out.println("Publish data to [" + topicName[i] + " success!");

                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }
            }
            Thread.sleep(300);
        }
    }

    @Override
    public void run() {
        try {
            MqttClient mqttClient = new MqttClient(MQTT_URL, String.valueOf(System.nanoTime()));
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            connOpts.setAutomaticReconnect(true);
            connOpts.setConnectionTimeout(10);
            connOpts.setKeepAliveInterval(1800);
            mqttClient.connect(connOpts);

            try {
                send1(mqttClient);
            } catch (Exception ex) {
            }

        } catch (Exception ex) {
        }
    }
}
