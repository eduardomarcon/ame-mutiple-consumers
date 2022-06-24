package com.ame.amqp.producer;

public class Todo {
    private String tipo;

    public Todo() {
    }

    public Todo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}
