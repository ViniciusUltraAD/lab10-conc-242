package com.example.concorrente_lab10.controller;

import com.example.concorrente_lab10.service.produtoInterface.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ProdutoController {

    private ProdutoService produtoService;

//    @PostMapping()
//    public ResponseEntity<EventDto> post(
//            @RequestBody @Valid EventCreateDto eventCreateDto) {
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(produtoService.);
//    }
//
//    @GetMapping()
//    public ResponseEntity<EventDto> get
//            (@PathVariable UUID id) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(eventService.getEvent(id));
//    }
//
//    @PutMapping()
//    public ResponseEntity<EventDto> put(
//            @PathVariable UUID id,
//            @RequestBody EventPutDto eventPutDto) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(eventService.updateEvent(id,eventPutDto));
//    }
}
