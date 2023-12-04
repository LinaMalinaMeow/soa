package com.soa.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SortService {
    public Sort getSort(String sort) {
        if ("desc".equals(sort)) {
            return Sort.by("id").descending();
        } else {
            return Sort.by("id").ascending();
        }
    }
}
