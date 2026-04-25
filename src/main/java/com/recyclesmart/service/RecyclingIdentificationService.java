package com.recyclesmart.service;

import com.recyclesmart.model.RecyclableItem;
import com.recyclesmart.model.RecyclingRule;
import com.recyclesmart.repository.RecyclingKnowledgeBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Serviço principal de identificação e classificação de materiais.
 *
 * Esta classe é a evolução direta do método processIdentification() do protótipo original.
 * Mantém a mesma lógica de decisão (if inputType == PHOTO / TEXT / VOICE),
 * mas agora com acesso à base de conhecimento real e retorno estruturado.
 *
 * Padrão Service: concentra toda a lógica de negócio, desacoplada do Controller e Repository.
 */
@Service
public class RecyclingIdentificationService {

    private final RecyclingKnowledgeBase knowledgeBase;

    @Autowired
    public RecyclingIdentificationService(RecyclingKnowledgeBase knowledgeBase) {
        this.knowledgeBase = knowledgeBase;
    }

    /**
     * Ponto de entrada principal — mesma responsabilidade do processIdentification() original,
     * mas agora retorna um RecyclableItem completo com todas as informações de reciclagem.
     *
     * @param inputType Tipo de entrada: "TEXT", "PHOTO" ou "VOICE"
     * @param rawData   Texto digitado ou nome do arquivo de imagem
     * @return RecyclableItem preenchido com as instruções de reciclagem
     */
    public RecyclableItem processIdentification(String inputType, String rawData) {
        System.out.println("--- RecycleSmart: Processando identificação ---");
        System.out.println("Tipo de entrada: " + inputType);
        System.out.println("Dados recebidos: " + rawData);

        RecyclableItem item = new RecyclableItem(inputType, rawData);
        Optional<RecyclingRule> ruleOpt;

        // Lógica de despacho — mesma estrutura do if/else do protótipo original
        switch (inputType.toUpperCase()) {
            case "TEXT":
            case "VOICE":
                System.out.println("Status: Analisando texto para identificar material...");
                ruleOpt = knowledgeBase.findByTextDescription(rawData);
                break;

            case "PHOTO":
                System.out.println("Status: Analisando nome do arquivo para identificar material...");
                // Em produção: chamada à API Google Vision / AWS Rekognition aqui
                ruleOpt = knowledgeBase.findByImageFilename(rawData);
                break;

            default:
                System.out.println("Tipo de entrada desconhecido: " + inputType);
                ruleOpt = Optional.empty();
        }

        // Preenche o item com os dados da regra encontrada
        if (ruleOpt.isPresent()) {
            RecyclingRule rule = ruleOpt.get();
            item.setIdentifiedMaterial(rule.getMaterialName());
            item.setBinColor(rule.getBinColor());
            item.setRecyclingInstruction(rule.getRecyclingInstruction());
            item.setEnvironmentalImpact(rule.getEnvironmentalImpact());
            item.setRecyclabilityScore(rule.getRecyclabilityScore());
            System.out.println("Resultado: " + rule.getMaterialName() + " → Lixeira " + rule.getBinColor());
        } else {
            // Material não identificado — orienta o usuário
            item.setIdentifiedMaterial("Material não identificado");
            item.setBinColor("Cinza");
            item.setRecyclingInstruction(
                "Não conseguimos identificar este material. Tente descrever com mais detalhes " +
                "(ex: 'garrafa de plástico', 'lata de alumínio', 'papel'). " +
                "Em caso de dúvida, descarte no lixo comum (cinza/preto)."
            );
            item.setEnvironmentalImpact(
                "Sem informações suficientes para calcular o impacto ambiental. " +
                "Tente ser mais específico na descrição do material."
            );
            item.setRecyclabilityScore("INDETERMINADO");
            System.out.println("Resultado: Material não identificado na base de conhecimento.");
        }

        return item;
    }

    /**
     * Processa identificação a partir de upload de imagem real.
     * Por ora, usa o nome do arquivo como proxy para a IA.
     * Em produção: integrar com Google Vision API ou modelo de ML próprio.
     *
     * @param file Arquivo de imagem enviado pelo usuário
     * @return RecyclableItem com instruções de reciclagem
     */
    public RecyclableItem processPhotoUpload(MultipartFile file) {
        String filename = file.getOriginalFilename() != null
            ? file.getOriginalFilename()
            : "imagem_desconhecida.jpg";

        System.out.println("Status: Recebendo imagem '" + filename + "' para análise...");
        System.out.println("Tamanho: " + (file.getSize() / 1024) + " KB");

        // TODO (Fase 2): Enviar bytes da imagem para API de visão computacional
        // byte[] imageBytes = file.getBytes();
        // String materialDetected = VisionAPIClient.detectMaterial(imageBytes);
        // return processIdentification("PHOTO", materialDetected);

        return processIdentification("PHOTO", filename);
    }
}