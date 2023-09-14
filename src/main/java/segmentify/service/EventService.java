package segmentify.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import segmentify.request.EventRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService {

    private final RedisService redisService;

    @Transactional
    public void consumeEvent(EventRequest eventRequest) {
        redisService.save(eventRequest);
    }
}
