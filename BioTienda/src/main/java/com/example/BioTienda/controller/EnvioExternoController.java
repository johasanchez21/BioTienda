package com.example.BioTienda.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BioTienda.service.EnvioExternoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/envios-externos")
@RequiredArgsConstructor
public class EnvioExternoController {

    private final EnvioExternoService envioExternoService;

    @GetMapping("/estado")
    public ResponseEntity<Map<String, Object>> consultarEstadoEnvio() {

        log.debug("GET /api/envios-externos/estado");

        return ResponseEntity.ok(
                envioExternoService.consultarEstadoEnvio()
        );
    }
}