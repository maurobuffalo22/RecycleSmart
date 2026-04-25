package com.recyclesmart.model;

/**
 * Representa uma regra de reciclagem para um material específico.
 * Utilizado pelo RecyclingKnowledgeBase para mapear materiais às instruções corretas.
 */
public class RecyclingRule {

    private String materialName;
    private String binColor;
    private String binColorHex;
    private String recyclingInstruction;
    private String environmentalImpact;
    private String recyclabilityScore; // "ALTO", "MÉDIO", "BAIXO"
    private String[] keywords;          // Palavras-chave para identificação por texto

    public RecyclingRule(
        String materialName,
        String binColor,
        String binColorHex,
        String recyclingInstruction,
        String environmentalImpact,
        String recyclabilityScore,
        String[] keywords
    ) {
        this.materialName = materialName;
        this.binColor = binColor;
        this.binColorHex = binColorHex;
        this.recyclingInstruction = recyclingInstruction;
        this.environmentalImpact = environmentalImpact;
        this.recyclabilityScore = recyclabilityScore;
        this.keywords = keywords;
    }

    // ─── Getters ──────────────────────────────────────────────────────────────
    public String getMaterialName() { return materialName; }
    public String getBinColor() { return binColor; }
    public String getBinColorHex() { return binColorHex; }
    public String getRecyclingInstruction() { return recyclingInstruction; }
    public String getEnvironmentalImpact() { return environmentalImpact; }
    public String getRecyclabilityScore() { return recyclabilityScore; }
    public String[] getKeywords() { return keywords; }
}