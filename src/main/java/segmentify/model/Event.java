package segmentify.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import segmentify.constants.*;

import java.io.Serializable;
import java.util.HashMap;

@Getter
@Setter
@ToString
@JsonSubTypes({@JsonSubTypes.Type(PageViewEvent.class), @JsonSubTypes.Type(ProductViewEvent.class)})
public abstract class Event implements Serializable {

    private EventNameType name;
    private String userId;
    private String sessionId;
    private DeviceType device;
    private String userAgent;
    private Language lang;
    private String region;
    private String currency;
    private BrowserType browser;
    private OperatingSystemType os;
    private String osversion;
    private String pageUrl;
    private String referrer;
    private HashMap<String, String> params;
    private boolean testMode;
    private boolean nextPage;
}
