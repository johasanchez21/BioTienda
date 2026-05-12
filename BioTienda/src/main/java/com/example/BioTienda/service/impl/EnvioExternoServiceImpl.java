package com.example.BioTienda.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.BioTienda.client.EnvioExternoClient;
import com.example.BioTienda.service.EnvioExternoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EnvioExternoServiceImpl implements EnvioExternoService {

    private final EnvioExternoClient envioExternoClient;

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> consultarEstadoEnvio() {

        log.info("Consultando servicio externo de envío con Feign");

        return envioExternoClient.consultarEstadoEnvio();
    }
}