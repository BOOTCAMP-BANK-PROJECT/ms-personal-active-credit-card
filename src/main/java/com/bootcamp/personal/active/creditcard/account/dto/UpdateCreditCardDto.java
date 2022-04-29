package com.bootcamp.personal.active.creditcard.account.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UpdateCreditCardDto {

    @Id
    private String id;

    //private String idClient;
    private String description;
    private String abbreviation;
    //private String isoCurrencyCode;
    private String debitCardNumber;

    private BigDecimal creditCardLine;
    private BigDecimal interesRate;
    private Date paymentDate;
    private Date expirationCardDate;
    private Date cutDate;
    private Date emitionDate;

    /*private short registrationStatus;
    private Date insertionDate;
    private String fk_insertionUser;
    private String insertionTerminal;*/

}