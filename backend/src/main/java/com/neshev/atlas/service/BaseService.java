package com.neshev.atlas.service;

import com.neshev.atlas.entity.Base;
import com.neshev.atlas.exception.BaseNotFoundException;
import com.neshev.atlas.repository.BaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BaseService {
    private final BaseRepository baseRepository;

    public List<Base> findAll() {
        return baseRepository.findAll();
    }

    public Base findById(Integer id) {
        return baseRepository.findById(id).orElseThrow(() -> new BaseNotFoundException());
    }
}
