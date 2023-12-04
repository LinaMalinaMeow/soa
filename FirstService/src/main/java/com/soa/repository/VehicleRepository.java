package com.soa.repository;

import com.soa.dto.FilterQueryDto;
import com.soa.dto.VehicleType;
import com.soa.entity.VehicleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select v from VehicleEntity v where (:#{#dto.name} is null or v.name = :#{#dto.name})"
            + "and (:#{#dto.id} is null or v.id = :#{#dto.id})"
            + "and (:#{#dto.minCoordinatesX} is null or v.coordinates.x >= :#{#dto.minCoordinatesX})"
            + "and (:#{#dto.maxCoordinatesX} is null or v.coordinates.x <= :#{#dto.maxCoordinatesX})"
            + "and (:#{#dto.minCoordinatesY} is null or v.coordinates.y >= :#{#dto.minCoordinatesY})"
            + "and (:#{#dto.maxCoordinatesY} is null or v.coordinates.y <= :#{#dto.maxCoordinatesY})"
            + "and (:#{#dto.minEnginePower} is null or v.enginePower >= :#{#dto.minEnginePower})"
            + "and (:#{#dto.maxEnginePower} is null or v.enginePower <= :#{#dto.maxEnginePower})"
            + "and (:#{#dto.type} is null or v.type = :#{#dto.type})"
            + "and (:#{#dto.fuelType} is null or v.fuelType = :#{#dto.fuelType})"
    )
    List<VehicleEntity> getVehiclesWithFiltering(@Param("dto") FilterQueryDto dto, Pageable pageable);
}
