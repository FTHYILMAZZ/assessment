package com.example.individual.garage.entities;

import com.example.individual.garage.enums.GeneralEnums;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Data
//@Table(name = "GARAGE", uniqueConstraints = {@UniqueConstraint(name="UniquePlateAndStatus",columnNames = {"plate","status"})})
@Table(name = "GARAGE")
@SQLDelete(sql = "UPDATE GARAGE SET STATU = 'PASIVE' WHERE TICKET_ID=? and VERSION = ?")

public class Garage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "TICKET_ID")
    private Long ticketId;
    @Column(name = "COLOR",nullable = false)
    private String color;
    @Column(name="PLATE",nullable = false)
    private String plate;
    @Column(name = "VEHICLE_TYPE")
    @Enumerated(EnumType.STRING)
    private GeneralEnums.VehicleType vehicleType;
    @Column(name = "SLOTS" , length = 255 )
    private String slots;

    public String carInfo() {
        return  plate + " " +  color + " [" + slots.replace(";",",") + "]";
    }
}
