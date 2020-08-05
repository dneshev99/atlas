package bg.neshev.atlas.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointPOJO {
    private double lat;
    private double lng;
}
