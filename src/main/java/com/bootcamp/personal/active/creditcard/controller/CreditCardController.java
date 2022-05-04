package com.bootcamp.personal.active.creditcard.controller;

import com.bootcamp.personal.active.creditcard.entity.CreditCard;
import com.bootcamp.personal.active.creditcard.service.CreditCardService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("personal/active/credit_card")
@Tag(name = "Personal Active Credit Card", description = "Manage Personal Active Credit Cards accounts")
@CrossOrigin(value = {"*"})
@RequiredArgsConstructor
public class CreditCardController {

    public final CreditCardService service;

    @GetMapping
    public Mono<ResponseEntity<Flux<CreditCard>>> getAll() {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getAll())
        );
    }

    @GetMapping("/{idClient}")
    public Mono<ResponseEntity<Mono<CreditCard>>> getByIdClient(@PathVariable String idClient) {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getByIdClient(idClient))
        );
    }

    @GetMapping("/{idCreditCard}")
    public Mono<ResponseEntity<Mono<CreditCard>>> getByIdCreditCard(@PathVariable String idCreditCard) {
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.getById(idCreditCard))
        );
    }

    @PostMapping
    public Mono<ResponseEntity<CreditCard>> create(@RequestBody CreditCard creditCard) {

        return service.save(creditCard).map(p -> ResponseEntity
                .created(URI.create("/CreditCard/".concat(p.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p)
        );
    }

    @PutMapping
    public Mono<ResponseEntity<CreditCard>> update(@RequestBody CreditCard creditCard) {
        return service.update(creditCard)
                .map(p -> ResponseEntity.created(URI.create("/CreditCard/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<ResponseEntity<CreditCard>> delete(@RequestBody String id) {
        return service.delete(id)
                .map(p -> ResponseEntity.created(URI.create("/CreditCard/"
                                .concat(p.getId())
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
