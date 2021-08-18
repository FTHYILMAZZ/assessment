package com.example.individual.garage;

import com.example.individual.garage.controllers.GarageController;
import com.example.individual.garage.enums.GeneralEnums;
import com.example.individual.garage.requests.RequestParkVehicle;
import com.example.individual.garage.services.IGarageService;
import com.example.individual.garage.services.impl.GarageServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

@SpringBootApplication
public class GarageApplication {


	public static void main(String[] args) {
		SpringApplication.run(GarageApplication.class, args);

	}

}
