package com.example.ecotrack.service;

import com.example.ecotrack.model.dto.request.UsuarioRequest;
import com.example.ecotrack.model.dto.response.ConsumoEnergeticoResponse;
import com.example.ecotrack.model.dto.response.UsuarioResponse;
import com.example.ecotrack.exception.ResourceNotFoundException;
import com.example.ecotrack.model.Usuario;
import com.example.ecotrack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Page<UsuarioResponse> getAllUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(usuario -> new UsuarioResponse(
                usuario.getId(),
                usuario.getFirebaseID(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataCadastro(),
                usuario.getConsumosEnergeticos() != null ?
                        usuario.getConsumosEnergeticos().stream()
                                .map(consumo -> new ConsumoEnergeticoResponse(
                                        consumo.getId(),
                                        consumo.getMes(),
                                        consumo.getAno(),
                                        consumo.getConsumoKWh(),
                                        null // Evita loop infinito
                                ))
                                .collect(Collectors.toList()) :
                        Collections.emptyList()
        ));
    }

    @Transactional(readOnly = true)
    public UsuarioResponse getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id " + id));
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getFirebaseID(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataCadastro(),
                usuario.getConsumosEnergeticos() != null ?
                        usuario.getConsumosEnergeticos().stream()
                                .map(consumo -> new ConsumoEnergeticoResponse(
                                        consumo.getId(),
                                        consumo.getMes(),
                                        consumo.getAno(),
                                        consumo.getConsumoKWh(),
                                        null // Evita loop infinito
                                ))
                                .collect(Collectors.toList()) :
                        Collections.emptyList()
        );
    }

    @Transactional
    public UsuarioResponse addUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = Usuario.builder()
                .firebaseID(usuarioRequest.firebaseID())
                .nome(usuarioRequest.nome())
                .email(usuarioRequest.email())
                .build();
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return new UsuarioResponse(
                savedUsuario.getId(),
                savedUsuario.getFirebaseID(),
                savedUsuario.getNome(),
                savedUsuario.getEmail(),
                savedUsuario.getDataCadastro(),
                Collections.emptyList() // Inicializa a lista como vazia
        );
    }

    @Transactional
    public UsuarioResponse updateUsuario(Long id, UsuarioRequest usuarioRequest) {
        return usuarioRepository.findById(id)
                .map(existingUsuario -> {
                    existingUsuario.setFirebaseID(usuarioRequest.firebaseID());
                    existingUsuario.setNome(usuarioRequest.nome());
                    existingUsuario.setEmail(usuarioRequest.email());
                    Usuario updatedUsuario = usuarioRepository.save(existingUsuario);
                    return new UsuarioResponse(
                            updatedUsuario.getId(),
                            updatedUsuario.getFirebaseID(),
                            updatedUsuario.getNome(),
                            updatedUsuario.getEmail(),
                            updatedUsuario.getDataCadastro(),
                            updatedUsuario.getConsumosEnergeticos() != null ?
                                    updatedUsuario.getConsumosEnergeticos().stream()
                                            .map(consumo -> new ConsumoEnergeticoResponse(
                                                    consumo.getId(),
                                                    consumo.getMes(),
                                                    consumo.getAno(),
                                                    consumo.getConsumoKWh(),
                                                    null // Evita loop infinito
                                            ))
                                            .collect(Collectors.toList()) :
                                    Collections.emptyList()
                    );
                })
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id " + id));
    }

    @Transactional
    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario not found with id " + id));
        usuarioRepository.delete(usuario);
    }
}
