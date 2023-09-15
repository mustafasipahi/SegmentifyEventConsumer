package segmentify.consumer;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import segmentify.request.PageViewEventRequest;
import segmentify.request.ProductViewEventRequest;
import segmentify.service.EventService;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventConsumerTest {

    @InjectMocks
    private EventConsumer eventConsumer;
    @Mock
    private EventService eventService;

    @Test
    void shouldConsumeProductViewEvent() {
        final ProductViewEventRequest productViewEventRequest = ProductViewEventRequest.builder()
                .apiKey(RandomStringUtils.randomNumeric(3))
                .build();
        eventConsumer.consumeProductViewEvent(productViewEventRequest);
        verify(eventService).saveProductViewEvent(productViewEventRequest);
    }

    @Test
    void shouldConsumePageViewEvent() {
        final PageViewEventRequest pageViewEventRequest = PageViewEventRequest.builder()
                .apiKey(RandomStringUtils.randomNumeric(3))
                .build();
        eventConsumer.consumePageViewEvent(pageViewEventRequest);
        verify(eventService).savePageViewEvent(pageViewEventRequest);
    }
}