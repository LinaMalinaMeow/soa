package com.soa.service.db;

import com.soa.dto.PageDto;
import com.soa.entity.VehicleEntity;
import com.soa.filter.EntityFieldsParamsFilter;
import com.soa.filter.FilterService;
import com.soa.filter.FilterableParams;
import com.soa.filter.PageableParams;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class VehicleCriteriaService {
    private List<FilterableParams> filters;
    private List<EntityFieldsParamsFilter> entityFieldsParamsFilter;
    private PageableParams<VehicleEntity> pageableParams;


    @PersistenceContext
    private EntityManager em;

    public List<VehicleEntity> getVehicles(Map<String, String> requestParams) {
        FilterService.isValidRequestParams(requestParams);

        PageDto pageDto = pageableParams.addPageParams(requestParams);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<VehicleEntity> cq = cb.createQuery(VehicleEntity.class);

        Root<VehicleEntity> root = cq.from(VehicleEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        for (FilterableParams filterService : filters) {
            filterService.addFilterParam(predicates, cb, root, cq, requestParams);
        }
        for (EntityFieldsParamsFilter filter : entityFieldsParamsFilter) {
            filter.addFilterParam(predicates, cb, root, cq, requestParams);
        }
        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<VehicleEntity> query = em.createQuery(cq);
        addPageParamsToQuery(query, pageDto);

        return query.getResultList();
    }

    private <T> void addPageParamsToQuery(TypedQuery<T> query, PageDto dto) {
        query.setFirstResult(dto.getOffset()).setMaxResults(dto.getLimit());
    }
}
