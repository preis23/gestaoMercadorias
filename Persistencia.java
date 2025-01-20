import java.io.*;
import java.util.ArrayList;

public class Persistencia {
    @SuppressWarnings("unchecked")
    public void carregarDados(ArrayList<Armazem> armazens, ArrayList<Mercadoria> mercadorias, ArrayList<Transporte> transportes) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dados.dat"))) {
            ArrayList<Armazem> armazensTemp = (ArrayList<Armazem>) ois.readObject();
            ArrayList<Mercadoria> mercadoriasTemp = (ArrayList<Mercadoria>) ois.readObject();
            ArrayList<Transporte> transportesTemp = (ArrayList<Transporte>) ois.readObject();
            
            armazens.addAll(armazensTemp);
            mercadorias.addAll(mercadoriasTemp);
            transportes.addAll(transportesTemp);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de dados não encontrado. Iniciando novo sistema.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    public void salvarDados(ArrayList<Armazem> armazens, ArrayList<Mercadoria> mercadorias, ArrayList<Transporte> transportes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dados.dat"))) {
            oos.writeObject(armazens);
            oos.writeObject(mercadorias);
            oos.writeObject(transportes);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}