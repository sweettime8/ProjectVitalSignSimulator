package com.mrd.vitalsign.mqtt;

import com.mrd.vitalsign.utils.NumberUtil;
import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class PublisherTemp implements Runnable {

    private String gateId;
    private String sensorId;
    private static final int QOS = 1;
    private static final String MQTT_URL = "tcp://103.21.151.182:1883";

    public PublisherTemp() {
       
    }
    public static void main(String[] args) {
        new Thread(new PublisherTemp()).start();
    }
    private static void send(MqttClient mqttClient) throws MqttException, IOException, InterruptedException {
        MqttTopic topic = mqttClient.getTopic("RES_TRANSMIT_DATA_TEMP_4eb2459ae05f004f");
        MqttMessage message;
        String data;
        while (!Thread.currentThread().isInterrupted()) {
            data = "{\n"
                    + "    \"gate_id\": \"GW-122mdmas\",\n"
                    + "    \"display_id\": \"DP123456\",\n"
                    + "    \"sensor_id\": \"SS123\",\n"
                    + "    \"measure_id\": \"asacdasdas\",\n"
                    + "    \"ts\":" + NumberUtil.generateDoubleFromRange(0, 10000) + ",\n"
                    + "    \"temp\": " + NumberUtil.generateDoubleFromRange(0, 50) + "\n"
                    + "}";

            message = new MqttMessage(data.getBytes());
            message.setQos(QOS);
            message.setRetained(false);
            topic.publish(message);
            System.out.println("Publish data to [RES_TRANSMIT_DATA_TEMP_4eb2459ae05f004f] success!");

            Thread.sleep(1000);
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
                send(mqttClient);
            } catch (Exception ex) {
            }

        } catch (Exception ex) {
        }
    }

    /**
     * @return the gateId
     */
    public String getGateId() {
        return gateId;
    }

    /**
     * @param gateId the gateId to set
     */
    public void setGateId(String gateId) {
        this.gateId = gateId;
    }

    /**
     * @return the sensorId
     */
    public String getSensorId() {
        return sensorId;
    }

    /**
     * @param sensorId the sensorId to set
     */
    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }
}
