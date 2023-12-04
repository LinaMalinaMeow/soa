package com.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehiclesListDTO implements Serializable {
    private List<VehicleDTO> vehiclesGetResponseDtos;
}
