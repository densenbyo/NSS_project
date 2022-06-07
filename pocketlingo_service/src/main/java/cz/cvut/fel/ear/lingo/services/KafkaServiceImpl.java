package cz.cvut.fel.ear.lingo.services;

import cz.cvut.fel.ear.lingo.services.interfaces.KafkaService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Mukan Atazhanov
 * @project nss_project
 * @created 15/04/2022 - 14:57
 */

@Service
public class KafkaServiceImpl implements KafkaService {

    @Override
    @KafkaListener(topics = "AdminReport", groupId = "group_id")
    public void consume(String message) throws IOException {
        System.out.println("Kafka\n" +
                "Message: " + message);
    }
}
