package com.example.ecotrack.controller;

import com.example.ecotrack.model.dto.request.ConsumoEnergeticoRequest;
import com.example.ecotrack.model.dto.response.ConsumoEnergeticoResponse;
import com.example.ecotrack.service.ConsumoEnergeticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumos-energeticos")
public class ConsumoEnergeticoController {

    @Autowired
    private ConsumoEnergeticoService consumoEnergeticoService;

    @GetMapping
    public ResponseEntity<Page<ConsumoEnergeticoResponse>> getAllConsumosEnergeticos(@RequestParam(defaultValue = "0") int page,
                                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ConsumoEnergeticoResponse> consumos = consumoEnergeticoService.getAllConsumosEnergeticos(pageable);
        return ResponseEntity.ok(consumos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumoEnergeticoResponse> getConsumoEnergeticoById(@PathVariable Long id) {
        ConsumoEnergeticoResponse consumo = consumoEnergeticoService.getConsumoEnergeticoById(id);
        return ResponseEntity.ok(consumo);
    }

    @PostMapping
    public ResponseEntity<ConsumoEnergeticoResponse> addConsumoEnergetico(@RequestBody ConsumoEnergeticoRequest consumoEnergeticoRequest) {
        ConsumoEnergeticoResponse novoConsumo = consumoEnergeticoService.addConsumoEnergetico(consumoEnergeticoRequest);
        return ResponseEntity.ok(novoConsumo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumoEnergeticoResponse> updateConsumoEnergetico(@PathVariable Long id, @RequestBody ConsumoEnergeticoRequest consumoEnergeticoRequest) {
        ConsumoEnergeticoResponse updatedConsumo = consumoEnergeticoService.updateConsumoEnergetico(id, consumoEnergeticoRequest);
        return ResponseEntity.ok(updatedConsumo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsumoEnergetico(@PathVariable Long id) {
        consumoEnergeticoService.deleteConsumoEnergetico(id);
        return ResponseEntity.noContent().build();
    }
}
