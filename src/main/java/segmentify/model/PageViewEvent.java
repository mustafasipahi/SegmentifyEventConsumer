package segmentify.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageViewEvent extends Event implements Serializable {

    private String category;
    private String subCategory;
}
