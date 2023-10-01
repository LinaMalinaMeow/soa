package com.soa.filter;

import com.soa.dto.FuelType;
import com.soa.dto.VehicleType;
import com.soa.exception.NotValidParamsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

@Service
@AllArgsConstructor
public class FilterService {
    public static void isValidRequestParams(Map<String, String> requestParams) {
        for (String key : requestParams.keySet()) {
            switch (key) {
                case "id" -> checkValidId(requestParams.get(key));
                case "name" -> checkValidName(requestParams.get(key));
                case "coordinates_x", "coordinates_y" -> checkValidCoordinates(requestParams.get(key));
                case "creation_date" -> checkCreationDate(requestParams.get(key));
                case "sort" -> checkSortParams(requestParams.get(key));
                case "engine_power" -> checkValidEnginePower(requestParams.get(key));
                case "limit", "offset" -> checkValidPageParams(requestParams.get(key));
                case "type" -> {
                    requestParams.put(requestParams.get(key), requestParams.get(key).toUpperCase(Locale.ROOT));
                    checkValidVehicleType(requestParams.get(key));
                }
                case "fuel_type" -> {
                    requestParams.put(requestParams.get(key), requestParams.get(key).toUpperCase(Locale.ROOT));
                    checkValidFuelType(requestParams.get(key));
                }
                default -> throw new NotValidParamsException("Введен невалидный набор параметров для фильтрации");
            }
        }
    }

    public static void checkValidName(String name) {
        checkEmpty(name);
        if (name.length() < 3) {
            throw new NotValidParamsException("Длина имени должна быть больше 3");
        }
    }

    public static void checkCreationDate(String date) {
        checkEmpty(date);
        if (!isValidDate(date)) {
            throw new NotValidParamsException("Неверная дата создания");
        }
    }

    public static void checkSortParams(String sort) {
        checkEmpty(sort);
        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new NotValidParamsException("Неверный параметр сортировки");
        }
    }

    public static void checkValidCoordinates(String coordinates) {
        checkEmpty(coordinates);
        if (!isNumeric(coordinates)) {
            throw new NotValidParamsException("Координаты должны быть числами");
        }
    }

    public static void checkValidPageParams(String param) {
        checkEmpty(param);
        if (!isIntegerValue(param)) {
            throw new NotValidParamsException("Параметры пагинации должны быть целыми числами");
        }
    }

    public static void checkValidId(String id) {
        checkEmpty(id);
        if (!isIntegerValue(id)) {
            throw new NotValidParamsException("ID должен быть целым числом");
        }
    }

    public static void checkValidEnginePower(String enginePower) {
        checkEmpty(enginePower);
        if (!isNumeric(enginePower)) {
            throw new NotValidParamsException("Сила двигателя должна быть числом");
        }
        if (Double.parseDouble(enginePower) <= 0) {
            throw new NotValidParamsException("Сила двигателя должна быть больше 0");
        }
    }

    public static void checkValidVehicleType(String type) {
        checkEmpty(type);
        if (!isValidEnum(type, VehicleType.class)) {
            throw new NotValidParamsException("Неверно указан тип машины");
        }
    }

    public static void checkValidFuelType(String type) {
        checkEmpty(type);
        if (!isValidEnum(type, FuelType.class)) {
            throw new NotValidParamsException("Неверно указан тип топлива машины");
        }
    }

    public static void checkEmpty(String value) {
        if (value == null || value.isBlank()) {
            throw new NotValidParamsException("Поля не должны быть пустыми");
        }
    }

    public static boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isIntegerValue(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyyThh:mm:ss:mmZ");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static <E extends Enum<E>> boolean isValidEnum(String type, Class<E> enumData) {
        for (Enum<E> value : enumData.getEnumConstants()) {
            if (value.name().equals(type)) {
                return true;
            }
        }

        return false;
    }
}
