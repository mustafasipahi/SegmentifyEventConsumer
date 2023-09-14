package segmentify.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import segmentify.request.EventRequest;
import segmentify.service.EventService;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventProducer {

    private final EventService eventService;

    @KafkaListener(topics = "event", groupId = "myGroup", containerFactory = "kafkaListenerContainerFactory")
    public void consumeEvent(EventRequest eventRequest) {
        log.info("kafkaTemplate consumed request : {}", eventRequest.toString());
        eventService.consumeEvent(eventRequest);
    }
}
