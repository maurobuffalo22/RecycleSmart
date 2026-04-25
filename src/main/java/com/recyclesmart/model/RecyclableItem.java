//Este arquivo define o que é o objeto que o usuário está enviando.

package com.recyclesmart.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Modelo que representa o objeto enviado pelo usuário para identificação.
 * Evolução direta do protótipo inicial — mantém a estrutura original
 * e adiciona suporte a resposta, timestamp e metadados da API REST.
 *
 * Encapsulamento: campos private, acesso via getters/setters públicos.
 * Rastreabilidade: UUID único por item, igual ao protótipo original.
 */
public class RecyclableItem {

    private String id;
    private String inputType;   // "TEXT", "PHOTO", "VOICE"
    private String rawData;     // Texto digitado ou nome do arquivo
    private String identifiedMaterial;
    private String recyclingInstruction;
    private String binColor;
    private String environmentalImpact;
    private String recyclabilityScore; // "ALTO", "MÉDIO", "BAIXO"
    private LocalDateTime processedAt;

    // ─── Construtor usado na entrada do usuário (sem resposta ainda) ───────────
    public RecyclableItem(String inputType, String rawData) {
        this.id = UUID.randomUUID().toString();
        this.inputType = inputType;
        this.rawData = rawData;
        this.processedAt = LocalDateTime.now();
    }

    // ─── Construtor padrão (para frameworks de serialização JSON) ─────────────
    public RecyclableItem() {
        this.id = UUID.randomUUID().toString();
        this.processedAt = LocalDateTime.now();
    }

    // ─── Getters ──────────────────────────────────────────────────────────────
    public String getId() { return id; }
    public String getInputType() { return inputType; }
    public String getRawData() { return rawData; }
    public String getIdentifiedMaterial() { return identifiedMaterial; }
    public String getRecyclingInstruction() { return recyclingInstruction; }
    public String getBinColor() { return binColor; }
    public String getEnvironmentalImpact() { return environmentalImpact; }
    public String getRecyclabilityScore() { return recyclabilityScore; }
    public LocalDateTime getProcessedAt() { return processedAt; }

    // ─── Setters ──────────────────────────────────────────────────────────────
    public void setId(String id) { this.id = id; }
    public void setInputType(String inputType) { this.inputType = inputType; }
    public void setRawData(String rawData) { this.rawData = rawData; }
    public void setIdentifiedMaterial(String identifiedMaterial) { this.identifiedMaterial = identifiedMaterial; }
    public void setRecyclingInstruction(String recyclingInstruction) { this.recyclingInstruction = recyclingInstruction; }
    public void setBinColor(String binColor) { this.binColor = binColor; }
    public void setEnvironmentalImpact(String environmentalImpact) { this.environmentalImpact = environmentalImpact; }
    public void setRecyclabilityScore(String recyclabilityScore) { this.recyclabilityScore = recyclabilityScore; }
    public void setProcessedAt(LocalDateTime processedAt) { this.processedAt = processedAt; }

    @Override
    public String toString() {
        return String.format(
            "RecyclableItem{id='%s', inputType='%s', material='%s', binColor='%s'}",
            id, inputType, identifiedMaterial, binColor
        );
    }
}
}
