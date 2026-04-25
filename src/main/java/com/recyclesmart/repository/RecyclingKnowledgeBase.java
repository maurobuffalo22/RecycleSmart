package com.recyclesmart.repository;

import com.recyclesmart.model.RecyclingRule;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Base de conhecimento de reciclagem.
 *
 * Simula o banco de dados de materiais e regras de descarte.
 * Em produção, este repositório seria conectado a um banco de dados real (PostgreSQL, etc.)
 * e possivelmente integrado a uma API de IA para identificação por imagem.
 *
 * Padrão Repository: separa a lógica de dados da lógica de negócio.
 */
@Repository
public class RecyclingKnowledgeBase {

    private final List<RecyclingRule> rules;

    public RecyclingKnowledgeBase() {
        this.rules = new ArrayList<>();
        loadRules();
    }

    /**
     * Carrega todas as regras de reciclagem conhecidas.
     * Baseado nas normas ABNT e diretrizes de coleta seletiva do Brasil.
     */
    private void loadRules() {

        // ── PLÁSTICOS ──────────────────────────────────────────────────────────
        rules.add(new RecyclingRule(
            "Garrafa PET / Plástico",
            "Vermelho",
            "#E53935",
            "Esvazie e lave o recipiente. Amasse para reduzir volume. " +
            "Retire tampas e rótulos se possível. Descarte na lixeira VERMELHA.",
            "Uma garrafa PET leva até 400 anos para se decompor no meio ambiente. " +
            "Ao não reciclar, você contribui para o acúmulo em aterros, rios e oceanos, " +
            "liberando microplásticos que contaminam a cadeia alimentar.",
            "ALTO",
            new String[]{"pet", "plástico", "plastico", "garrafa", "embalagem plástica",
                         "sacola", "pote", "isopor", "canudo", "balde", "copo descartável",
                         "frasco", "tampa", "tubo"}
        ));

        // ── VIDRO ──────────────────────────────────────────────────────────────
        rules.add(new RecyclingRule(
            "Vidro",
            "Verde",
            "#43A047",
            "Lave o recipiente. NÃO quebre o vidro — isso dificulta a reciclagem. " +
            "Retire tampas metálicas. Descarte na lixeira VERDE.",
            "O vidro leva mais de 4.000 anos para se decompor. " +
            "A boa notícia: ele pode ser reciclado infinitas vezes sem perda de qualidade. " +
            "Não reciclar desperdiça energia equivalente a manter uma lâmpada acesa por 4 horas por garrafa.",
            "ALTO",
            new String[]{"vidro", "garrafa de vidro", "pote de vidro", "frasco de vidro",
                         "copo de vidro", "jarra", "espelho", "janela"}
        ));

        // ── PAPEL ──────────────────────────────────────────────────────────────
        rules.add(new RecyclingRule(
            "Papel / Papelão",
            "Azul",
            "#1E88E5",
            "Mantenha o papel seco e limpo. Papéis sujos com gordura (como caixas de pizza) " +
            "NÃO são recicláveis. Desdobre caixas de papelão para economizar espaço. " +
            "Descarte na lixeira AZUL.",
            "Uma tonelada de papel reciclado poupa 20 árvores, 50.000 litros de água e " +
            "energy equivalente a 300 litros de petróleo. Papel no lixo comum vai para aterros " +
            "e libera metano, um gás 25x mais poluente que o CO₂.",
            "ALTO",
            new String[]{"papel", "papelão", "caixa", "jornal", "revista", "caderno",
                         "folha", "envelope", "papel higiênico", "guardanapo", "cartão",
                         "embalagem papel", "papel craft", "papelão ondulado"}
        ));

        // ── METAL ──────────────────────────────────────────────────────────────
        rules.add(new RecyclingRule(
            "Metal / Alumínio",
            "Amarelo",
            "#FDD835",
            "Lave latas de alumínio e aço. Amasse as latas para reduzir volume. " +
            "Tampas de metal também são recicláveis. Descarte na lixeira AMARELA.",
            "Uma lata de alumínio leva 200 a 500 anos para se decompor. " +
            "Reciclar alumínio consome 95% menos energia do que produzir a partir do minério. " +
            "O Brasil é líder mundial em reciclagem de latas de alumínio — não desperdice!",
            "ALTO",
            new String[]{"metal", "alumínio", "aluminio", "lata", "ferro", "aço", "aco",
                         "latinha", "tampa metal", "panela", "fio", "cabo", "prego",
                         "lata de refrigerante", "lata de cerveja"}
        ));

        // ── ORGÂNICO ──────────────────────────────────────────────────────────
        rules.add(new RecyclingRule(
            "Resíduo Orgânico",
            "Marrom",
            "#6D4C41",
            "Restos de comida, cascas de frutas e legumes, borra de café e folhas. " +
            "Se tiver composteira, utilize! Caso contrário, descarte na lixeira MARROM " +
            "ou no lixo comum (onde não houver coleta orgânica).",
            "Resíduos orgânicos em aterros produzem metano, contribuindo para o efeito estufa. " +
            "A compostagem transforma esse resíduo em adubo, devolvendo nutrientes ao solo e " +
            "eliminando a necessidade de fertilizantes químicos.",
            "MÉDIO",
            new String[]{"orgânico", "organico", "comida", "alimento", "fruta", "legume",
                         "verdura", "casca", "resto comida", "café", "cafe", "folha",
                         "grama", "borra", "ovo", "carne", "pão", "pao"}
        ));

        // ── REJEITO ───────────────────────────────────────────────────────────
        rules.add(new RecyclingRule(
            "Rejeito (Não Reciclável)",
            "Cinza/Preto",
            "#757575",
            "Itens como papel higiênico usado, fraldas, absorventes, canudinhos, " +
            "isopor sujo e embalagens multicamadas não são recicláveis. " +
            "Descarte na lixeira CINZA ou PRETA (lixo comum).",
            "Rejeitos inevitavelmente vão para aterros sanitários. " +
            "Reduzir o consumo de produtos não recicláveis é a melhor estratégia. " +
            "Prefira embalagens recicláveis na hora da compra para diminuir esse impacto.",
            "BAIXO",
            new String[]{"rejeito", "fralda", "absorvente", "papel higiênico usado",
                         "isopor sujo", "pirex", "cerâmica", "ceramica", "porcelana",
                         "vela", "toco de cigarro", "bituca", "esponja"}
        ));

        // ── ELETRÔNICO ────────────────────────────────────────────────────────
        rules.add(new RecyclingRule(
            "Resíduo Eletrônico (E-Lixo)",
            "Branco (Ponto de coleta especial)",
            "#ECEFF1",
            "NUNCA descarte eletrônicos no lixo comum! Leve a pontos de coleta especializados: " +
            "lojas de eletrônicos, fabricantes ou programas municipais de e-lixo. " +
            "Retire baterias antes se possível.",
            "Eletrônicos contêm metais pesados como mercúrio, chumbo e cádmio. " +
            "Descartados incorretamente, contaminam o solo e lençóis freáticos por séculos. " +
            "Uma bateria de celular pode contaminar 600.000 litros de água.",
            "MÉDIO",
            new String[]{"eletrônico", "eletronico", "celular", "smartphone", "computador",
                         "notebook", "tablet", "bateria", "pilha", "carregador", "cabo usb",
                         "tv", "televisão", "televisao", "monitor", "teclado", "mouse",
                         "impressora", "rádio", "radio", "e-lixo", "lixo eletrônico"}
        ));

        // ── ÓLEO DE COZINHA ───────────────────────────────────────────────────
        rules.add(new RecyclingRule(
            "Óleo de Cozinha Usado",
            "Ponto de coleta especial",
            "#FF8F00",
            "Guarde o óleo em garrafas PET fechadas. NUNCA jogue no ralo ou lixo comum. " +
            "Leve a postos de coleta (mercados, escolas, prefeituras). " +
            "O óleo pode virar biodiesel ou sabão.",
            "Um litro de óleo contamina até 1 milhão de litros de água. " +
            "Descartado no ralo, obstrui esgotos e destrói a vida aquática dos rios. " +
            "Reciclado, pode se tornar biodiesel, reduzindo emissões de CO₂.",
            "MÉDIO",
            new String[]{"óleo", "oleo", "azeite", "gordura", "fritura", "óleo de cozinha",
                         "óleo vegetal", "margarina"}
        ));

        // ── MEDICAMENTOS ──────────────────────────────────────────────────────
        rules.add(new RecyclingRule(
            "Medicamentos Vencidos",
            "Ponto de coleta especial (Farmácias)",
            "#AB47BC",
            "NUNCA jogue remédios no lixo comum ou vaso sanitário! " +
            "Leve a farmácias participantes do Programa Descarte Consciente. " +
            "Mantenha na embalagem original para facilitar identificação.",
            "Medicamentos descartados incorretamente contaminam a água e o solo com " +
            "substâncias químicas que resistem ao tratamento convencional de esgoto, " +
            "afetando fauna aquática e potencialmente a saúde humana.",
            "MÉDIO",
            new String[]{"remédio", "remedio", "medicamento", "comprimido", "cápsula",
                         "capsula", "antibiótico", "antibiotico", "xarope", "pomada",
                         "injeção", "injecao", "vencido"}
        ));
    }

    /**
     * Busca a regra de reciclagem com base em uma descrição textual.
     * Faz matching por palavras-chave (case-insensitive).
     *
     * @param description Texto fornecido pelo usuário
     * @return Optional com a regra encontrada, ou vazio se não identificado
     */
    public Optional<RecyclingRule> findByTextDescription(String description) {
        if (description == null || description.isBlank()) {
            return Optional.empty();
        }

        String lowerDesc = description.toLowerCase().trim();

        for (RecyclingRule rule : rules) {
            for (String keyword : rule.getKeywords()) {
                if (lowerDesc.contains(keyword.toLowerCase())) {
                    return Optional.of(rule);
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Busca a regra de reciclagem com base no nome do arquivo de imagem.
     * Em produção, seria substituída por chamada a uma API de visão computacional (ex: Google Vision).
     *
     * @param filename Nome do arquivo de imagem
     * @return Optional com a regra encontrada
     */
    public Optional<RecyclingRule> findByImageFilename(String filename) {
        if (filename == null || filename.isBlank()) {
            return Optional.empty();
        }

        String lowerFilename = filename.toLowerCase();
        return findByTextDescription(lowerFilename);
    }

    public List<RecyclingRule> getAllRules() {
        return new ArrayList<>(rules);
    }
}