package ru.com.api.controllers;

import com.service.VehicleService;
import com.service.dto.VehicleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.com.api.util.JndiUtils;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class VehicleController {

    @GetMapping("/add-wheels/{id}/{count}")
    public VehicleDTO addWheels(@PathVariable("id") String id, @PathVariable("count") String count) {
        return getService().setWhells(id, count);
    }

    @GetMapping("/search/by-engine-power/{from}/{to}")
    public List<VehicleDTO> searchByEnginePower(@PathVariable("from") String from, @PathVariable("to") String to) {
        return getService().getVehiclesBetween(from, to);
    }


    private VehicleService getService() {
        return JndiUtils.getFromContext(VehicleService.class,
                "ejb:/logic-1.0-SNAPSHOT-jar-with-dependencies/VehicleServiceImpl!com.service.VehicleService");
    }

}
