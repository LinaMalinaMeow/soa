package com.soa.service.db;

import com.soa.dto.FilterQueryDto;
import com.soa.dto.VehicleType;
import com.soa.entity.VehicleEntity;
import com.soa.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleDbService {
    private VehicleRepository vehicleRepository;

    @Transactional
    public VehicleEntity saveVehicle(VehicleEntity vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Transactional(readOnly = true)
    public Optional<VehicleEntity> findById(Integer vehicleId) {
        return vehicleRepository.findById(vehicleId);
    }

    @Transactional(readOnly = true)
    public BigDecimal findSumOfAllVehiclesEnginePower() {
        return vehicleRepository.findSumOfAllVehiclesEnginePower();
    }

    @Transactional(readOnly = true)
    public BigDecimal findAverageVehiclesEnginePower() {
        return vehicleRepository.findAverageVehiclesEnginePower();
    }

    @Transactional(readOnly = true)
    public List<VehicleEntity> findAllByType(VehicleType type) {
        return vehicleRepository.findAllByType(type);
    }

    @Transactional
    public VehicleEntity delete(VehicleEntity vehicle) {
        vehicleRepository.delete(vehicle);
        return vehicle;
    }

    @Transactional
    public void removeAllByType(VehicleType type) {
        vehicleRepository.removeAllByType(type);
    }

    @Transactional
    public List<VehicleEntity> getVehicles(FilterQueryDto dto, Pageable pageable) {
        return vehicleRepository.getVehiclesWithFiltering(dto, pageable);
    }
}
