package com.neshev.atlas.dto;

import lombok.Data;

import java.util.List;

@Data
public class PathDTO {
    private Double price;
    private Integer length;
    private List<PointDTO> points;
}
