package com.soa.filter;

import com.soa.dto.PageDto;
import com.soa.entity.VehicleEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class PageService implements PageableParams<VehicleEntity> {
    private final int DEFAULT_PAGE_LIMIT = 3;
    private final String PAGE_LIMIT_PARAM = "limit";
    private final int DEFAULT_PAGE_OFFSET = 0;
    private final String PAGE_OFFSET_PARAM = "offset";


    public int getPageLimit(Map<String, String> requestParams) {
        if (requestParams.containsKey(PAGE_LIMIT_PARAM)) {
            return Integer.parseInt(requestParams.get(PAGE_LIMIT_PARAM));
        }
        return DEFAULT_PAGE_LIMIT;
    }

    public int getPageOffset(Map<String, String> requestParams) {
        if (requestParams.containsKey(PAGE_OFFSET_PARAM)) {
            return Integer.parseInt(requestParams.get(PAGE_OFFSET_PARAM));
        }
        return DEFAULT_PAGE_OFFSET;
    }

    @Override
    public PageDto addPageParams(Map<String, String> requestParams) {
        PageDto dto = new PageDto();
        dto.setLimit(getPageLimit(requestParams)).setOffset(getPageOffset(requestParams));
        ClearRequestParamsService.clearPageParams(requestParams);
        return dto;
    }
}
