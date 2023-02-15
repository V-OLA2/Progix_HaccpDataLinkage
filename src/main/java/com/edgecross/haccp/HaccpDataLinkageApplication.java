package com.edgecross.haccp;

import com.edgecross.haccp.config.MqttConfig;
import com.edgecross.haccp.datalinkage.HaccpDataLinkage;
import com.edgecross.haccp.dto.mqtt.MqttDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.MessageHandler;


@SpringBootApplication
public class HaccpDataLinkageApplication implements CommandLineRunner {

    @Autowired
    private MqttConfig mqttConfig;

	@Autowired
	private HaccpDataLinkage haccpDataLinkage;


    private static final Logger logger = LoggerFactory.getLogger(HaccpDataLinkageApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HaccpDataLinkageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        MqttDto mqttDto = (MqttDto) mqttConfig.inboundMessageHandler();
        MessageHandler message = mqttConfig.inboundMessageHandler();

        String status = message.getStatus();

        if (status.equals("start")) {
            haccpDataLinkage.start();
        } else if (status.equals("running")) {
            haccpDataLinkage.dataInput();
        } else if (status.equals("finish")) {
            haccpDataLinkage.finish();
        }

    }

}
