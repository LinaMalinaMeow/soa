package com.soa.filter;

import com.soa.dto.PageDto;

import java.util.Map;

public interface PageableParams<T> {
    PageDto addPageParams(Map<String, String> requestParams);
}
