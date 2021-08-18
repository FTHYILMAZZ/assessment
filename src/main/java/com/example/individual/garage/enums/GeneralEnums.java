package com.example.individual.garage.enums;

import lombok.Getter;

public final class GeneralEnums{


    public enum Status {
        ACTIVE,
        PASIVE;
    }


    @Getter
    public enum VehicleType {
        CAR("CAR", 1),
        JEEP("JEEP", 2),
        TRUCK("TRUCK", 4);

        private final String type;
        private final int slotWidth;

        VehicleType(String type, int slotWidth) {
            this.type = type;
            this.slotWidth = slotWidth;
        }


    }

    @Getter
    public enum Exceptions{
        GeneralException("Detected a general exception"),
        VehicleTypeException("Specified vehicle is not defined"),
        NoTicketIdException("Ticket id can not found in recods");

        private final String message;


        Exceptions(String message) {
            this.message = message;
        }

        public String getMessage(Throwable t){
            String methodName="";
            String errDesc="";
            if(t!=null && t.getStackTrace() !=null && t.getStackTrace().length>0){
                methodName = " "+t.getStackTrace()[0].getMethodName();
                errDesc=" " +t.toString();
            }
            return this.message+methodName+errDesc;
        }

    }

}