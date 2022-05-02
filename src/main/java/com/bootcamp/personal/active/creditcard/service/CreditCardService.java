package com.bootcamp.personal.active.creditcard.service;

import com.bootcamp.personal.active.creditcard.entity.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CreditCardService {

    public Flux<CreditCard> getAll();

    public Mono<CreditCard> getById(String id);

    public Mono<CreditCard> save(CreditCard creditCard);

    public Mono<CreditCard> update(CreditCard creditCard);

    public Mono<CreditCard> delete(String id);

    Mono<CreditCard> getByIdClient(String idClient);

}