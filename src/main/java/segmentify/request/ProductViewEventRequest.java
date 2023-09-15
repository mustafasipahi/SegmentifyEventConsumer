package segmentify.request;

import lombok.*;
import segmentify.model.ProductViewEvent;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductViewEventRequest implements Serializable {

    private ProductViewEvent productViewEvent;
    private String apiKey;
}
