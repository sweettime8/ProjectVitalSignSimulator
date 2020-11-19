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
//    public static void main(String[] args) {
//        new Thread(new PublisherTemp()).start();
//    }

    private static void send(MqttClient mqttClient) throws MqttException, IOException, InterruptedException {
        String topicName = "RES/TRANSMIT/DATA/TEMP/4eb2459ae05f004c";
        MqttTopic topic = mqttClient.getTopic(topicName);
        MqttMessage message;
        String data;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                data = "{\n"
                        + "    \"gate_id\": \"ELCGW-001-00000000649af267\",\n"
                        + "    \"display_id\": \"4eb2459ae05f004c\",\n"
                        + "    \"sensor_id\": \"bcbf91bd-c648-46fd-8bac-a0ee0b71d33p\",\n"
                        + "    \"measure_id\": \"asacdasdas\",\n"
                        + "    \"ts\":" + System.currentTimeMillis() / 1000 + ",\n"
                        + "    \"temp\": " + NumberUtil.generateIntegerFromRange(3000, 4000) + "\n"
                        + "}";

                message = new MqttMessage(data.getBytes());
                message.setQos(QOS);
                message.setRetained(false);
                topic.publish(message);
                System.out.println("Publish data to +[" + topicName + " success!");

                //temp2
                String topicName1 = "RES/TRANSMIT/DATA/TEMP/25f97b784dc3780a";
                String data1 = "{\n"
                        + "    \"gate_id\": \"ELCGW-001-000000006bcbd964\",\n"
                        + "    \"display_id\": \"25f97b784dc3780a\",\n"
                        + "    \"sensor_id\": \"bcbf91bd-c648-46fd-8bac-a0ee0b71ddt1\",\n"
                        + "    \"measure_id\": \"asacdasdas\",\n"
                        + "    \"ts\":" + System.currentTimeMillis() / 1000 + ",\n"
                        + "    \"temp\": " + NumberUtil.generateIntegerFromRange(3000, 4000) + "\n"
                        + "}";

                message = new MqttMessage(data1.getBytes());
                message.setQos(QOS);
                message.setRetained(false);
                topic.publish(message);
                System.out.println("Publish data to +[" + topicName1 + " success!");
                //end temp2

                Thread.sleep(100);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

        }
    }

    private static void sendTemp(MqttClient mqttClient) throws MqttException, IOException, InterruptedException {
        String[] topicName = {
            "RES/TRANSMIT/DATA/TEMP/4eb2459ae05f004c",
            "RES/TRANSMIT/DATA/TEMP/25f97b784dc3780a",
            "RES/TRANSMIT/DATA/TEMP/25f97b784dc3780a",
            "RES/TRANSMIT/DATA/TEMP/4eb2459ae05f004f"
        };

        String[] displayId = {
            "4eb2459ae05f004c",
            "25f97b784dc3780a",
            "25f97b784dc3780a",
            "4eb2459ae05f004f"
        };
        String[] gateId = {
            "ELCGW-001-00000000649af267",
            "ELCGW-001-000000006bcbd964",
            "ELCGW-001-000000006bcbd964",
            "ELCGW-001-00000000649af265",};
        String[] sensorId = {
            "bcbf91bd-c648-46fd-8bac-a0ee0b71d33p",
            "bcbf91bd-c648-46fd-8bac-a0ee0b71ddt1",
            "bcbf91bd-c648-46fd-8bac-a0ee0b71d33f",
            "bcbf91bd-c648-46fd-8bac-a0ee0b71d33d"
        };

        while (!Thread.currentThread().isInterrupted()) {

            for (int i = 0; i < topicName.length; i++) {
                MqttTopic topic = mqttClient.getTopic(topicName[i]);
                MqttMessage message;
                String data;
                try {
                    data = "{\n"
                            + "    \"gate_id\":\"" + gateId[i] + "\",\n"
                            + "    \"display_id\":\"" + displayId[i] + "\",\n"
                            + "    \"sensor_id\":\"" + sensorId[i] + "\",\n"
                            + "    \"measure_id\": \"asacdasdas\",\n"
                            + "    \"ts\":" + System.currentTimeMillis() / 1000 + ",\n"
                            + "    \"temp\": " + NumberUtil.generateIntegerFromRange(3000, 4000) + "\n"
                            + "}";
                    message = new MqttMessage(data.getBytes());
                    message.setQos(QOS);
                    message.setRetained(false);
                    topic.publish(message);
                    System.out.println("Publish data to [" + topicName[i] + " success!");

                } catch (MqttException ex) {
                    System.out.println(ex.toString());
                }
                Thread.sleep(300);
            }
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
                sendTemp(mqttClient);
                //send1(mqttClient);
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
