package com.neshev.atlas.service;

import com.neshev.atlas.dto.PathDTO;
import com.neshev.atlas.dto.PointDTO;
import com.neshev.atlas.entity.Path;
import com.neshev.atlas.entity.Point;
import com.neshev.atlas.exception.PathNotFoundException;
import com.neshev.atlas.repository.PathRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PathService {
    private final PathRepository pathRepository;
    private final ModelMapper mapper;

    public List<PathDTO> findAll() {
       List<PathDTO> dtos = new ArrayList<>();

        for (Path path : pathRepository.findAll()) {
            PathDTO dto = mapper.map(path, PathDTO.class);

            dto.setPoints(path.getPoints().stream()
                                          .map(point -> mapper.map(point, PointDTO.class))
                                          .collect(Collectors.toList()));

            dtos.add(dto);
        }
        return dtos;
    }

    public PathDTO findById(Integer id) {
        Optional<Path> pathOptional = pathRepository.findById(id);

        if (pathOptional.isEmpty()) {
            throw new PathNotFoundException();
        } else {
            Path path = pathOptional.get();

            PathDTO dto = mapper.map(path, PathDTO.class);

            dto.setPoints(path.getPoints().stream()
                    .map(point -> mapper.map(point, PointDTO.class))
                    .collect(Collectors.toList()));

            return dto;
        }
    }

    public PathDTO createPath(PathDTO pathDTO) {
        Path newPath = mapper.map(pathDTO, Path.class);
        newPath.setPoints(pathDTO.getPoints().stream()
                                             .map(point -> mapper.map(point, Point.class))
                                             .collect(Collectors.toList()));

        pathRepository.save(newPath);
        return pathDTO;
    }

    public void deletePath(Integer id) {
        Optional<Path> pathOptional = pathRepository.findById(id);

        if (pathOptional.isEmpty()) {
            throw new PathNotFoundException();
        } else {
            pathRepository.delete(pathOptional.get());
        }
    }
}
