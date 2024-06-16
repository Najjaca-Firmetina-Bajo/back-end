package com.nfb.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_NAME = "contract-queue";
    public static final String DELIVER_QUEUE_NAME = "deliver-contract";
    public static final String EXPIRE_QUEUE_NAME = "expire-contract";
    public static final String CANCEL_QUEUE_NAME = "cancel-contract";

    @Bean
    public Queue contractQueue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Queue deliverContractQueue() {
        return new Queue(DELIVER_QUEUE_NAME, true);
    }

    @Bean
    public Queue expireContractQueue() {
        return new Queue(EXPIRE_QUEUE_NAME, true);
    }

    @Bean
    public Queue cancelContractQueue() {
        return new Queue(CANCEL_QUEUE_NAME, true);
    }


}
