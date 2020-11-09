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
        turnOnGate();

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
}
