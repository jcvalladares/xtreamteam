package com.sfda.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
public class Users {
	@Id
	@Column
    private long id;

    @Column
    @NotNull(message="{NotNull.User.firstName}")
    private String firstName;
    
    @Column
    @NotNull(message="{NotNull.User.lastName}")
    private String lastName;
    
    @Column
    @NotNull(message="{NotNull.User.email}")
    private String email;
    
    @Column
    private String type;
    
    @Column(name = "VALIDITY_IND")
    private String isValidated;
    
    @Column(name = "QRCODE_IND")
    private String isQRCodeGenerated;
    
    @Column
    private Date birthDate;
}