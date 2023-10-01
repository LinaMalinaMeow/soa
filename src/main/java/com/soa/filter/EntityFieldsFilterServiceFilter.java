package com.soa.filter;

import com.soa.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EntityFieldsFilterServiceFilter implements EntityFieldsParamsFilter {
    @Override
    public void addFilterParam(List<Predicate> predicates, CriteriaBuilder cb, Root<VehicleEntity> root, CriteriaQuery<VehicleEntity> cq, Map<String, String> requestParams) {
        for (String key : requestParams.keySet()) {
            predicates.add(cb.equal(root.get(key), requestParams.get(key)));
        }
    }
}
