import java.io.Serializable;

public class TagIoT implements Serializable {
    private String id;
    private String localizacao;
    private String estado; // "armazenada", "em transporte", "entregue"

    public TagIoT(String id) {
        this.id = id;
        this.localizacao = "Sem localização";
        this.estado = "armazenada";
    }

    public String getId() { 
        return id; 
    }

    public String getLocalizacao() { 
        return localizacao; 
    }

    public void setLocalizacao(String localizacao) { 
        this.localizacao = localizacao; 
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "TagIoT{id='" + id + "', localizacao='" + localizacao + "', estado='" + estado + "'}";
    }
}