package com.bootcamp.personal.active.creditcard.service.impl;


import com.bootcamp.personal.active.creditcard.entity.CreditCard;
import com.bootcamp.personal.active.creditcard.repository.CreditCardRepository;
import com.bootcamp.personal.active.creditcard.service.CreditCardService;
import com.bootcamp.personal.active.creditcard.util.Constant;
import com.bootcamp.personal.active.creditcard.util.handler.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    public final CreditCardRepository repository;

    @Override
    public Flux<CreditCard> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<CreditCard> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<CreditCard> getByIdClient(String idClient) {
        return repository.findByIdClient(idClient);
    }

    @Override
    public Mono<CreditCard> save(CreditCard creditCard) {
        return repository.findByIdClient(creditCard.getIdClient())
                .map(sa -> {
                    throw new BadRequestException(
                            "ID",
                            "Client have one ore more creditCards",
                            sa.getId(),
                            CreditCardServiceImpl.class,
                            "save.onErrorResume"
                    );
                })
                .switchIfEmpty(Mono.defer(() -> {
                            creditCard.setId(null);
                            creditCard.setInsertionDate(new Date());
                            return repository.save(creditCard);
                        }
                ))
                .onErrorResume(e -> Mono.error(e)).cast(CreditCard.class);
    }

    @Override
    public Mono<CreditCard> update(CreditCard creditCard) {

        return repository.findById(creditCard.getId())
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + creditCard.getId() + " was not found. >> switchIfEmpty")))
                .flatMap(p -> repository.save(creditCard))
                .onErrorResume(e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to update an item.",
                        e.getMessage(),
                        CreditCardServiceImpl.class,
                        "update.onErrorResume"
                )));
    }

    @Override
    public Mono<CreditCard> delete(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new Exception("An item with the id " + id + " was not found. >> switchIfEmpty")))
                .flatMap(p -> {
                    p.setRegistrationStatus(Constant.STATUS_INACTIVE);
                    return repository.save(p);
                })
                .onErrorResume(e -> Mono.error(new BadRequestException(
                        "ID",
                        "An error occurred while trying to delete an item.",
                        e.getMessage(),
                        CreditCardServiceImpl.class,
                        "update.onErrorResume"
                )));
    }
}