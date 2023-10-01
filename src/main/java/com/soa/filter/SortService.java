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
public class SortService implements FilterableParams {
    private final String SORT_PARAM = "sort";
    private final String SORT_DESC_PARAM = "desc";
    private final String SORT_ASC_PARAM = "asc";
    private final String DEFAULT_SORT_FIELD = "id";


    public Boolean isDescendingOrder(Map<String, String> requestParams) {
        if (requestParams.containsKey(SORT_PARAM)) {
            if (SORT_DESC_PARAM.equals(requestParams.get(SORT_PARAM))) {
                return true;
            } else if (SORT_ASC_PARAM.equals(requestParams.get(SORT_PARAM))) {
                return false;
            }
        }
        return null;
    }

    @Override
    public void addFilterParam(List<Predicate> predicates, CriteriaBuilder cb, Root<VehicleEntity> root, CriteriaQuery<VehicleEntity> cq, Map<String, String> requestParams) {
        Boolean isDescendingOrder = isDescendingOrder(requestParams);
        if (isDescendingOrder != null) {
            if (Boolean.TRUE.equals(isDescendingOrder)) {
                cq.orderBy(cb.desc(root.get(DEFAULT_SORT_FIELD)));
            } else {
                cq.orderBy(cb.asc(root.get(DEFAULT_SORT_FIELD)));
            }
        }
        ClearRequestParamsService.clearSortParams(requestParams);
    }
}
