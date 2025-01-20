import java.io.Serializable;
import java.util.ArrayList;

public class Armazem implements Serializable {
    private String nome;
    private String morada;
    private double espacoTotal;
    private double espacoOcupado;
    private String tipo;
    private ArrayList<Mercadoria> mercadorias;

    public Armazem(String nome, String morada, double espacoTotal, String tipo) {
        this.nome = nome;
        this.morada = morada;
        this.espacoTotal = espacoTotal;
        this.espacoOcupado = 0;
        this.tipo = tipo;
        this.mercadorias = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public String getMorada() { return morada; }
    public double getEspacoTotal() { return espacoTotal; }
    public double getEspacoOcupado() { return espacoOcupado; }
    public String getTipo() { return tipo; }
    public ArrayList<Mercadoria> getMercadorias() { return mercadorias; }

    public boolean adicionarMercadoria(Mercadoria mercadoria) {
        if (espacoOcupado + mercadoria.getVolume() <= espacoTotal) {
            mercadorias.add(mercadoria);
            espacoOcupado += mercadoria.getVolume();
            mercadoria.setLocalizacao("Armazém: " + nome);
            mercadoria.setEstado("Armazenada");
            return true;
        }
        return false;
    }

    public boolean removerMercadoria(String id) {
        for (Mercadoria m : mercadorias) {
            if (m.getId().equals(id)) {
                mercadorias.remove(m);
                espacoOcupado -= m.getVolume();
                m.setLocalizacao("Em trânsito");
                m.setEstado("Em transporte");
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Armazem{" +
                "nome='" + nome + '\'' +
                ", morada='" + morada + '\'' +
                ", espacoTotal=" + espacoTotal +
                ", espacoOcupado=" + espacoOcupado +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}