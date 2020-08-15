package com.neshev.atlas.repository;

import com.neshev.atlas.entity.Country;
import com.neshev.atlas.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    List<Region> findByCountry(Country country);
    Optional<Region> findByCode(String code);
}
