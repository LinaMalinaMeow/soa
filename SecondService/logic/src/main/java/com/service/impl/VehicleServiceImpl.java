package com.service.impl;

import com.service.VehicleService;
import com.service.dto.VehicleDTO;
import com.service.dto.VehiclesListDTO;
import org.jboss.ejb3.annotation.Pool;

import javax.ejb.Stateless;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Stateless
@Pool(value = "vehicleServicePool")
public class VehicleServiceImpl implements VehicleService {
    private Client client;
    private final String serviceUrl = "http://haproxy:10000/vehicles";

    public List<VehicleDTO> getVehiclesBetween(String from, String to) {
        String url = serviceUrl + "/new?limit=100&minEnginePower=" + from + "&maxEnginePower=" + to;
        try {
            client = ClientBuilder.newClient();

            VehiclesListDTO response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).get(VehiclesListDTO.class);
            client.close();

            return response.getVehiclesGetResponseDtos();

        } catch (ProcessingException e) {
            client.close();
            return null;
        }
    }

    public VehicleDTO setWhells(String id, String count) {
        String url = serviceUrl + "/" + id + "/add-wheels/" + count;
        try {
            client = ClientBuilder.newClient();

            Response response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).get();
            VehicleDTO vh = response.readEntity(VehicleDTO.class);

            client.close();
            return vh;

        } catch (ProcessingException e) {
            client.close();
            return null;
        }
    }

}
