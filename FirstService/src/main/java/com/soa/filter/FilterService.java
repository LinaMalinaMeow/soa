package com.soa.filter;

import com.soa.dto.FilterQueryDto;
import com.soa.exception.NotValidParamsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class FilterService {
    public static void isValidRequestParams(FilterQueryDto dto) {
        if (dto.getId() != null) {
            checkNumberIsPositive("Id", dto.getId());
        }
        if (dto.getName() != null) {
            checkValidName(dto.getName());
        }
        if (dto.getSort() != null) {
            checkSortParams(dto.getSort());
        }
        if (dto.getMinEnginePower() != null) {
            checkValidEnginePower(dto.getMinEnginePower());
        }
        if (dto.getMaxEnginePower() != null) {
            checkValidEnginePower(dto.getMaxEnginePower());
        }
        if (dto.getMaxEnginePower() != null && dto.getMinEnginePower() != null) {
            checkMinEnginePowerIsSmallerThanMaxEnginePower(dto.getMinEnginePower(), dto.getMaxEnginePower());
        }
        if (dto.getLimit() != null) {
            checkNumberIsPositive("Limit", dto.getLimit());
        }
        if (dto.getOffset() != null) {
            checkNumberIsPositive("Offset", dto.getOffset());
        }
    }

    public static void checkValidName(String name) {
        checkEmpty(name);
        if (name.length() < 3) {
            throw new NotValidParamsException("Длина имени должна быть больше 3");
        }
    }

    public static void checkSortParams(String sort) {
        checkEmpty(sort);
        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new NotValidParamsException("Неверный параметр сортировки");
        }
    }

    public static void checkNumberIsPositive(String nameOfField, Integer number) {
        if (number <= 0) {
            throw new NotValidParamsException(nameOfField + " должен быть больше 0");
        }
    }

    public static void checkMinEnginePowerIsSmallerThanMaxEnginePower(BigDecimal min, BigDecimal max) {
        if (max.compareTo(min) < 0) {
            throw new NotValidParamsException("Максимальное значение мощности должно быть меньше минимального");
        }
    }

    public static void checkValidEnginePower(BigDecimal enginePower) {
        if (enginePower.compareTo(BigDecimal.valueOf(0L)) < 0) {
            throw new NotValidParamsException("Сила двигателя должна быть больше 0");
        }
    }

    public static void checkEmpty(String value) {
        if (value == null || value.isBlank()) {
            throw new NotValidParamsException("Поля не должны быть пустыми");
        }
    }
}
