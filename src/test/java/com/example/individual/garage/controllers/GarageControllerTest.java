package com.example.individual.garage.controllers;

import com.example.individual.garage.common.UrlProvider;
import com.example.individual.garage.requests.RequestParkVehicle;
import com.example.individual.garage.services.IGarageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GarageController.class)
public class GarageControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    IGarageService iGarageService;


    @Test
    public void whenGetStatus_thenShouldReturnStatusOK() throws Exception {
        when(iGarageService.status()).thenReturn(new String());
        mockMvc.perform(MockMvcRequestBuilders.get(UrlProvider.GARAGE_MODULE+UrlProvider.GARAGE_MODULE_STATUS).contentType(MediaType.APPLICATION_JSON_VALUE))
                 .andExpect(MockMvcResultMatchers.status().isOk());
        verify(iGarageService, times(1)).status();
    }

    @Test
    public void whenCallPark_thenShouldReturnStatusOK() throws Exception {
        when(iGarageService.park(any(RequestParkVehicle.class))).thenReturn(new String());
        mockMvc.perform(MockMvcRequestBuilders.post(UrlProvider.GARAGE_MODULE+UrlProvider.GARAGE_MODULE_PARK).contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(asJsonString(new RequestParkVehicle("06ABC06","RED","CAR"))))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(iGarageService, times(1)).park(any(RequestParkVehicle.class));
     }

    @Test
    public void whenCallleave_thenShouldReturnStatusOK() throws Exception {
        when(iGarageService.leave(any(Long.class))).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete(UrlProvider.GARAGE_MODULE+UrlProvider.GARAGE_MODULE_LEAVE+"/{id}","06").contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(iGarageService, times(1)).leave(any(Long.class));
    }




    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
