package bg.neshev.atlas.service;

import bg.neshev.atlas.dto.BaseDTO;
import bg.neshev.atlas.dto.PathDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PathService {
    public List<PathDTO> test() {
        List<PathDTO> paths = new ArrayList<>();

        paths.add(PathDTO.builder()
                         .price(1000)
                         .from(BaseDTO.builder()
                                      .lat(42.6977)
                                      .lng(23.3219)
                                      .name("Sofia")
                                      .build())
                        .to(BaseDTO.builder()
                                   .lat(42.1354)
                                   .lng(24.7453)
                                   .name("Plovdiv")
                                   .build())
                        .build());
        paths.add(PathDTO.builder()
                         .price(1000)
                         .from(BaseDTO.builder()
                                      .lat(42.6977)
                                      .lng(23.3219)
                                      .name("Sofia")
                                      .build())
                        .to(BaseDTO.builder()
                                   .lat(43.2141)
                                   .lng(27.9147)
                                   .name("Varna")
                                   .build())
                        .build());

        paths.add(PathDTO.builder()
                .price(1000)
                .from(BaseDTO.builder()
                             .lat(42.1354)
                             .lng(24.7453)
                             .name("Plovdiv")
                             .build())
                .to(BaseDTO.builder()
                           .lat(43.2141)
                           .lng(27.9147)
                           .name("Varna")
                           .build())
                .build());

        return paths;
    }
}
