package com.edgecross.haccp.config;

import com.edgecross.haccp.dto.mqtt.MqttDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class MqttConfig {

    @Value("${spring.mqtt.username}")
    private String username;
    @Value("${spring.mqtt.password}")
    private String password;
    @Value("${spring.mqtt.url}")
    private String url;
    private static final String MQTT_CLIENT_ID = MqttAsyncClient.generateClientId();
    private static final String TOPIC_FILTER = "progix/haccp/process/history";

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MqttConnectOptions connectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setServerURIs(new String[] { url });
        return options;
    }

    @Bean
    public DefaultMqttPahoClientFactory defaultMqttPahoClientFactory() {
        DefaultMqttPahoClientFactory clientFactory = new DefaultMqttPahoClientFactory();
        clientFactory.setConnectionOptions(connectOptions());
        return clientFactory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inboundChannel() {

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(MQTT_CLIENT_ID, defaultMqttPahoClientFactory(), TOPIC_FILTER);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler inboundMessageHandler() {
        return message -> {
            String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
            System.out.println("Topic:" + topic);
            System.out.println("Payload" + message.getPayload());

            ObjectMapper mapper = new ObjectMapper();
            MqttDto mqttDto = null;

            try {
                mqttDto = mapper.readValue((String) message.getPayload(), MqttDto.class);
            } catch (JsonProcessingException e) {
                logger.error("[MqttConfig -> inboundMessageHandler] " + e.getMessage());
            }

            if (mqttDto != null) {
                logger.info("remoteMqtt != null");
            }
        };
    }
}