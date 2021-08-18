package com.example.individual.garage.services.impl;

import com.example.individual.garage.common.exceptions.GeneralException;
import com.example.individual.garage.common.exceptions.NoTicketIdException;
import com.example.individual.garage.common.exceptions.VehicleTypeException;
import com.example.individual.garage.entities.Garage;
import com.example.individual.garage.enums.GeneralEnums;
import com.example.individual.garage.repositories.IGarageRepository;
import com.example.individual.garage.requests.RequestParkVehicle;
import com.example.individual.garage.services.IGarageService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class GarageServiceImpl implements IGarageService {

    private final IGarageRepository iGarageRepository;

    public GarageServiceImpl(IGarageRepository iGarageRepository) {
        this.iGarageRepository = iGarageRepository;
    }

    @Override
    public String park(RequestParkVehicle request) {
        try {
            if (request == null || request.getVehicleType() == null || !Arrays.stream(GeneralEnums.VehicleType.values()).anyMatch(x -> x.getType().equals(request.getVehicleType().toUpperCase()))) {
                throw new RuntimeException(); // Custom Exception Yaz
            }
            GeneralEnums.VehicleType vehicleType = null;
            for (GeneralEnums.VehicleType type : GeneralEnums.VehicleType.values()) {
                if (type.getType().equals(request.getVehicleType().toUpperCase())) {
                    vehicleType = type;
                    break;
                }
            }
            List<String> avaibleSlots = getAvaibleSlots(GeneralEnums.Status.ACTIVE);
            Integer avaibleParkingSlot = findAvaibleSlotForParking(avaibleSlots, vehicleType.getSlotWidth());
            if (avaibleParkingSlot != -5) { // Dummy value for full slots
                Garage garageEntity = new Garage();
                garageEntity.setColor(request.getColor());
                garageEntity.setPlate(request.getPlate());
                garageEntity.setVehicleType(vehicleType);
                List<String> vehicleSlotList = new ArrayList<>();
                for (int i = avaibleParkingSlot; i < avaibleParkingSlot + vehicleType.getSlotWidth(); i++) {
                    vehicleSlotList.add(String.valueOf(i));
                }
                garageEntity.setSlots(String.join(";", vehicleSlotList));
                Garage response = iGarageRepository.save(garageEntity);
                return "Allocated " + vehicleType.getSlotWidth() + " slot. Customer Ticket Id:" + String.valueOf(response.getTicketId());
            }

            return "Garage is full.";
        } catch (RuntimeException e) {
            throw new VehicleTypeException(GeneralEnums.Exceptions.VehicleTypeException.getMessage(e));
        }
        catch (Exception e) {
            throw new GeneralException(GeneralEnums.Exceptions.GeneralException.getMessage(e));
        }
    }

    //reason of public metod that need access of test classes
    public List<String> getAvaibleSlots(GeneralEnums.Status activeStatu) {
        //Generate Slotset 1 to garageSlotCapacity
        final int garageSlotCapacity = 10;
        List<String> allSlots = new ArrayList<>();
        IntStream.range(1, garageSlotCapacity + 1).boxed().forEach(x -> allSlots.add(String.valueOf(x)));

        //Find filled slots
        List<Garage> activeGarageList = iGarageRepository.findAllByStatu(activeStatu);
        List<String> unAvaibleSlots = new ArrayList<>();
        activeGarageList.stream().map(Garage::getSlots).forEach(x -> unAvaibleSlots.addAll(Arrays.asList(x.split(";"))));
        //Find empty slots
        allSlots.removeAll(unAvaibleSlots);

        return allSlots;
    }


    private Integer findAvaibleSlotForParking(List<String> avaibleSlots, int vehicleWidth) {
        for (String slotItem : avaibleSlots) {
            String dbSlotData = "";
            int i = Integer.parseInt(slotItem);
            for (int j = i + 1; j < i + vehicleWidth + 1; j++) {  // j<i+vehicleWidth loop for to check if there is enough for the jeep or truck
                if (((i != 1 && avaibleSlots.contains(String.valueOf(i - 1)) && avaibleSlots.contains(String.valueOf(j))) || (i == 1 && avaibleSlots.contains(String.valueOf(j)))) && j == (i + vehicleWidth)) {
                    return i;
                }
            }
        }
        return -5;  // No avaible slots means "Garage is full"
    }

    @Override
    public boolean leave(Long id) {
        try{
            iGarageRepository.deleteById(id);
        }
        catch (Exception e){
            throw new NoTicketIdException(GeneralEnums.Exceptions.NoTicketIdException.getMessage(e));
        }
        return true;
    }

    @Override
    public String status() {
        List<Garage> allActiveData = iGarageRepository.findAllByStatu(GeneralEnums.Status.ACTIVE);
        String header = "Status:".concat(System.lineSeparator() + System.lineSeparator());

        if (allActiveData.isEmpty()) {
            return header + "No vehicles in the garage";
        }
        String response = allActiveData.stream().map(Garage::carInfo).collect(Collectors.joining(System.lineSeparator()));
        header = header + response;
        return header;
    }

}
