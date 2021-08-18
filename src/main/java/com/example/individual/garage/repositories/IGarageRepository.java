package com.example.individual.garage.repositories;

import com.example.individual.garage.entities.Garage;
import com.example.individual.garage.enums.GeneralEnums;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  IGarageRepository extends JpaRepository<Garage,Long> {

    List<Garage> findAllByStatu(GeneralEnums.Status status);

}
