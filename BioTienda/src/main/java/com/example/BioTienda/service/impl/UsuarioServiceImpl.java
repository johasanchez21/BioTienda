package com.example.BioTienda.service.impl;

import com.example.BioTienda.dto.PermisoDTO;
import com.example.BioTienda.dto.RolDTO;
import com.example.BioTienda.dto.UsuarioDTO;
import com.example.BioTienda.entity.Usuario;
import com.example.BioTienda.entity.Rol;
import com.example.BioTienda.repository.RolRepository;
import com.example.BioTienda.repository.UsuarioRepository;
import com.example.BioTienda.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{
    
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO.Response> listarTodos() {
        log.info("Listando todos los usuarios");
        return usuarioRepository.findAll()
        .stream()
        .map(this::mapToResponse)
        .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO.Response buscarPorId(Long id) {
        log.info("Buscando usuario con id: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Usuario no encontrado con id: {}", id);
                    return new RuntimeException("Usuario no encontrado con id: " + id);
                });
        return mapToResponse(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioDTO.Response buscarPorRut(String rut) {
        log.info("Buscando usuario con RUT: {}", rut);
        Usuario usuario = usuarioRepository.findByRut(rut)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con RUT: " + rut));
        return mapToResponse(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioDTO.Response> buscarPorRol(Long rolId) {
        log.info("Buscando usuarios con rolId: {}", rolId);
        return usuarioRepository.findByRolId(rolId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsuarioDTO.Response crear(UsuarioDTO.Request request) {
        log.info("Creando usuario con Rut: {}", request.getRut());

        if (usuarioRepository.existsByRut(request.getRut())) {
            throw new RuntimeException("Ya existe una persona con el RUT: " + request.getRut());
        }

        Rol rol = rolRepository.findById(request.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + request.getRolId()));

        Usuario usuario = new Usuario();
        usuario.setRut(request.getRut());
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setRol(rol);

        Usuario guardado = usuarioRepository.save(usuario);
        log.info("Persona creada con id: {}", guardado.getId());

        return mapToResponse(guardado);
    }

    @Override
    @Transactional
    public UsuarioDTO.Response actualizar(Long id, UsuarioDTO.Request request) {
        log.info("Actualizando persona con id: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        Rol rol= rolRepository.findById(request.getRolId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + request.getRolId()));

        usuario.setRut(request.getRut());
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(request.getPassword());
        usuario.setRol(rol);

        Usuario actualizado = usuarioRepository.save(usuario);
        log.info("Persona actualizado: {}", actualizado.getId());

        return mapToResponse(actualizado);
    }

        @Override
        @Transactional
        public void eliminar(Long id) {
            log.info("Eliminando usuario con id: {}", id);

            if (!usuarioRepository.existsById(id)) {
                throw new RuntimeException("Usuario no encontrada con id: " + id);
            }

            usuarioRepository.deleteById(id);
            log.info("Usuario eliminada con id: {}", id);
        }

        public Usuario desactivarUsuario(Long id) {

            Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            usuario.setActivo(false);

            return usuarioRepository.save(usuario);
        }

        @Override
        public Usuario activarUsuario(Long id) {

            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            usuario.setActivo(true);

            return usuarioRepository.save(usuario);
        }


    private UsuarioDTO.Response mapToResponse(Usuario usuario) {

        List<PermisoDTO.Response> permisosResponse =
                usuario.getRol()
                        .getPermisos()
                        .stream()
                        .map(permiso -> new PermisoDTO.Response(
                                permiso.getId(),
                                permiso.getNombre()
                        ))
                        .toList();

        RolDTO.Response rolResponse = new RolDTO.Response(
                usuario.getRol().getId(),
                usuario.getRol().getNombre(),
                permisosResponse
        );

        return new UsuarioDTO.Response(
                usuario.getId(),
                usuario.getRut(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getActivo(),
                rolResponse
        );
    }
}
