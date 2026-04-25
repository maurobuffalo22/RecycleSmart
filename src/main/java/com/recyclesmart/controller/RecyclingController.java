package com.recyclesmart.controller;

import com.recyclesmart.model.RecyclableItem;
import com.recyclesmart.service.RecyclingIdentificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Controller REST da API RecycleSmart.
 *
 * Expõe os endpoints que o frontend (HTML/JS) consome via fetch().
 * Segue as boas práticas REST: verbos HTTP corretos, status codes, CORS habilitado.
 *
 * Padrão Controller: apenas recebe requisições, delega ao Service e retorna resposta.
 * Nenhuma lógica de negócio deve existir aqui.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Permite requisições do frontend em qualquer porta
public class RecyclingController {

    private final RecyclingIdentificationService identificationService;

    @Autowired
    public RecyclingController(RecyclingIdentificationService identificationService) {
        this.identificationService = identificationService;
    }

    /**
     * Health check — verifica se a API está no ar.
     * Útil para monitoramento e testes.
     *
     * GET /api/health
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "online",
            "app", "RecycleSmart API",
            "version", "1.0.0-mvp"
        ));
    }

    /**
     * Identificação por texto.
     * O usuário digita o nome/descrição do objeto e recebe as instruções de reciclagem.
     *
     * POST /api/identify/text
     * Body: { "description": "garrafa de plástico" }
     */
    @PostMapping("/identify/text")
    public ResponseEntity<RecyclableItem> identifyByText(@RequestBody Map<String, String> body) {
        String description = body.getOrDefault("description", "").trim();

        if (description.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        RecyclableItem result = identificationService.processIdentification("TEXT", description);
        return ResponseEntity.ok(result);
    }

    /**
     * Identificação por upload de foto.
     * O usuário envia a imagem e o sistema analisa o nome do arquivo (MVP)
     * ou o conteúdo via IA (fase futura).
     *
     * POST /api/identify/photo
     * Content-Type: multipart/form-data
     * Field: "image" → arquivo de imagem
     */
    @PostMapping(value = "/identify/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RecyclableItem> identifyByPhoto(
        @RequestParam("image") MultipartFile imageFile
    ) {
        if (imageFile.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        RecyclableItem result = identificationService.processPhotoUpload(imageFile);
        return ResponseEntity.ok(result);
    }

    /**
     * Identificação por comando de voz (texto transcrito).
     * O frontend captura o áudio, transcreve via Web Speech API e envia o texto.
     *
     * POST /api/identify/voice
     * Body: { "transcript": "isso aqui é uma garrafa de vidro" }
     */
    @PostMapping("/identify/voice")
    public ResponseEntity<RecyclableItem> identifyByVoice(@RequestBody Map<String, String> body) {
        String transcript = body.getOrDefault("transcript", "").trim();

        if (transcript.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        RecyclableItem result = identificationService.processIdentification("VOICE", transcript);
        return ResponseEntity.ok(result);
    }
}