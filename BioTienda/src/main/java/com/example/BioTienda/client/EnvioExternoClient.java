package com.example.BioTienda.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(
        name = "envioExternoClient",
        url = "https://jsonplaceholder.typicode.com"
)
public interface EnvioExternoClient {

    @GetMapping("/todos/1")
    Map<String, Object> consultarEstadoEnvio();
}