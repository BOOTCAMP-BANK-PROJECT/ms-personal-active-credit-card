package com.bootcamp.personal.active.creditcard.repository;

import com.bootcamp.personal.active.creditcard.entity.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, String> {

    Mono<CreditCard> findByIdClient(String idClient);
}