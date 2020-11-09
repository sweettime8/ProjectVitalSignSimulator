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
public class PublisherFromDisplay {

    public static void main(String[] args) throws MqttException {
        displayTurnOn();
        //displayUnLink();
        //displayAddLink();
        //displayGetPatient();

    }

    public static void displayUnLink() throws MqttException {
        String data = "{\"display_id\":\"4eb2459ae05f004f\",\"gate_id\":\"00bade93cdd4bd19\"}";

        System.out.println("== START displayUnLink PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("DISPLAY_UNLINK_GATE_REQ_4eb2459ae05f004f", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

    public static void displayAddLink() throws MqttException {
        String data = "{\"display_id\":\"4eb2459ae05f004f\",\"gate_id\":\"00bade93cdd4bd19\"}";

        System.out.println("== START displayAddLink PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("DISPLAY_LINK_GATE_REQ_4eb2459ae05f004f", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

    public static void displayTurnOn() throws MqttException {

        String data = "{\"display_id\":\"4eb2459ae05f004c\"}";
        System.out.println("== START PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("DISPLAY_REQ_GATE_SENSOR_4eb2459ae05f004c", message);

        System.out.println("\tMessage \n'" + data + "' '");

        client.disconnect();

        System.out.println("== END PUBLISHER ==");

    }

    public static void displayGetPatient() throws MqttException {
        //String s = "DISPLAY_REQ_GATE_SENSOR_4eb2459ae05f004c";
        //System.out.println(s.substring("DISPLAY_REQ_GATE_SENSOR_".length())); 
        System.out.println("displayGetPatient");
        String data = "{\"display_id\":\"4eb2459ae05f004c\"}";

//        JSONObject jsonObj = new JSONObject(data);
//        System.out.println("mrd : " + jsonObj.getString("display_id"));
        System.out.println("== START PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("GET_PATIENT_LIST_4eb2459ae05f004c", message);

        System.out.println("\tMessage \n'" + data + "' '");

        client.disconnect();

        System.out.println("== END PUBLISHER ==");

    }
}
