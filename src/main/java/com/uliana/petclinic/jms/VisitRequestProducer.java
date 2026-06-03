package com.uliana.petclinic.jms;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.jms.Queue;

import java.util.logging.Logger;

@JMSDestinationDefinition(
        name = "java:/jms/queue/PetVisitQueue",
        interfaceName = "jakarta.jms.Queue",
        destinationName = "PetVisitQueue"
)
@Stateless
public class VisitRequestProducer {

    private static final Logger LOGGER = Logger.getLogger(VisitRequestProducer.class.getName());

    @Resource(lookup = "java:/jms/queue/PetVisitQueue")
    private Queue queue;

    @jakarta.inject.Inject
    private JMSContext context;

    public void sendVisitRequest(Long petId, String date, String reason) {
        String message = petId + "|" + date + "|" + reason;
        context.createProducer().send(queue, message);

        LOGGER.info("Visit request sent: petId=" + petId + ", date=" + date);
    }
}
