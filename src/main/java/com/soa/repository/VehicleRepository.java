package com.soa.repository;

import com.soa.dto.VehicleType;
import com.soa.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {
    void removeAllByType(VehicleType type);

    List<VehicleEntity> findAllByType(VehicleType type);

    @Query("select sum(v.enginePower) from VehicleEntity v")
    BigDecimal findSumOfAllVehiclesEnginePower();

    @Query("select avg(v.enginePower) from VehicleEntity v")
    BigDecimal findAverageVehiclesEnginePower();
}
