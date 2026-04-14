package br.com.beanandcode.cafe.itemPedido;

import java.text.DecimalFormat;

public class ItemPedido {

        private String nomeProduto;
        private double precoUnitario;
        private int quantidade;

        public ItemPedido(String nomeProduto, double precoUnitario, int quantidade) {
            this.nomeProduto = nomeProduto;
            this.precoUnitario = precoUnitario;
            this.quantidade = quantidade;
        }

        public String getNomeProduto() {
            return nomeProduto;
        }

        public double getPrecoUnitario() {
            return precoUnitario;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setNomeProduto(String nomeProduto) {
            this.nomeProduto = nomeProduto;
        }

        public void setPrecoUnitario(double precoUnitario) {
            this.precoUnitario = precoUnitario;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }

        public double calcularSubtotal(){
            return precoUnitario*quantidade;
        }

        public String getDados(){
            DecimalFormat df = new DecimalFormat("R$ #,###.00");
            return nomeProduto+" | Quantidade: "+quantidade+" | Unitário: "+ df.format(precoUnitario)+" | Subtotal: "+ df.format(calcularSubtotal());
        }

    }
