package com.example.BioTienda.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BioTienda.dto.PermisoDTO;
import com.example.BioTienda.dto.RolDTO;
import com.example.BioTienda.entity.Permiso;
import com.example.BioTienda.entity.Rol;
import com.example.BioTienda.repository.PermisoRepository;
import com.example.BioTienda.repository.RolRepository;
import com.example.BioTienda.service.RolService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    
    private final RolRepository rolRepository;
    private final PermisoRepository permisoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<RolDTO.Response> listarTodos(){
        log.info("Listando todos los roles");
        return rolRepository.findAll()
        .stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RolDTO.Response buscarPorId(Long id){
        log.info("Buscando roles por id: {}", id);
        Rol rol = rolRepository.findById(id)
            .orElseThrow(() -> {
                log.error("Rol no encontrado con id: {}", id);
                return new RuntimeException("Rol no encontrado con id:" + id);
            });
        return mapToResponse(rol);
    }
    
    @Override
    @Transactional
    public RolDTO.Response crear(RolDTO.Request request){
        log.info("Creando nuevo Rol: {}", request.getNombre());

        if (rolRepository.existsByNombreIgnoreCase(request.getNombre())) {
            throw new RuntimeException("Ya existe un rol con ese nombre: " + request.getNombre());
        }

        Rol rol = new Rol();
        rol.setNombre(request.getNombre());

        Rol guardado = rolRepository.save(rol);
        log.info("Rol creado con id: {}", guardado.getId());

        return mapToResponse(guardado);

    }

    @Override
    @Transactional
    public RolDTO.Response actualizar(Long id, RolDTO.Request request){
        log.info("Actualizando Rol con id: {}", id);

        Rol rol = rolRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con id:" + id));
        
        rol.setNombre(request.getNombre());
        Rol actualizado = rolRepository.save(rol);

        log.info("Rol actualizado: {}", actualizado.getNombre());
        return mapToResponse(actualizado);

    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        log.info("Eliminando Rol con id: {}", id);

        if (!rolRepository.existsById(id)) {
            throw new RuntimeException("Rol no encontrado con id:" + id);
        }

        rolRepository.deleteById(id);
        log.info("Rol eliminado con id: {}", id);
    }

    @Override
    public Rol asignarPermisos(Long rolId, List<Long> permisosIds) {

        Rol rol = rolRepository.findById(rolId)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        List<Permiso> permisos = permisoRepository.findAllById(permisosIds);

        rol.setPermisos(permisos);

        return rolRepository.save(rol);
    }

    private RolDTO.Response mapToResponse(Rol rol) {

    List<PermisoDTO.Response> permisosResponse =
        rol.getPermisos() == null
                ? new ArrayList<>()
                : rol.getPermisos()
                    .stream()
                    .map(permiso -> new PermisoDTO.Response(
                            permiso.getId(),
                            permiso.getNombre()
                    ))
                    .toList();           

    return new RolDTO.Response(
            rol.getId(),
            rol.getNombre(),
            permisosResponse
    );
    
}
}
