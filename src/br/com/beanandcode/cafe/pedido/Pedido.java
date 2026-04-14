package br.com.beanandcode.cafe.pedido;

import br.com.beanandcode.cafe.cliente.Cliente;
import br.com.beanandcode.cafe.itemPedido.ItemPedido;

import java.text.DecimalFormat;

public class Pedido {
    private int numeroPedido;
    private Cliente cliente;
    private ItemPedido[] itens ;
    private int index;
    private String status;

    public Pedido(int numeroPedido, Cliente cliente) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.itens = new ItemPedido[20];
        this.index = 0;
        this.status = "Aberto";
    }

    public Cliente getCliente() {
        return cliente;
    }
    public int getNumeroPedido() {
        return numeroPedido;
    }
    public ItemPedido[] getItens() {
        return itens;
    }
    public int getIndex() {
        return index;
    }
    public String getStatus() {
        return status;
    }
    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setItens(ItemPedido[] itens) {
        this.itens = itens;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void adicionarItem(ItemPedido item){
        if (index<20){
            itens [index] = item;
            index++;
        }else {
            System.out.println("LIMITE DE ITENS ATINGIDO!");
        }
    }

    public double calcularTotal(){
        double total = 0;
        for (int i = 0; i < index; i++) {// utiliza o index para percorrer apenas os vetores prenchidos
            total += itens[i].calcularSubtotal();
        }
        return total;
    }

    public String getDados(){
        DecimalFormat df = new DecimalFormat("R$ #,###.00");
        String dados = "Número do pedido: "+numeroPedido+ "\n";
        dados += cliente.getDados()+ "\n"; //adicionamos += para add texto e não apagar o anterior
        dados += " #### Itens #### "+ "\n";
        for (int i = 0; i < index; i++) {
            dados += itens[i].getDados()+ "\n";
        }
        dados += "Valor total: "+df.format(calcularTotal())+ "\n";
        return dados;

    }

}
