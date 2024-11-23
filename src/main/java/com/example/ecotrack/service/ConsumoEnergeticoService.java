package com.example.ecotrack.service;

import com.example.ecotrack.model.dto.request.ConsumoEnergeticoRequest;
import com.example.ecotrack.model.dto.response.ConsumoEnergeticoResponse;
import com.example.ecotrack.exception.ResourceNotFoundException;
import com.example.ecotrack.model.ConsumoEnergetico;
import com.example.ecotrack.model.Usuario;
import com.example.ecotrack.repository.ConsumoEnergeticoRepository;
import com.example.ecotrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ConsumoEnergeticoService {

    @Autowired
    private ConsumoEnergeticoRepository consumoEnergeticoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<ConsumoEnergeticoResponse> getAllConsumosEnergeticos(Pageable pageable) {
        return consumoEnergeticoRepository.findAll(pageable).map(consumoEnergetico -> new ConsumoEnergeticoResponse(
                consumoEnergetico.getId(),
                consumoEnergetico.getMes(),
                consumoEnergetico.getAno(),
                consumoEnergetico.getConsumoKWh(),
                consumoEnergetico.getUsuario().getId()
        ));
    }

    public ConsumoEnergeticoResponse getConsumoEnergeticoById(Long id) {
        ConsumoEnergetico consumoEnergetico = consumoEnergeticoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConsumoEnergetico not found with id " + id));
        return new ConsumoEnergeticoResponse(
                consumoEnergetico.getId(),
                consumoEnergetico.getMes(),
                consumoEnergetico.getAno(),
                consumoEnergetico.getConsumoKWh(),
                consumoEnergetico.getUsuario().getId()
        );
    }

    public ConsumoEnergeticoResponse addConsumoEnergetico(ConsumoEnergeticoRequest request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id " + request.usuarioId()));
        ConsumoEnergetico consumoEnergetico = ConsumoEnergetico.builder()
                .mes(request.mes())
                .ano(request.ano())
                .consumoKWh(request.consumoKWh())
                .usuario(usuario)
                .build();
        ConsumoEnergetico savedConsumoEnergetico = consumoEnergeticoRepository.save(consumoEnergetico);
        return new ConsumoEnergeticoResponse(
                savedConsumoEnergetico.getId(),
                savedConsumoEnergetico.getMes(),
                savedConsumoEnergetico.getAno(),
                savedConsumoEnergetico.getConsumoKWh(),
                savedConsumoEnergetico.getUsuario().getId()
        );
    }

    public ConsumoEnergeticoResponse updateConsumoEnergetico(Long id, ConsumoEnergeticoRequest request) {
        return consumoEnergeticoRepository.findById(id)
                .map(existingConsumo -> {
                    existingConsumo.setMes(request.mes());
                    existingConsumo.setAno(request.ano());
                    existingConsumo.setConsumoKWh(request.consumoKWh());
                    ConsumoEnergetico updatedConsumoEnergetico = consumoEnergeticoRepository.save(existingConsumo);
                    return new ConsumoEnergeticoResponse(
                            updatedConsumoEnergetico.getId(),
                            updatedConsumoEnergetico.getMes(),
                            updatedConsumoEnergetico.getAno(),
                            updatedConsumoEnergetico.getConsumoKWh(),
                            updatedConsumoEnergetico.getUsuario().getId()
                    );
                })
                .orElseThrow(() -> new ResourceNotFoundException("ConsumoEnergetico not found with id " + id));
    }

    public void deleteConsumoEnergetico(Long id) {
        ConsumoEnergetico consumoEnergetico = consumoEnergeticoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ConsumoEnergetico not found with id " + id));
        consumoEnergeticoRepository.delete(consumoEnergetico);
    }
}
