package com.soa.filter;

import com.soa.entity.VehicleEntity;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

public interface FilterableParams {
    void addFilterParam(List<Predicate> predicates, CriteriaBuilder cb, Root<VehicleEntity> root, CriteriaQuery<VehicleEntity> cq, Map<String, String> requestParams);
}
