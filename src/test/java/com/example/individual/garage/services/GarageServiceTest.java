package com.example.individual.garage.services;

import com.example.individual.garage.GarageUtil;
import com.example.individual.garage.common.exceptions.VehicleTypeException;
import com.example.individual.garage.entities.Garage;
import com.example.individual.garage.enums.GeneralEnums;
import com.example.individual.garage.repositories.IGarageRepository;
import com.example.individual.garage.requests.RequestParkVehicle;
import com.example.individual.garage.services.impl.GarageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class GarageServiceTest {
    @Mock
    IGarageRepository iGarageRepository;
    @InjectMocks
    @Spy
    GarageServiceImpl iGarageService;

@Nested
    class park{

    @Test
    void givenNull_whenPark_thenReturnVehicleTypeException(){
        Exception exception = Assertions.assertThrows(VehicleTypeException.class, () ->{
            iGarageService.park(new RequestParkVehicle());
        });
        Assertions.assertTrue(exception.getMessage().contains("Specified vehicle is not defined"));

    }

    @Test
    void givenRequest_whenPark_thenReturnTickedID(){
        Garage savedData = new Garage();
        savedData.setTicketId(3L);
        when(iGarageRepository.save(any(Garage.class))).thenReturn(savedData);
        String response = iGarageService.park(GarageUtil.getDefaultRequest());
        verify(iGarageRepository,times(1)).save(any(Garage.class));

        Assertions.assertNotNull(response);
       Assertions.assertTrue(response.contains("Allocated"));
    }

    @Test
    void givenRequest_whenPark_thenReturnGarageFull(){
        List<Garage> slots=new ArrayList<>();
        Mockito.doReturn(new ArrayList<>()).when(iGarageService).getAvaibleSlots(GeneralEnums.Status.ACTIVE);
        String response = iGarageService.park(GarageUtil.getDefaultRequest());
        verify(iGarageService,times(1)).getAvaibleSlots(any(GeneralEnums.Status.class));
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.contains("Garage is full."));
    }

}

    @Nested
    class leave{

    @Test
    void givenRequest_whenLeave_thenReturnTrue(){
        doNothing().when(iGarageRepository).deleteById(any(Long.class));
        boolean response = iGarageService.leave(999L);
        verify(iGarageRepository,times(1)).deleteById(any(Long.class));

    }
}

    @Nested
    class status{

        @Test
        void givenRequest_whenStatus_thenReturnStatusInfo(){
            List<Garage> allActiveData = new ArrayList<>();
            when(iGarageRepository.findAllByStatu(any(GeneralEnums.Status.class))).thenReturn(allActiveData);
            String response = iGarageService.status();
            verify(iGarageRepository,times(1)).findAllByStatu(any(GeneralEnums.Status.class));
            Assertions.assertNotNull(response);
            Assertions.assertTrue(response.length()>0);


        }
    }

}
