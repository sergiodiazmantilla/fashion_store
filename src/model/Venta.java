package model;

import java.time.LocalDateTime;

public class Venta {

    private int id;

    private Cliente cliente;

    private LocalDateTime fecha;

    private double total;

    public Venta() {
    }

    public Venta(
            int id,
            Cliente cliente,
            LocalDateTime fecha,
            double total
    ) {

        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}