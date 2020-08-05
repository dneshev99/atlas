package com.neshev.atlas.service;

import com.neshev.atlas.entity.Base;
import com.neshev.atlas.entity.Path;
import com.neshev.atlas.exception.BaseNotFoundException;
import com.neshev.atlas.exception.PathNotFoundException;
import com.neshev.atlas.repository.PathRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PathService {
    private final PathRepository pathRepository;

    public List<Path> findAll() {
        return pathRepository.findAll();
    }

    public Path findById(Integer id) {
        return pathRepository.findById(id).orElseThrow(() -> new PathNotFoundException());
    }
}
