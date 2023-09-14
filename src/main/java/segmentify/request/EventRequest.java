package segmentify.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import segmentify.dto.EventDto;

import java.util.List;

@Getter
@Setter
@ToString
public class EventRequest {

    private List<EventDto> eventList;
    private String apiKey;
}
