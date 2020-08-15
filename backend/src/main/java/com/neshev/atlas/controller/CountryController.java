package com.neshev.atlas.controller;

import com.neshev.atlas.dto.BaseDTO;
import com.neshev.atlas.dto.CountryDTO;
import com.neshev.atlas.exception.CountryNotFoundException;
import com.neshev.atlas.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("country")
public class CountryController {
    private final CountryService countryService;

    @GetMapping(path = "/")
    public ResponseEntity<List<CountryDTO>> getAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CountryDTO> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(countryService.findById(id));
        } catch (CountryNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<CountryDTO> createBase(@RequestBody CountryDTO countryDTO) {
        return ResponseEntity.status(201).body(countryService.createCountry(countryDTO));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseDTO> deleteBase(@PathVariable Integer id) {
        try {
            countryService.deleteCountry(id);
            return ResponseEntity.noContent().build();
        } catch (CountryNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }
}
