package com.neshev.atlas.service;

import com.neshev.atlas.dto.BaseDTO;
import com.neshev.atlas.dto.CountryDTO;
import com.neshev.atlas.dto.RegionDTO;
import com.neshev.atlas.entity.Base;
import com.neshev.atlas.exception.BaseNotFoundException;
import com.neshev.atlas.repository.BaseRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BaseService {
    private final BaseRepository baseRepository;
    private final RegionService regionService;

    private final ModelMapper mapper;

    public List<BaseDTO> findAll() {
        List<BaseDTO> result = new ArrayList<>();
        for (Base base: baseRepository.findAll()) {
            if (base != null) {
                BaseDTO dto = mapper.map(base, BaseDTO.class);

                dto.setRegion(mapper.map(base.getRegion(), RegionDTO.class));
                dto.getRegion().setCountry(mapper.map(base.getRegion().getCountry(), CountryDTO.class));

                result.add(dto);
            }
        }
        return result;
    }

    public BaseDTO findById(Integer id) {
        Optional<Base> baseOptional = baseRepository.findById(id);

        if (baseOptional.isEmpty()) {
            throw new BaseNotFoundException();
        } else {
            Base base = baseOptional.get();

            BaseDTO dto = mapper.map(base, BaseDTO.class);

            dto.setRegion(mapper.map(base.getRegion(), RegionDTO.class));
            dto.getRegion().setCountry(mapper.map(base.getRegion().getCountry(), CountryDTO.class));

            return dto;
        }
    }

    public BaseDTO createBase(BaseDTO baseDTO) {
        Base newBase = mapper.map(baseDTO, Base.class);
        newBase.setRegion(regionService.findByCode(baseDTO.getRegion().getCode()));
        baseRepository.save(newBase);

        return baseDTO;
    }

    public void deleteBase(Integer id) {
        Optional<Base> baseOptional = baseRepository.findById(id);

        if (baseOptional.isEmpty()) {
            throw new BaseNotFoundException();
        } else {
           baseRepository.delete(baseOptional.get());
        }
    }
}
