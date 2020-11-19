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
 * @author admin
 */
public class PublisherSpo2 implements Runnable {

    private static final int QOS = 1;
    private static final String MQTT_URL = "tcp://103.21.151.182:1883";

//    public static void main(String[] args) {
//        new Thread(new PublisherTemp()).start();
//    }
    private static void send(MqttClient mqttClient22212) throws MqttException, IOException, InterruptedException {

        MqttClient mqttClient = new MqttClient(MQTT_URL, MqttClient.generateClientId());
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(false);
        connOpts.setAutomaticReconnect(true);
        connOpts.setConnectionTimeout(10);
        connOpts.setKeepAliveInterval(1800);
        mqttClient.connect(connOpts);

        MqttTopic topic = mqttClient.getTopic("RES_TRANSMIT_DATA_SPO2_4eb2459ae05f004c");
        MqttMessage message;
        String data;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                data = "{\n"
                        + "    \"gate_id\": \"ELCGW-001-00000000649af267\",\n"
                        + "    \"display_id\": \"4eb2459ae05f004c\",\n"
                        + "    \"sensor_id\": \"bcbf91bd-c648-46fd-8bac-a0ee0b71d33n\",\n"
                        + "    \"measure_id\": \"asacdasdas\",\n"
                        + "    \"ts\":" + System.currentTimeMillis() / 1000 + ",\n"
                        + "    \"spo2\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                        + "    \"pi\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                        + "    \"pr\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                        + "    \"step\": " + NumberUtil.generateIntegerFromRange(0, 11150) + "\n"
                        + "}";

                message = new MqttMessage(data.getBytes());
                message.setQos(QOS);
                message.setRetained(false);
                topic.publish(message);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            System.out.println("Publish data to [RES_TRANSMIT_DATA_SPO2_4eb2459ae05f004c] success!");

            Thread.sleep(100);
        }
    }

    private static void sendNew(MqttClient mqttClient) throws MqttException, IOException, InterruptedException {


        MqttTopic topic = mqttClient.getTopic("RES/TRANSMIT/DATA/SPO2/4eb2459ae05f004c");
        MqttMessage message;
        String data;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                data = "{\n"
                        + "    \"gate_id\": \"ELCGW-001-00000000649af267\",\n"
                        + "    \"display_id\": \"4eb2459ae05f004c\",\n"
                        + "    \"sensor_id\": \"bcbf91bd-c648-46fd-8bac-a0ee0b71d33n\",\n"
                        + "    \"measure_id\": \"asacdasdas\",\n"
                        + "    \"ts\":" + System.currentTimeMillis() / 1000 + ",\n"
                        + "    \"spo2\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                        + "    \"pi\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                        + "    \"pr\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                        + "    \"step\": " + NumberUtil.generateIntegerFromRange(0, 11150) + "\n"
                        + "}";

                message = new MqttMessage(data.getBytes());
                message.setQos(QOS);
                message.setRetained(false);
                topic.publish(message);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
            System.out.println("Publish data to [RES_TRANSMIT_DATA_SPO2_4eb2459ae05f004c] success!");

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
                //send(mqttClient);
                sendNew(mqttClient);
            } catch (Exception ex) {
            }

        } catch (Exception ex) {
        }
    }
}
