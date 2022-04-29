package com.bootcamp.personal.active.creditcard.account.repository;

import com.bootcamp.personal.active.creditcard.account.entity.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, String> {

    Mono<CreditCard> findByIdClient(String idClient);

    Mono<Boolean> existsByIdClient(String idClient);

}