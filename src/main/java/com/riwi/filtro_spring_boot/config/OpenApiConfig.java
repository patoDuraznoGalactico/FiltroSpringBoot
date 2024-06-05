package com.riwi.filtro_spring_boot.config;
//Para configurar beans dentro de spring 

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Api para la gestión de contenido de Riwi ", version = "S.0", description = "Esta api fue creada para gestionar de manera eficiente las clases, lecciones, materiales multimedia y\n" +
        "estudiantes."))
public class OpenApiConfig {

}