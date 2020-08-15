package com.neshev.atlas.service;

import com.neshev.atlas.dto.CountryDTO;
import com.neshev.atlas.entity.Country;
import com.neshev.atlas.exception.CountryNotFoundException;
import com.neshev.atlas.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final ModelMapper mapper;

    @Cacheable("countries")
    public List<CountryDTO> findAll() {
        return countryRepository.findAll().stream()
                                          .map(country -> mapper.map(country, CountryDTO.class))
                                          .collect(Collectors.toList());
    }

    public CountryDTO findById(Integer id) {
        Optional<Country> countryOptional = countryRepository.findById(id);

        if (countryOptional.isEmpty()) {
            throw new CountryNotFoundException();
        } else {
            Country country = countryOptional.get();
            return mapper.map(country, CountryDTO.class);
        }
    }

    public Country findByCode(String countryCode) {
        Optional<Country> countryOptional = countryRepository.findByCode(countryCode);

        return countryOptional.orElseThrow(() -> new CountryNotFoundException());
    }

    @CacheEvict(value="countries",  allEntries = true)
    public CountryDTO createCountry(CountryDTO countryDTO) {
        Country newCountry = mapper.map(countryDTO, Country.class);
        countryRepository.save(newCountry);

        return countryDTO;
    }

    @CacheEvict(value="countries",  allEntries = true)
    public void deleteCountry(Integer id) {
        Optional<Country> countryOptional = countryRepository.findById(id);

        if (countryOptional.isEmpty()) {
            throw new CountryNotFoundException();
        } else {
            countryRepository.delete(countryOptional.get());
        }
    }
}
