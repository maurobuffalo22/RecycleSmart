//Este é o ponto de entrada. Aqui simulamos o recebimento de uma entrada e como o sistema "decide" o que fazer.

package com.recyclesmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RecycleSmart - Aplicativo de Identificação de Reciclagem
 * Ponto de entrada principal da aplicação Spring Boot.
 *
 * Evoluído do protótipo inicial para uma API REST completa,
 * mantendo os conceitos de RecyclableItem e processamento de identificação.
 */
@SpringBootApplication
public class RecycleSmartApp {

    public static void main(String[] args) {
        System.out.println("--- RecycleSmart: Iniciando servidor... ---");
        SpringApplication.run(RecycleSmartApp.class, args);
        System.out.println("--- RecycleSmart: API disponível em http://localhost:8080 ---");
    }
}
