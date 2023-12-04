package com.soa.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PageService {
    private final int DEFAULT_LIMIT = 3;
    private final int DEFAULT_OFFSET = 0;

    private SortService sortService;

    public PageRequest getPageRequest(Integer limitParam, Integer offsetParam, String sort) {
        int limit = limitParam == null ? DEFAULT_LIMIT : limitParam;
        int offset = offsetParam == null ? DEFAULT_OFFSET : offsetParam;
        int page = offset / limit;
        return PageRequest.of(page, limit, sortService.getSort(sort));
    }
}
