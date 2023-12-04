package com.service;


import com.service.dto.VehicleDTO;
import com.service.dto.VehiclesListDTO;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface VehicleService {

    List<VehicleDTO> getVehiclesBetween(String from, String to);

    VehicleDTO setWhells(String id, String count);
}
