package com.nfb.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "contract-queue";

    @Bean
    public Queue contractQueue() {
        return new Queue(QUEUE_NAME, true);
    }


}
