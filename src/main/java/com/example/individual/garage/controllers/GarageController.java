package com.example.individual.garage.controllers;

import com.example.individual.garage.requests.RequestParkVehicle;
import com.example.individual.garage.services.IGarageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.individual.garage.common.UrlProvider.*;

@RestController
@RequestMapping(GARAGE_MODULE)

public class GarageController {
    private final IGarageService iGarageService;

    public GarageController(IGarageService iGarageService) {
        this.iGarageService = iGarageService;
    }


    @PostMapping(value = GARAGE_MODULE_PARK )
    public ResponseEntity<String> park(@Validated @RequestBody RequestParkVehicle request){
        return ResponseEntity.ok(iGarageService.park(request));
    }

    @DeleteMapping(value = GARAGE_MODULE_LEAVE + "/{id}" )
    public ResponseEntity<String> park(@PathVariable Long id){
       boolean isRemoved =  iGarageService.leave(id);
       if(isRemoved){
           return  new ResponseEntity<>("Leaving Success",HttpStatus.OK);
       } else  {
           return  new ResponseEntity<>("Ticket Id Not Found",HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping(value =GARAGE_MODULE_STATUS )
    public  ResponseEntity<String> status(){
        return ResponseEntity.ok(iGarageService.status());
    }

}
