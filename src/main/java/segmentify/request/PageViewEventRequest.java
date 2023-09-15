package segmentify.request;

import lombok.*;
import segmentify.model.PageViewEvent;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageViewEventRequest implements Serializable {

    private PageViewEvent pageViewEvent;
    private String apiKey;
}
