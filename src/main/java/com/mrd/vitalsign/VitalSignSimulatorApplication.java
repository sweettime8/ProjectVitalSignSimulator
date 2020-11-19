package com.mrd.vitalsign;

import com.mrd.vitalsign.mqtt.PublisherBP;
import com.mrd.vitalsign.mqtt.PublisherSpo2;
import com.mrd.vitalsign.mqtt.PublisherTemp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VitalSignSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(VitalSignSimulatorApplication.class, args);
        new Thread(new PublisherSpo2()).start();
        new Thread(new PublisherBP()).start();
        new Thread(new PublisherTemp()).start();
    }

}
