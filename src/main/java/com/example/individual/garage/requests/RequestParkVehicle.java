package com.example.individual.garage.requests;

import com.example.individual.garage.enums.GeneralEnums;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestParkVehicle {
    @NotNull
    private String plate;
    @NotNull
    private String color;
    @NotNull
    private String vehicleType;

}
