import java.io.Serializable;
import java.util.ArrayList;

public class Transporte implements Serializable {
    private String id;
    private String tipo; // "interno" ou "externo"
    private double capacidadePeso;
    private double capacidadeVolume;
    private boolean transporteEspecial;
    private ArrayList<Mercadoria> mercadorias;
    private double pesoAtual;
    private double volumeAtual;

    public Transporte(String id, String tipo, double capacidadePeso, double capacidadeVolume, boolean transporteEspecial) {
        this.id = id;
        this.tipo = tipo;
        this.capacidadePeso = capacidadePeso;
        this.capacidadeVolume = capacidadeVolume;
        this.transporteEspecial = transporteEspecial;
        this.mercadorias = new ArrayList<>();
        this.pesoAtual = 0;
        this.volumeAtual = 0;
    }

    public String getId() { return id; }
    public String getTipo() { return tipo; }
    public boolean isTransporteEspecial() { return transporteEspecial; }

    public boolean podeTransportarParaDestino(String tipoDestino) {
        // Se o destino é um armazém de reciclagem, precisa ser transporte especial
        if (tipoDestino.equals("reciclagem") && !transporteEspecial) {
            return false;
        }
        return true;
    }

    public boolean adicionarMercadoria(Mercadoria mercadoria, String tipoDestino) {
        // Verifica se pode transportar para o destino
        if (!podeTransportarParaDestino(tipoDestino)) {
            System.out.println("Este transporte não está autorizado para destinos do tipo: " + tipoDestino);
            return false;
        }

        // Verifica capacidade
        if (pesoAtual + mercadoria.getPeso() <= capacidadePeso &&
            volumeAtual + mercadoria.getVolume() <= capacidadeVolume) {
            
            mercadorias.add(mercadoria);
            pesoAtual += mercadoria.getPeso();
            volumeAtual += mercadoria.getVolume();
            mercadoria.setLocalizacao("Em transporte: " + id);
            mercadoria.setEstado("Em transporte");
            return true;
        } else {
            System.out.println("Capacidade excedida! Peso atual: " + pesoAtual + 
                             "kg, Volume atual: " + volumeAtual + "m³");
            return false;
        }
    }

    public boolean removerMercadoria(String id) {
        for (Mercadoria m : mercadorias) {
            if (m.getId().equals(id)) {
                mercadorias.remove(m);
                pesoAtual -= m.getPeso();
                volumeAtual -= m.getVolume();
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Transporte{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", capacidadePeso=" + capacidadePeso +
                ", capacidadeVolume=" + capacidadeVolume +
                ", transporteEspecial=" + transporteEspecial +
                ", pesoAtual=" + pesoAtual +
                ", volumeAtual=" + volumeAtual +
                '}';
    }
}