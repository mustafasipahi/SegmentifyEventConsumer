package segmentify.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import segmentify.request.PageViewEventRequest;
import segmentify.request.ProductViewEventRequest;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @InjectMocks
    private EventService eventService;
    @Mock
    private RedisService redisService;

    @Test
    void shouldSaveProductViewEvent() {
        final ProductViewEventRequest productViewEventRequest = ProductViewEventRequest.builder()
                .apiKey(RandomStringUtils.randomNumeric(3))
                .build();
        eventService.saveProductViewEvent(productViewEventRequest);
        verify(redisService).saveProductViewEvent(productViewEventRequest);
    }

    @Test
    void shouldSavePageViewEvent() {
        final PageViewEventRequest pageViewEventRequest = PageViewEventRequest.builder()
                .apiKey(RandomStringUtils.randomNumeric(3))
                .build();
        eventService.savePageViewEvent(pageViewEventRequest);
        verify(redisService).savePageViewEvent(pageViewEventRequest);
    }
}