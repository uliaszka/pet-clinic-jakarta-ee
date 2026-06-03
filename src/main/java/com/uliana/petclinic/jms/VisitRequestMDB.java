package com.uliana.petclinic.jms;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/queue/PetVisitQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class VisitRequestMDB implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(VisitRequestMDB.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage textMessage) {
                String body = textMessage.getText();
                LOGGER.info("Visit request received: " + body);

                String[] parts = body.split("\\|", -1);
                if (
                        parts.length < 3
                                || parts[0].isBlank()
                                || parts[1].isBlank()
                                || parts[2].isBlank()
                )  {
                    LOGGER.warning("Visit request failed: invalid data.");
                } else {
                    LOGGER.info("Visit request processed successfully for pet id " + parts[0]);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Visit request failed.", e);
        }
    }
}
