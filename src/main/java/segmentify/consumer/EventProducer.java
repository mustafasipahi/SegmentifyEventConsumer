package segmentify.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import segmentify.request.PageViewEventRequest;
import segmentify.request.ProductViewEventRequest;
import segmentify.service.EventService;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventProducer {

    private final EventService eventService;

    @KafkaListener(topics = "product_view_event", groupId = "myGroup", containerFactory = "kafkaListenerContainerFactory")
    public void consumeProductViewEvent(ProductViewEventRequest request) {
        log.info("kafkaTemplate consumed product view event request : {}", request.toString());
        eventService.saveProductViewEvent(request);
    }

    @KafkaListener(topics = "page_view_event", groupId = "myGroup", containerFactory = "kafkaListenerContainerFactory")
    public void consumePageViewEvent(PageViewEventRequest request) {
        log.info("kafkaTemplate consumed page view event request : {}", request.toString());
        eventService.savePageViewEvent(request);
    }
}
