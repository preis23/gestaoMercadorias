import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Relatorio {
    private static final String CURRENT_USER = "Pedro Reis";
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.parse("2025-01-20 22:54:52", 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    
    private static final String NOME_RELATORIO = "relatorio_mercadorias.txt";

    public static void gerarRelatorioMercadorias(ArrayList<Mercadoria> mercadorias, ArrayList<Armazem> armazens) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_RELATORIO, true))) {
            writer.println("\n====================================================");
            writer.println("              RELATÓRIO DE MERCADORIAS               ");
            writer.println("====================================================");
            writer.println("Data/Hora: " + CURRENT_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println("Utilizador: " + CURRENT_USER);
            writer.println("====================================================");

            writer.println("\nRESUMO GERAL:");
            writer.println("Total de Mercadorias: " + mercadorias.size());
            
            // Contar mercadorias por estado
            int armazenadas = 0;
            int emTransporte = 0;
            int semLocalizacao = 0;
            
            for (Mercadoria m : mercadorias) {
                if (m.getEstado().equals("Armazenada")) armazenadas++;
                else if (m.getEstado().equals("Em transporte")) emTransporte++;
                else semLocalizacao++;
            }
            
            writer.println("- Armazenadas: " + armazenadas);
            writer.println("- Em Transporte: " + emTransporte);
            writer.println("- Sem Localização: " + semLocalizacao);

            // Detalhes de cada mercadoria
            writer.println("\nDETALHES DAS MERCADORIAS:");
            writer.println("----------------------------------------------------");
            
            for (Mercadoria m : mercadorias) {
                writer.println("\nMercadoria ID: " + m.getId());
                writer.println("Descrição: " + m.getDescricao());
                writer.println("Tipo: " + m.getTipo());
                writer.println("Peso: " + m.getPeso() + " kg");
                writer.println("Volume: " + m.getVolume() + " m³");
                writer.println("Estado Atual: " + m.getEstado());
                writer.println("Localização Atual: " + m.getLocalizacao());
                
                // Histórico de localizações
                writer.println("Histórico de Movimentações:");
                if (m.getHistoricoLocalizacoes().isEmpty()) {
                    writer.println("  * Sem movimentações registradas");
                } else {
                    for (String loc : m.getHistoricoLocalizacoes()) {
                        writer.println("  * " + loc);
                    }
                }
                writer.println("----------------------------------------------------");
            }

            // Distribuição por armazém
            writer.println("\nDISTRIBUIÇÃO POR ARMAZÉM:");
            for (Armazem a : armazens) {
                writer.println("\nArmazém: " + a.getNome() + " (Tipo: " + a.getTipo() + ")");
                if (a.getMercadorias().isEmpty()) {
                    writer.println("  * Nenhuma mercadoria armazenada");
                } else {
                    for (Mercadoria m : a.getMercadorias()) {
                        writer.println("  * " + m.getId() + " - " + m.getDescricao());
                    }
                }
            }

            writer.println("\n====================================================");
            writer.println("                 FIM DO RELATÓRIO                    ");
            writer.println("====================================================\n");

        } catch (IOException e) {
            System.out.println("Erro ao gerar relatório: " + e.getMessage());
        }
    }

    public static void limparRelatorio() {
        try {
            new PrintWriter(NOME_RELATORIO).close();
            System.out.println("Relatório limpo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao limpar relatório: " + e.getMessage());
        }
    }
}