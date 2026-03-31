//Este arquivo define o que é o objeto que o usuário está enviando.

public class RecyclableItem {
    private String id;
    private String inputType; // "FOTO", "TEXTO", "VOZ"
    private String rawData;   // Descrição ou caminho do arquivo

    public RecyclableItem(String inputType, String rawData) {
        this.inputType = inputType;
        this.rawData = rawData;
        this.id = java.util.UUID.randomUUID().toString();
    }

    // Getters
    public String getId() { return id; }
    public String getInputType() { return inputType; }
    public String getRawData() { return rawData; }
}


//Encapsulamento: Usamos modificadores private e métodos public.
//Identificadores Únicos: O uso do UUID mostra como rastrear cada item no banco de dados.
//Escalabilidade: O código está pronto para receber uma API de visão computacional ou reconhecimento de voz no futuro.