package com.neshev.atlas.controller;

import com.neshev.atlas.entity.Base;
import com.neshev.atlas.exception.BaseNotFoundException;
import com.neshev.atlas.service.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("base")
public class BaseController {
    private final BaseService baseService;

    @GetMapping(path = "/")
    public ResponseEntity<List<Base>> getAll() {
        try {
            return ResponseEntity.ok(baseService.findAll());
        } catch (BaseNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Base> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(baseService.findById(id));
        } catch (BaseNotFoundException e) {
            return ResponseEntity.status(400).build();
        }
    }
}
