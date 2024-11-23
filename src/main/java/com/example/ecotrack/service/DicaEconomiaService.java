package com.example.ecotrack.service;

import com.example.ecotrack.model.dto.request.DicaEconomiaRequest;
import com.example.ecotrack.model.dto.response.DicaEconomiaResponse;
import com.example.ecotrack.exception.ResourceNotFoundException;
import com.example.ecotrack.model.DicaEconomia;
import com.example.ecotrack.repository.DicaEconomiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DicaEconomiaService {

    @Autowired
    private DicaEconomiaRepository dicaEconomiaRepository;

    public Page<DicaEconomiaResponse> getAllDicasEconomia(Pageable pageable) {
        return dicaEconomiaRepository.findAll(pageable).map(dicaEconomia -> new DicaEconomiaResponse(
                dicaEconomia.getId(),
                dicaEconomia.getTitulo(),
                dicaEconomia.getDescricao(),
                dicaEconomia.getDataCriacao()
        ));
    }

    public DicaEconomiaResponse getDicaEconomiaById(Long id) {
        DicaEconomia dicaEconomia = dicaEconomiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DicaEconomia not found with id " + id));
        return new DicaEconomiaResponse(
                dicaEconomia.getId(),
                dicaEconomia.getTitulo(),
                dicaEconomia.getDescricao(),
                dicaEconomia.getDataCriacao()
        );
    }

    public DicaEconomiaResponse addDicaEconomia(DicaEconomiaRequest request) {
        DicaEconomia dicaEconomia = DicaEconomia.builder()
                .titulo(request.titulo())
                .descricao(request.descricao())
                .build();
        DicaEconomia savedDicaEconomia = dicaEconomiaRepository.save(dicaEconomia);
        return new DicaEconomiaResponse(
                savedDicaEconomia.getId(),
                savedDicaEconomia.getTitulo(),
                savedDicaEconomia.getDescricao(),
                savedDicaEconomia.getDataCriacao()
        );
    }

    public DicaEconomiaResponse updateDicaEconomia(Long id, DicaEconomiaRequest request) {
        return dicaEconomiaRepository.findById(id)
                .map(existingDica -> {
                    existingDica.setTitulo(request.titulo());
                    existingDica.setDescricao(request.descricao());
                    DicaEconomia updatedDica = dicaEconomiaRepository.save(existingDica);
                    return new DicaEconomiaResponse(
                            updatedDica.getId(),
                            updatedDica.getTitulo(),
                            updatedDica.getDescricao(),
                            updatedDica.getDataCriacao()
                    );
                })
                .orElseThrow(() -> new ResourceNotFoundException("DicaEconomia not found with id " + id));
    }

    public void deleteDicaEconomia(Long id) {
        DicaEconomia dicaEconomia = dicaEconomiaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DicaEconomia not found with id " + id));
        dicaEconomiaRepository.delete(dicaEconomia);
    }
}
