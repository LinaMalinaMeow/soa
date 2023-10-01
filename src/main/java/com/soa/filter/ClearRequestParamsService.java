package com.soa.filter;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@AllArgsConstructor
public class ClearRequestParamsService {
    private final static Set<String> coordinatesArgs = Set.of("coordinates_x", "coordinates_y");
    private final static Set<String> pageArgs = Set.of("limit", "offset");
    private final static Set<String> sortArgs = Set.of("sort");

    public static void clearCoordinatesParams(Map<String, String> map) {
        removeKeysFromMap(map, coordinatesArgs);
    }

    public static void clearPageParams(Map<String, String> map) {
        removeKeysFromMap(map, pageArgs);
    }

    public static void clearSortParams(Map<String, String> map) {
        removeKeysFromMap(map, sortArgs);
    }

    public static void removeKeysFromMap(Map<String, String> map, Set<String> removeKeys) {
        for (String key : removeKeys) {
            map.remove(key);
        }
    }
}
