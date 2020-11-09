/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.vitalsign.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author admin
 */
public class PublisherToDisplay {

    public static void main(String[] args) throws MqttException {
        //DisplayTurnOn();
        DisplayPatient();

    }

    public static void DisplayUnLink() throws MqttException {

    }

    public static void DisplayPatient() throws MqttException {

        String topic = "DISPLAY_UNLINK_GATE_RES_4eb2459ae05f004c";
        String data = "";
        System.out.println("== START PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish(topic, message);

        System.out.println("\tMessage \n'" + data + "' '");

        client.disconnect();

        System.out.println("== END PUBLISHER ==");

    }

    public static void DisplayTurnOn() throws MqttException {
        String s = "SERVER_RES_GATE_SENSOR_4eb2459ae05f004c";
        // System.out.println(s.substring("SERVER_RES_GATE_SENSOR_".length()));
        String data = "{\n"
                + "  \"gateId\": \"00bade93cdd4bd18\",\n"
                + "  \"status\": \"active\",\n"
                + "  \"sensorLst\": [\n"
                + "    {\n"
                + "      \"id\": \"sensor1\",\n"
                + "      \"gateId\": \"00bade93cdd4bd18\",\n"
                + "      \"patientId\": \"p1\",\n"
                + "      \"name\": \"nameSensor1\",\n"
                + "      \"sensorType\": \"V6\",\n"
                + "      \"batteryValue\": 0,\n"
                + "      \"status\": 1,\n"
                + "      \"measureState\": 0,\n"
                + "      \"isDeleted\": 0,\n"
                + "      \"lastUpdatedAt\": \"2020-11-05 22:50:02\"\n"
                + "    },\n"
                + "    {\n"
                + "      \"id\": \"sensor2\",\n"
                + "      \"gateId\": \"00bade93cdd4bd18\",\n"
                + "      \"patientId\": \"p2\",\n"
                + "      \"name\": \"nameSensor2\",\n"
                + "      \"sensorType\": \"v6\",\n"
                + "      \"batteryValue\": 0,\n"
                + "      \"status\": 1,\n"
                + "      \"measureState\": 0,\n"
                + "      \"isDeleted\": 0,\n"
                + "      \"lastUpdatedAt\": \"2020-11-05 22:50:33\"\n"
                + "    }\n"
                + "  ]\n"
                + "}";
        System.out.println("== START PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("SERVER_RES_GATE_SENSOR_4eb2459ae05f004c", message);

        System.out.println("\tMessage \n'" + data + "' '");

        client.disconnect();

        System.out.println("== END PUBLISHER ==");

    }
}
