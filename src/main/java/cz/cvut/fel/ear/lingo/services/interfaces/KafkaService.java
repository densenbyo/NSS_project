package cz.cvut.fel.ear.lingo.services.interfaces;

import java.io.IOException;

/**
 * @author Mukan Atazhanov
 * @project nss_project
 * @created 15/04/2022 - 14:56
 */
public interface KafkaService {
    void consume(String message) throws IOException;
}
