package segmentify.model;

import lombok.*;
import segmentify.constants.Gender;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductViewEvent extends Event implements Serializable {

    private String productId;
    private String title;
    private boolean inStock;
    private String url;
    private String mUrl;
    private String image;
    private String imageXS;
    private String imageS;
    private String imageM;
    private String imageL;
    private String imageXL;
    private String category;
    private String brand;
    private BigDecimal price;
    private BigDecimal oldPrice;
    private BigDecimal specialPrice;
    private String currency;
    private Gender gender;
    private List<String> colors;
    private List<String> sizes;
    private List<String> labels;
    private Integer stockCount;
    private Double stockRatio;
    private Integer stockStatus;
}
