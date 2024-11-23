package com.example.ecotrack.controller;

import com.example.ecotrack.model.dto.request.DicaEconomiaRequest;
import com.example.ecotrack.model.dto.response.DicaEconomiaResponse;
import com.example.ecotrack.service.DicaEconomiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dicas-economia")
public class DicaEconomiaController {

    @Autowired
    private DicaEconomiaService dicaEconomiaService;

    @GetMapping
    public ResponseEntity<Page<DicaEconomiaResponse>> getAllDicasEconomia(@RequestParam(defaultValue = "0") int page,
                                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DicaEconomiaResponse> dicas = dicaEconomiaService.getAllDicasEconomia(pageable);
        return ResponseEntity.ok(dicas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DicaEconomiaResponse> getDicaEconomiaById(@PathVariable Long id) {
        DicaEconomiaResponse dica = dicaEconomiaService.getDicaEconomiaById(id);
        return ResponseEntity.ok(dica);
    }

    @PostMapping
    public ResponseEntity<DicaEconomiaResponse> addDicaEconomia(@RequestBody DicaEconomiaRequest request) {
        DicaEconomiaResponse novaDica = dicaEconomiaService.addDicaEconomia(request);
        return ResponseEntity.ok(novaDica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DicaEconomiaResponse> updateDicaEconomia(@PathVariable Long id, @RequestBody DicaEconomiaRequest request) {
        DicaEconomiaResponse updatedDica = dicaEconomiaService.updateDicaEconomia(id, request);
        return ResponseEntity.ok(updatedDica);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDicaEconomia(@PathVariable Long id) {
        dicaEconomiaService.deleteDicaEconomia(id);
        return ResponseEntity.noContent().build();
    }
}
