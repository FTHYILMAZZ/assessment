package com.example.individual.garage.controllers;

import com.example.individual.garage.GarageUtil;
import com.example.individual.garage.common.UrlProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class GarageControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void whenGetStatus_thenShouldReturnStatusWithStatusOk(){
       ResponseEntity<String> response= testRestTemplate.getForEntity(UrlProvider.GARAGE_MODULE+UrlProvider.GARAGE_MODULE_STATUS,String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void whenPostPark_thenShouldReturnTicketIdWithStatusOK(){
        ResponseEntity<String> response= testRestTemplate.postForEntity(UrlProvider.GARAGE_MODULE+UrlProvider.GARAGE_MODULE_PARK, GarageUtil.getDefaultRequest(),String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void whenLeave_thenShouldReturnStatusOk(){
        HttpEntity<Long> longHttpEntity= new HttpEntity<Long>(156L);
        ResponseEntity<String> response = testRestTemplate.exchange(UrlProvider.GARAGE_MODULE+UrlProvider.GARAGE_MODULE_LEAVE, HttpMethod.DELETE,longHttpEntity,String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

}
