package com.bootcamp.personal.active.creditcard.account.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DeleteCreditCardDto {

    @Id
    private String id;
    //private String idClient;

}