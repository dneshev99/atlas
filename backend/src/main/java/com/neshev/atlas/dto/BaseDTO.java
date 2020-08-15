package com.neshev.atlas.dto;

import lombok.Data;

@Data
public class BaseDTO {
    private String name;
    private Double lat;
    private Double lng;
    private Integer capacity;
    private RegionDTO region = new RegionDTO();
}
