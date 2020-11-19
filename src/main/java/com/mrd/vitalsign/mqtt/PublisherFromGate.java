/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrd.vitalsign.mqtt;

import com.mrd.vitalsign.utils.NumberUtil;
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
        //dataTemp();
        //dataSpo2();
        // dataBp();
        //GateResAddSensor();
        //sync data
        new Thread(new PublisherSpo2()).start();
        new Thread(new PublisherBP()).start();
        new Thread(new PublisherTemp()).start();
    }

    public static void GateResAddSensor() throws MqttException {
        String data = "{\n"
                + "       \"display_id\" : \"4eb2459ae05f004c\",\n"
                + "       \"gate_id\" : \"ELCGW-001-00000000649af264\",\n"
                + "       \"mac\" : \"bcbf91bd-c648-46fd-8bac-a0ee0b71d33m\",\n"
                + "       \"name\": \"Máy đo huyết ap\",\n"
                + "       \"status\" : 1,\n"
                + "       \"message\" : \"Thêm thành công\"\n"
                + "}";

        System.out.println("== START turnOnGate PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("GATE/SERVER/ADD_SENSOR_REQ/ELCGW-001-00000000649af264", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

    public static void turnOnGate() throws MqttException {
        String data = "{\"gate_id\":\"ELCGW-001-00000000649af267\"}";

        System.out.println("== START turnOnGate PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("GATE/REQ/DISPLAY/SENSOR/ELCGW-001-00000000649af267", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

    public static void connectSensor() throws MqttException {
        String data = "{\"gate_id\":\"ELCGW-001-00000000649af267\",\n"
                + "\"display_id\":\"4eb2459ae05f004c\",\n"
                + "\"sensor_id\":\"bcbf91bd-c648-46fd-8bac-a0ee0b71d33n\",\n"
                + "\"result_code\":1\n"
                + "}";
        System.out.println("== START connectSensor PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("RES/CONNECT/TO/SENSOR/4eb2459ae05f004c", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

    public static void disconnectSensor() throws MqttException {
        String data = "{\"gate_id\":\"ELCGW-001-00000000649af267\",\n"
                + "\"display_id\":\"4eb2459ae05f004c\",\n"
                + "\"sensor_id\":\"bcbf91bd-c648-46fd-8bac-a0ee0b71d33n\",\n"
                + "\"result_code\":1\n"
                + "}";
        System.out.println("== START DisconnectSensor PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("RES/DISCONNECT/TO/SENSOR/4eb2459ae05f004c", message);

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

    public static void dataSpo2() throws MqttException {
        String data = "{\n"
                + "    \"gate_id\": \"ELCGW-001-00000000649af264\",\n"
                + "    \"display_id\": \"4eb2459ae05f004f\",\n"
                + "    \"sensor_id\": \"bcbf91bd-c648-46fd-8bac-a0ee0b71d33g\",\n"
                + "    \"measure_id\": \"asacdasdas\",\n"
                + "    \"ts\":" + NumberUtil.generateDoubleFromRange(0, 10000) + ",\n"
                + "    \"spo2\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                + "    \"pi\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                + "    \"pr\":" + NumberUtil.generateDoubleFromRange(0, 50) + ",\n"
                + "    \"step\": " + NumberUtil.generateIntegerFromRange(0, 11150) + "\n"
                + "}";

        System.out.println("== START dataSpo2 PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("RES_TRANSMIT_DATA_SPO2_4eb2459ae05f004f", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

    public static void dataBp() throws MqttException {
        String data = "{\n"
                + "    \"gate_id\": \"ELCGW-001-00000000649af264\",\n"
                + "    \"display_id\": \"DP123456\",\n"
                + "    \"sensor_id\": \"SS123\",\n"
                + "    \"measure_id\": \"asacdasdas\",\n"
                + "    \"ts\":" + NumberUtil.generateDoubleFromRange(0, 10000) + ",\n"
                + "    \"dia\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                + "    \"sys\":" + NumberUtil.generateIntegerFromRange(0, 50) + ",\n"
                + "    \"map\":" + NumberUtil.generateIntegerFromRange(0, 100) + ",\n"
                + "    \"pr\": " + NumberUtil.generateIntegerFromRange(0, 100) + "\n"
                + "}";
        System.out.println("== START dataBp PUBLISHER ==");

        MqttClient client = new MqttClient("tcp://103.21.151.182:1883", MqttClient.generateClientId());
        client.connect();
        MqttMessage message = new MqttMessage();
        message.setPayload(data.getBytes());
        client.publish("RES_TRANSMIT_DATA_NIBP_4eb2459ae05f004f", message);

        System.out.println("\tMessage \n'" + data + "' '");
        client.disconnect();
        System.out.println("== END PUBLISHER ==");
    }

}
