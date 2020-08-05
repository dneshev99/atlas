package bg.neshev.atlas.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PathDTO {
    private BaseDTO from;
    private BaseDTO to;
    private int price;
}
