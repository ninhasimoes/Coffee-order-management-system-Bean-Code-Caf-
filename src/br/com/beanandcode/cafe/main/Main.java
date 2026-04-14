package br.com.beanandcode.cafe.main;

import br.com.beanandcode.cafe.cliente.Cliente;
import br.com.beanandcode.cafe.itemPedido.ItemPedido;
import br.com.beanandcode.cafe.pedido.Pedido;

import java.util.Scanner;

public class Main {static Scanner sc = new Scanner(System.in);
    static Pedido[] pedidos = new Pedido[100];
    static int indexPedido = 0;
    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println();
            System.out.println("#### Bean & Code Café ####");
            System.out.println("[1] Registrar novo pedido");
            System.out.println("[2] Adicionar item a um pedido");
            System.out.println("[3] Finalizar pedido");
            System.out.println("[4] Listar pedidos em aberto");
            System.out.println("[5] Exibir faturamento do dia");
            System.out.println("[6] Sair");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao){
                case 1->registrarPedido();
                case 2->adicionarItem();
                case 3->finalizarPedido();
                case 4->listaPedidosAbertos();
                case 5->exibirFaturamento();
                case 6-> System.out.println("Sistema Bean & Code encerrado. Até logo!");
                default -> System.out.println("Opção Inválida");
            }

        }while (opcao!=6);


    }

    private static void exibirFaturamento() {
        double total = 0;
        for (int i = 0; i < indexPedido; i++) {
            if (pedidos[i].getStatus().equalsIgnoreCase("FINALIZADO"))
                total += pedidos[i].calcularTotal();
        }
        System.out.println("Faturamento do dia: R$"+total);

    }
    private static void listaPedidosAbertos() {
        for (int i = 0; i < indexPedido; i++) {
            boolean status = false;
            if(pedidos[i].getStatus().equalsIgnoreCase("ABERTO")){
                System.out.println(pedidos[i].getDados());
                status = true;
            }
            if (!status){
                System.out.println("Nenhum pedido em aberto no momento.");
            }


        }

    }
    private static void finalizarPedido() {
        System.out.print("Número do pedido --> ");
        int num = sc.nextInt();

        Pedido pesquisar = pesquisarPedido(num);
        if (pesquisar == null) {
            System.out.println("Pedido Não Encontrado!");
            return;
        }
        if (pesquisar.getStatus().equals("FINALIZADO")) {
            System.out.println("Não é possível adicionar itens!");
            return;
        }
        System.out.println("Total do pedido R$ %.2f\n"+pesquisar.calcularTotal());
        pesquisar.setStatus("FINALIZADO");

        System.out.println(pesquisar.getDados());

    }
    private static void adicionarItem() {
        System.out.print("Número do pedido --> ");
        int num = sc.nextInt();

        Pedido pesquisar = pesquisarPedido(num);
        if (pesquisar == null) {
            System.out.println("Pedido Não Encontrado!");
            return;
        }
        if (pesquisar.getStatus().equals("FINALIZADO")) {
            System.out.println("Não é possível adicionar itens!");
            return;
        }
        System.out.print("Nome do Produto --> ");
        String produto = sc.next();
        System.out.print("Preço Unitário --> ");
        double precoUnitario = sc.nextDouble();
        System.out.print("Quantidade --> ");
        int quant = sc.nextInt();

        ItemPedido ip = new ItemPedido(produto,precoUnitario,quant);
        pesquisar.adicionarItem(ip);

    }
    private static void registrarPedido() {
        System.out.print("Número do pedido --> ");
        int num = sc.nextInt();

        Pedido pesquisar = pesquisarPedido(num);
        if (pesquisar != null) {
            System.out.println("Pedido já existe!");
            return;
        }


        System.out.print("Nome do Cliente --> ");
        String nome = sc.next();

        System.out.print("CPF do Cliente --> ");
        String cpf = sc.next();

        Cliente cl = new Cliente(cpf,nome);
        Pedido p = new Pedido(num,cl);
        pedidos[indexPedido] =p;
        indexPedido++;
    }
    // pesquisar se o numero do pedido ja foi registrado
    public static Pedido pesquisarPedido(int numeroPedido) {
        for (int i=0;i<indexPedido;i++){
            if (pedidos[i].getNumeroPedido() == numeroPedido){
                return pedidos[i]; //caso tenha achado o pedido no registro ele finaliza o codigo aqui e não le mais nenhuma linha abaixo
            }
        }
        return null;

    }
}
