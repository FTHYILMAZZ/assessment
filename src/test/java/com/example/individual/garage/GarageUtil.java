package com.example.individual.garage;

import com.example.individual.garage.requests.RequestParkVehicle;

public class GarageUtil {

    public static RequestParkVehicle getDefaultRequest(){
        RequestParkVehicle requestParkVehicle = new RequestParkVehicle();
        requestParkVehicle.setVehicleType("CAR");
        requestParkVehicle.setColor("RED");
        requestParkVehicle.setPlate("06ABC06");
        return requestParkVehicle;
    }
}
