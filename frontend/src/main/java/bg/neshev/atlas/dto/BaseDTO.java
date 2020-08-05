package bg.neshev.atlas.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class BaseDTO {
    private double lat;
    private double lng;
    private String name;
}
