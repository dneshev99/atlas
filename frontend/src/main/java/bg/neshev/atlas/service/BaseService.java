package bg.neshev.atlas.service;

import bg.neshev.atlas.dto.BaseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseService {
    public List<BaseDTO> test() {
        List<BaseDTO> testData = new ArrayList<>();

        testData.add(BaseDTO.builder()
                            .lat(42.6977)
                            .lng(23.3219)
                            .name("Sofia")
                            .build());

        testData.add(BaseDTO.builder()
                            .lat(42.1354)
                            .lng(24.7453)
                            .name("Plovdiv")
                            .build());

        testData.add(BaseDTO.builder()
                            .lat(43.2141)
                            .lng(27.9147)
                            .name("Varna")
                            .build());

        return testData;
    }
}
