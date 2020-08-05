package com.neshev.atlas.controller;

import com.neshev.atlas.entity.Base;
import com.neshev.atlas.entity.Path;
import com.neshev.atlas.exception.BaseNotFoundException;
import com.neshev.atlas.exception.PathNotFoundException;
import com.neshev.atlas.repository.PathRepository;
import com.neshev.atlas.service.PathService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("path")
public class PathController {
    private final PathService pathService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Path>> getAll() {
        try {
            return ResponseEntity.ok(pathService.findAll());
        } catch (PathNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Path> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(pathService.findById(id));
        } catch (PathNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }
}
