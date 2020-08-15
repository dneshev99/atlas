package com.neshev.atlas.service;

import com.neshev.atlas.dto.RegionDTO;
import com.neshev.atlas.dto.RegionLessDTO;
import com.neshev.atlas.entity.Country;
import com.neshev.atlas.entity.Region;
import com.neshev.atlas.exception.RegionNotFoundException;
import com.neshev.atlas.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegionService {
    private final RegionRepository regionRepository;
    private final CountryService countryService;

    private final ModelMapper mapper;

    public List<RegionLessDTO> findAllLess() {
        return regionRepository.findAll().stream()
                                         .map(region -> mapper.map(region, RegionLessDTO.class))
                                         .collect(Collectors.toList());
    }

    public List<RegionDTO> findAll() {
        return regionRepository.findAll().stream()
                                         .map(region -> mapper.map(region, RegionDTO.class))
                                         .collect(Collectors.toList());
    }

    public List<RegionDTO> findAllByCountryCode(String code) {
        Country country = countryService.findByCode(code);

        return regionRepository.findByCountry(country).stream()
                                                      .map(region -> mapper.map(region, RegionDTO.class))
                                                      .collect(Collectors.toList());
    }

    public RegionDTO findById(Integer id) {
        Optional<Region> regionOptional = regionRepository.findById(id);

        if (regionOptional.isEmpty()) {
            throw new RegionNotFoundException();
        } else {
            Region region = regionOptional.get();
            return mapper.map(region, RegionDTO.class);
        }
    }

    public Region findByCode(String code) {
        return regionRepository.findByCode(code).orElseThrow(() -> new RegionNotFoundException());
    }

    public RegionDTO createRegion(RegionDTO regionDTO) {
        Region newRegion = mapper.map(regionDTO, Region.class);
        newRegion.setCountry(countryService.findByCode(regionDTO.getCountry().getCode()));

        regionRepository.save(newRegion);

        return regionDTO;
    }

    public void deleteRegion(Integer id) {
        Optional<Region> regionOptional = regionRepository.findById(id);

        if (regionOptional.isEmpty()) {
            throw new RegionNotFoundException();
        } else {
            regionRepository.delete(regionOptional.get());
        }
    }
}
