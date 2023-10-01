package com.soa.filter;

import com.soa.entity.CoordinatesEntity;
import com.soa.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;


@Service
@AllArgsConstructor
public class CoordinatesService implements FilterableParams {
    private final String COORDINATES_PARAM = "coordinates";
    private final String COORDINATES_X_PARAM = "x";
    private final String COORDINATES_Y_PARAM = "y";

    private String getCoordinatesXParam() {
        return COORDINATES_PARAM + "_" + COORDINATES_X_PARAM;
    }

    private String getCoordinatesYParam() {
        return COORDINATES_PARAM + "_" + COORDINATES_Y_PARAM;
    }

    @Override
    public void addFilterParam(List<Predicate> predicates, CriteriaBuilder cb, Root<VehicleEntity> root, CriteriaQuery<VehicleEntity> cq, Map<String, String> requestParams) {
        Join<VehicleEntity, CoordinatesEntity> coordinatesJoin = root.join(COORDINATES_PARAM);

        if (requestParams.containsKey(getCoordinatesXParam())) {
            predicates.add(cb.equal(coordinatesJoin.get(COORDINATES_X_PARAM), requestParams.getOrDefault(getCoordinatesXParam(), "")));
        }
        if (requestParams.containsKey(getCoordinatesYParam())) {
            predicates.add(cb.equal(coordinatesJoin.get(COORDINATES_Y_PARAM), requestParams.getOrDefault(getCoordinatesYParam(), "")));
        }
        ClearRequestParamsService.clearCoordinatesParams(requestParams);
    }
}
