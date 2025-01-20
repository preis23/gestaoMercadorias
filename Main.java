import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String CURRENT_USER = "Pedro Reis";
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.parse("2025-01-20 23:09:25", 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    
    private static ArrayList<Armazem> armazens = new ArrayList<>();
    private static ArrayList<Mercadoria> mercadorias = new ArrayList<>();
    private static ArrayList<Transporte> transportes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Persistencia persistencia = new Persistencia();

    public static void main(String[] args) {
        System.out.println("Data/Hora atual: " + CURRENT_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("Utilizador: " + CURRENT_USER);
        
        // Carregar dados salvos
        persistencia.carregarDados(armazens, mercadorias, transportes);

        int opcao;
        do {
            System.out.println("\n=== Sistema de Gestão de Armazéns ===");
            System.out.println("Data/Hora: " + CURRENT_DATE.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            System.out.println("Utilizador: " + CURRENT_USER);
            System.out.println("\nOpções:");
            System.out.println("1. Criar Armazém");
            System.out.println("2. Criar Mercadoria");
            System.out.println("3. Criar Transporte");
            System.out.println("4. Adicionar Mercadoria ao Armazém");
            System.out.println("5. Remover Mercadoria do Armazém");
            System.out.println("6. Movimentar Mercadoria");
            System.out.println("7. Localizar Mercadoria por Tag IoT");
            System.out.println("8. Gerar Relatório de Mercadorias");
            System.out.println("9. Limpar Relatório");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = lerInteiro();

            try {
                switch (opcao) {
                    case 1: criarArmazem(); break;
                    case 2: criarMercadoria(); break;
                    case 3: criarTransporte(); break;
                    case 4: adicionarMercadoriaArmazem(); break;
                    case 5: removerMercadoriaArmazem(); break;
                    case 6: movimentarMercadoria(); break;
                    case 7: localizarMercadoria(); break;
                    case 8: 
                        System.out.println("A gerar relatório de mercadorias...");
                        Relatorio.gerarRelatorioMercadorias(mercadorias, armazens);
                        System.out.println("Relatório atualizado com sucesso!");
                        System.out.println("Arquivo gerado: relatorio_mercadorias.txt");
                        break;
                    case 9:
                        System.out.println("Limpando relatório...");
                        Relatorio.limparRelatorio();
                        break;
                    case 0:
                        System.out.println("Salvando dados...");
                        persistencia.salvarDados(armazens, mercadorias, transportes);
                        System.out.println("Sistema encerrado!");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
        
        scanner.close();
    }

    private static void criarArmazem() {
        System.out.println("\n=== Criar Novo Armazém ===");
        System.out.print("Nome do armazém: ");
        String nome = scanner.nextLine();
        System.out.print("Morada: ");
        String morada = scanner.nextLine();
        System.out.print("Espaço total (m³): ");
        double espacoTotal = lerDouble();

        System.out.println("Tipo de armazém:");
        System.out.println("1. Normal");
        System.out.println("2. Reciclagem");
        System.out.print("Escolha o tipo: ");
        int tipoEscolha = lerInteiro();
        
        String tipo;
        switch (tipoEscolha) {
            case 1: tipo = "normal"; break;
            case 2: tipo = "reciclagem"; break;
            default: 
                System.out.println("Tipo inválido! Definindo como normal.");
                tipo = "normal";
        }

        Armazem armazem = new Armazem(nome, morada, espacoTotal, tipo);
        armazens.add(armazem);
        System.out.println("Armazém criado com sucesso!");
    }

    private static void criarMercadoria() {
        System.out.println("\n=== Criar Nova Mercadoria ===");
        System.out.print("Tag IoT da mercadoria: ");
        String id = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Peso (kg): ");
        double peso = lerDouble();
        System.out.print("Volume (m³): ");
        double volume = lerDouble();
        
        System.out.println("Tipo de mercadoria:");
        System.out.println("1. Normal");
        System.out.println("2. Frágil");
        System.out.println("3. Perecível");
        System.out.print("Escolha o tipo: ");
        int tipoEscolha = lerInteiro();
        
        String tipo;
        switch (tipoEscolha) {
            case 1: tipo = "normal"; break;
            case 2: tipo = "fragil"; break;
            case 3: tipo = "perecivel"; break;
            default: 
                System.out.println("Tipo inválido! Definindo como normal.");
                tipo = "normal";
        }

        Mercadoria mercadoria = new Mercadoria(id, descricao, peso, volume, tipo);
        mercadorias.add(mercadoria);
        System.out.println("Mercadoria criada com sucesso!");
    }

    private static void criarTransporte() {
        System.out.println("\n=== Criar Novo Transporte ===");
        System.out.print("ID do transporte: ");
        String id = scanner.nextLine();
        
        System.out.println("Tipo de transporte:");
        System.out.println("1. Interno");
        System.out.println("2. Externo");
        System.out.print("Escolha o tipo: ");
        int tipoEscolha = lerInteiro();
        String tipo = tipoEscolha == 1 ? "interno" : "externo";

        System.out.print("Capacidade de peso (kg): ");
        double capacidadePeso = lerDouble();
        System.out.print("Capacidade de volume (m³): ");
        double capacidadeVolume = lerDouble();
        
        System.out.print("É transporte especial? (S/N): ");
        boolean transporteEspecial = scanner.nextLine().trim().equalsIgnoreCase("S");

        Transporte transporte = new Transporte(id, tipo, capacidadePeso, capacidadeVolume, transporteEspecial);
        transportes.add(transporte);
        System.out.println("Transporte criado com sucesso!");
    }

    private static void adicionarMercadoriaArmazem() {
        if (armazens.isEmpty() || mercadorias.isEmpty()) {
            System.out.println("É necessário ter armazéns e mercadorias cadastrados!");
            return;
        }

        System.out.println("\n=== Adicionar Mercadoria ao Armazém ===");
        
        // Listar armazéns disponíveis
        System.out.println("\nArmazéns disponíveis:");
        for (int i = 0; i < armazens.size(); i++) {
            System.out.println(i + ". " + armazens.get(i).getNome());
        }
        System.out.print("Escolha o armazém: ");
        int armazemIndex = lerInteiro();

        if (armazemIndex < 0 || armazemIndex >= armazens.size()) {
            System.out.println("Armazém inválido!");
            return;
        }

        // Listar mercadorias disponíveis
        System.out.println("\nMercadorias disponíveis:");
        ArrayList<Mercadoria> mercadoriasDisponiveis = new ArrayList<>();
        for (Mercadoria m : mercadorias) {
            if (m.getLocalizacao().equals("Sem localização")) {
                mercadoriasDisponiveis.add(m);
                System.out.println(mercadoriasDisponiveis.size() - 1 + ". " + m.getDescricao());
            }
        }

        if (mercadoriasDisponiveis.isEmpty()) {
            System.out.println("Não há mercadorias disponíveis para adicionar!");
            return;
        }

        System.out.print("Escolha a mercadoria: ");
        int mercadoriaIndex = lerInteiro();

        if (mercadoriaIndex < 0 || mercadoriaIndex >= mercadoriasDisponiveis.size()) {
            System.out.println("Mercadoria inválida!");
            return;
        }

        Armazem armazem = armazens.get(armazemIndex);
        Mercadoria mercadoria = mercadoriasDisponiveis.get(mercadoriaIndex);

        if (armazem.adicionarMercadoria(mercadoria)) {
            System.out.println("Mercadoria adicionada com sucesso ao armazém!");
        } else {
            System.out.println("Não foi possível adicionar a mercadoria (espaço insuficiente)!");
        }
    }

    private static void removerMercadoriaArmazem() {
        if (armazens.isEmpty()) {
            System.out.println("Não há armazéns cadastrados!");
            return;
        }

        System.out.println("\n=== Remover Mercadoria do Armazém ===");
        
        // Listar armazéns
        System.out.println("\nArmazéns disponíveis:");
        for (int i = 0; i < armazens.size(); i++) {
            System.out.println(i + ". " + armazens.get(i).getNome());
        }
        System.out.print("Escolha o armazém: ");
        int armazemIndex = lerInteiro();

        if (armazemIndex < 0 || armazemIndex >= armazens.size()) {
            System.out.println("Armazém inválido!");
            return;
        }

        Armazem armazem = armazens.get(armazemIndex);
        if (armazem.getMercadorias().isEmpty()) {
            System.out.println("Este armazém não possui mercadorias!");
            return;
        }

        // Listar mercadorias do armazém
        System.out.println("\nMercadorias no armazém:");
        for (int i = 0; i < armazem.getMercadorias().size(); i++) {
            Mercadoria m = armazem.getMercadorias().get(i);
            System.out.println(i + ". " + m.getDescricao() + " (Tag IoT: " + m.getId() + ")");
        }

        System.out.print("Escolha a mercadoria a remover: ");
        int mercadoriaIndex = lerInteiro();

        if (mercadoriaIndex < 0 || mercadoriaIndex >= armazem.getMercadorias().size()) {
            System.out.println("Mercadoria inválida!");
            return;
        }

        String idMercadoria = armazem.getMercadorias().get(mercadoriaIndex).getId();
        if (armazem.removerMercadoria(idMercadoria)) {
            System.out.println("Mercadoria removida com sucesso!");
        } else {
            System.out.println("Erro ao remover mercadoria!");
        }
    }

    private static void movimentarMercadoria() {
        if (transportes.isEmpty()) {
            System.out.println("Não há transportes criados!");
            return;
        }

        System.out.println("\n=== Movimentar Mercadoria ===");
        
        // Listar armazéns origem
        System.out.println("\nArmazéns de origem disponíveis:");
        for (int i = 0; i < armazens.size(); i++) {
            System.out.println(i + ". " + armazens.get(i).getNome() + " (Tipo: " + armazens.get(i).getTipo() + ")");
        }
        System.out.print("Escolha o armazém de origem: ");
        int origemIndex = lerInteiro();

        if (origemIndex < 0 || origemIndex >= armazens.size()) {
            System.out.println("Armazém de origem inválido!");
            return;
        }

        Armazem origem = armazens.get(origemIndex);

        // Verificar se é armazém de reciclagem
        if (origem.getTipo().equals("reciclagem")) {
            System.out.println("Erro: Não é possível retirar mercadorias do armazém de reciclagem!");
            return;
        }

        // Listar armazéns destino
        System.out.println("\nArmazéns de destino disponíveis:");
        for (int i = 0; i < armazens.size(); i++) {
            if (i != origemIndex) {
                System.out.println(i + ". " + armazens.get(i).getNome() + " (Tipo: " + armazens.get(i).getTipo() + ")");
            }
        }
        System.out.print("Escolha o armazém de destino: ");
        int destinoIndex = lerInteiro();

        if (destinoIndex < 0 || destinoIndex >= armazens.size() || destinoIndex == origemIndex) {
            System.out.println("Armazém de destino inválido!");
            return;
        }

        Armazem destino = armazens.get(destinoIndex);
        
        // Verificar se é necessário transporte especial
        boolean precisaTransporteEspecial = destino.getTipo().equals("reciclagem");
        
        // Listar transportes disponíveis e compatíveis
        System.out.println("\nTransportes disponíveis:");
        ArrayList<Transporte> transportesCompativeis = new ArrayList<>();
        for (Transporte t : transportes) {
            if (!precisaTransporteEspecial || t.isTransporteEspecial()) {
                transportesCompativeis.add(t);
                System.out.println(transportesCompativeis.size() - 1 + ". " + t.getId() + 
                    " (Tipo: " + t.getTipo() + ", " + 
                    (t.isTransporteEspecial() ? "Especial" : "Normal") + ")");
            }
        }

        if (transportesCompativeis.isEmpty()) {
            System.out.println("Não há transportes compatíveis disponíveis!");
            return;
        }

        System.out.print("Escolha o transporte: ");
        int transporteIndex = lerInteiro();

        if (transporteIndex < 0 || transporteIndex >= transportesCompativeis.size()) {
            System.out.println("Transporte inválido!");
            return;
        }

        Transporte transporte = transportesCompativeis.get(transporteIndex);

        // Selecionar mercadoria para transportar
        ArrayList<Mercadoria> mercadoriasDisponiveis = origem.getMercadorias();
        if (mercadoriasDisponiveis.isEmpty()) {
            System.out.println("Não há mercadorias disponíveis neste armazém!");
            return;
        }

        System.out.println("\nMercadorias disponíveis para transporte:");
        for (int i = 0; i < mercadoriasDisponiveis.size(); i++) {
            Mercadoria m = mercadoriasDisponiveis.get(i);
            System.out.println(i + ". " + m.getDescricao() + 
                " (Tag IoT: " + m.getId() + 
                ", Peso: " + m.getPeso() + "kg" +
                ", Volume: " + m.getVolume() + "m³)");
        }

        System.out.print("Escolha a mercadoria: ");
        int mercadoriaIndex = lerInteiro();

        if (mercadoriaIndex < 0 || mercadoriaIndex >= mercadoriasDisponiveis.size()) {
            System.out.println("Mercadoria inválida!");
            return;
        }

        Mercadoria mercadoria = mercadoriasDisponiveis.get(mercadoriaIndex);

        // Processo de movimentação
        if (origem.removerMercadoria(mercadoria.getId())) {
            // Corrigido para passar apenas a mercadoria e o tipo de destino
            if (transporte.adicionarMercadoria(mercadoria, destino.getTipo())) {
                System.out.println("Mercadoria carregada no transporte!");
                
                // Simular transporte
                System.out.println("Transportando mercadoria...");
                
                if (destino.adicionarMercadoria(mercadoria)) {
                    transporte.removerMercadoria(mercadoria.getId());
                    System.out.println("Mercadoria entregue com sucesso no destino!");
                } else {
                    // Se não conseguir entregar, volta para origem
                    origem.adicionarMercadoria(mercadoria);
                    System.out.println("Erro: Destino sem espaço. Mercadoria retornada à origem!");
                }
            } else {
                // Se não conseguir carregar, volta para origem
                origem.adicionarMercadoria(mercadoria);
                System.out.println("Erro: Transporte sem capacidade ou incompatível!");
            }
        } else {
            System.out.println("Erro ao remover mercadoria da origem!");
        }
    }

    private static void localizarMercadoria() {
        System.out.println("\n=== Localizar Mercadoria por Tag IoT ===");
        System.out.print("Digite a tag IoT da mercadoria: ");
        String tagIoT = scanner.nextLine();

        boolean encontrada = false;
        for (Mercadoria m : mercadorias) {
            if (m.getId().equals(tagIoT)) {
                System.out.println("\nMercadoria encontrada:");
                System.out.println("Tag IoT: " + m.getId());
                System.out.println("Descrição: " + m.getDescricao());
                System.out.println("Localização atual: " + m.getLocalizacao());
                System.out.println("Estado: " + m.getEstado());
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            System.out.println("Mercadoria não encontrada!");
        }
    }

    private static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite um número inteiro: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Valor inválido! Digite um número decimal: ");
            }
        }
    }
}