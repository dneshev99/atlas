package com.neshev.atlas.controller;

import com.neshev.atlas.dto.RegionDTO;
import com.neshev.atlas.dto.RegionLessDTO;
import com.neshev.atlas.exception.CountryNotFoundException;
import com.neshev.atlas.exception.RegionNotFoundException;
import com.neshev.atlas.service.RegionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("region")
public class RegionController {
    private final RegionService regionService;

    @GetMapping(path = "/")
    public ResponseEntity<List<RegionDTO>> getAll() {
        return ResponseEntity.ok(regionService.findAll());
    }

    @GetMapping(path = "/less")
    public ResponseEntity<List<RegionLessDTO>> getAllLess() {
        return ResponseEntity.ok(regionService.findAllLess());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RegionDTO> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(regionService.findById(id));
        } catch (RegionNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping(path = "/country/{countryCode}")
    public ResponseEntity<List<RegionDTO>> getAllByCountry(@PathVariable String countryCode) {
        try {
            return ResponseEntity.ok(regionService.findAllByCountryCode(countryCode));
        } catch (CountryNotFoundException | RegionNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<RegionDTO> createRegion(@RequestBody RegionDTO regionDTO) {
        try {
            return ResponseEntity.status(201).body(regionService.createRegion(regionDTO));
        } catch (CountryNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<RegionDTO> deleteRegion(@PathVariable Integer id) {
        try {
            regionService.deleteRegion(id);
            return ResponseEntity.noContent().build();
        } catch (RegionNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }
}
