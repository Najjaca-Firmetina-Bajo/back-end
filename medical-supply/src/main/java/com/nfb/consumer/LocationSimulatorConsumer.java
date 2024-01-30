package com.nfb.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class LocationSimulatorConsumer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private static final Logger log = LoggerFactory.getLogger(LocationSimulatorConsumer.class);


    /*
     * @RabbitListener anotira metode za kreiranje handlera za bilo koju poruku koja
     * pristize, sto znaci da ce se kreirati listener koji je konektovan na RabbitQM
     * queue i koji ce prosledjivati poruke metodi. Listener ce konvertovati poruku
     * u odgovorajuci tip koristeci odgovarajuci konvertor poruka (implementacija
     * org.springframework.amqp.support.converter.MessageConverter interfejsa).
     */
    @RabbitListener(
            queues = "${myqueue}"
    )
    public void handleMessage(String message) {
        log.info("Consumer> " + message);
        messagingTemplate.convertAndSend("/socket-publisher", message);
    }
}
