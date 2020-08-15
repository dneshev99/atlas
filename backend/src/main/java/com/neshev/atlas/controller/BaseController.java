package com.neshev.atlas.controller;

import com.neshev.atlas.dto.BaseDTO;
import com.neshev.atlas.exception.BaseNotFoundException;
import com.neshev.atlas.exception.RegionNotFoundException;
import com.neshev.atlas.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("base")
public class BaseController {
    private final BaseService baseService;

    @GetMapping(path = "/")
    public ResponseEntity<List<BaseDTO>> getAll() {
            return ResponseEntity.ok(baseService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BaseDTO> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(baseService.findById(id));
        } catch (BaseNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<BaseDTO> createBase(@RequestBody BaseDTO baseDTO) {
        try {
            return ResponseEntity.status(201).body(baseService.createBase(baseDTO));
        } catch (RegionNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseDTO> deleteBase(@PathVariable Integer id) {
        try {
           baseService.deleteBase(id);
            return ResponseEntity.noContent().build();
        } catch (BaseNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }
}
