import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Mercadoria implements Serializable {
    private String id;
    private String descricao;
    private double peso;
    private double volume;
    private String tipo;
    private String localizacao;
    private String estado;
    private ArrayList<String> historicoLocalizacoes;

    public Mercadoria(String id, String descricao, double peso, double volume, String tipo) {
        this.id = id;
        this.descricao = descricao;
        this.peso = peso;
        this.volume = volume;
        this.tipo = tipo;
        this.localizacao = "Sem localização";
        this.estado = "Disponível";
        this.historicoLocalizacoes = new ArrayList<>();
        historicoLocalizacoes.add("Criada em: " + LocalDate.now());
    }

    public String getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getPeso() { return peso; }
    public double getVolume() { return volume; }
    public String getTipo() { return tipo; }
    public String getLocalizacao() { return localizacao; }
    public String getEstado() { return estado; }
    public ArrayList<String> getHistoricoLocalizacoes() { return historicoLocalizacoes; }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
        historicoLocalizacoes.add(LocalDate.now() + ": " + localizacao);
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Mercadoria{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", peso=" + peso +
                ", volume=" + volume +
                ", tipo='" + tipo + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}