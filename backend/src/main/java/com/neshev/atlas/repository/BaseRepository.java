package com.neshev.atlas.repository;

import com.neshev.atlas.entity.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository extends JpaRepository<Base, Integer> {
}
