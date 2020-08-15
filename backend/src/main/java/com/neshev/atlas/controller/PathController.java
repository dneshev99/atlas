package com.neshev.atlas.controller;

import com.neshev.atlas.dto.PathDTO;
import com.neshev.atlas.exception.PathNotFoundException;
import com.neshev.atlas.service.PathService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("path")
public class PathController {
    private final PathService pathService;

    @GetMapping(path = "/")
    public ResponseEntity<List<PathDTO>> getAll() {
        return ResponseEntity.ok(pathService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PathDTO> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(pathService.findById(id));
        } catch (PathNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping(path = "/")
    public ResponseEntity<PathDTO> createPath(@RequestBody PathDTO pathDTO) {
        return ResponseEntity.status(201).body(pathService.createPath(pathDTO));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PathDTO> deletePath(@PathVariable Integer id) {
        try {
            pathService.deletePath(id);
            return ResponseEntity.noContent().build();
        } catch (PathNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }
}
