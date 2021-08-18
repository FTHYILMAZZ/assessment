package com.example.individual.garage.entities;


import com.example.individual.garage.enums.GeneralEnums;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {
    @Version
    private Long version;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "STATU")
    @Enumerated(EnumType.STRING)
    private GeneralEnums.Status statu;

    @PrePersist
    public void onPrePersist() {
        // this.setVersiyon(GeneralConstants.DEFAULT_VERSION);
        this.setStatu(GeneralEnums.Status.ACTIVE);
        this.setCreatedAt(new Date());
    }


}
