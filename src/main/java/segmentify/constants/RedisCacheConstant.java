package segmentify.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisCacheConstant {

    public static final String PRODUCT_VIEW_EVENT_CACHE_PREFIX = "product_view_event_cache_prefix_";
    public static final String PAGE_VIEW_EVENT_CACHE_PREFIX = "page_view_event_cache_prefix_";
}
