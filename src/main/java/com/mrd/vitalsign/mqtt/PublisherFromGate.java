/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.vitalsign.mqtt;

import static com.mrd.vitalsign.mqtt.PublisherFromDisplay.displayTurnOn;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author admin
 */
public class PublisherFromGate {

    public static void main(String[] args) throws MqttException {
        //turnOnGate();
        //connectSensor();
        //disconnectSensor();
        dataTemp();
    }

    public static void turnOnGate() throws MqttException {
        String data = "{\"gateId\":\"00bade93cdd4bd18\"}";

        System.out.println("== START turnOnGate PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("GATE_REQ_DISPLAY_SENSOR_00bade93cdd4bd19", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

    public static void connectSensor() throws MqttException {
        String data = "{\"gate_id\":\"00bade93cdd4bd19\",\"display_id\":\"4eb2459ae05f004f\",\"sensor_id\":\"sensor1\"}";
        System.out.println("== START connectSensor PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("RES_CONNECT_TO_SENSOR_4eb2459ae05f004f", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

    public static void disconnectSensor() throws MqttException {
        String data = "{\"gate_id\":\"00bade93cdd4bd19\",\"display_id\":\"4eb2459ae05f004f\",\"sensor_id\":\"sensor1\"}";
        System.out.println("== START connectSensor PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("RES_DISCONNECT_TO_SENSOR_4eb2459ae05f004f", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

    public static void dataTemp() throws MqttException {
        String data = "{\n"
                + "    \"gate_id\": \"GW-122mdmas\",\n"
                + "    \"display_id\": \"DP123456\",\n"
                + "    \"sensor_id\": \"SS123\",\n"
                + "    \"measure_id\": \"asacdasdas\",\n"
                + "    \"ts\": 12345678992,\n"
                + "    \"temp\": 3645\n"
                + "}";
        System.out.println("== START dataTemp PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("RES_TRANSMIT_DATA_TEMP_4eb2459ae05f004f", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

}
