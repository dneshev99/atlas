package com.neshev.atlas.dto;

import lombok.Data;

@Data
public class RegionDTO {
    private String name;
    private String code;
    private CountryDTO country = new CountryDTO();
}
