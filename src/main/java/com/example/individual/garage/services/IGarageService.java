package com.example.individual.garage.services;

import com.example.individual.garage.requests.RequestParkVehicle;

public interface IGarageService {
    String park(RequestParkVehicle request);

    boolean leave(Long id);

    String status();
}
