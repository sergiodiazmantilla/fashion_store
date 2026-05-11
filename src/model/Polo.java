package model;

public class Polo extends Producto {

    public Polo() {
    }

    public Polo(
            int id,
            String nombre,
            String tipo,
            double precio,
            int stock,
            int stockMinimo,
            String talla,
            String color,
            String atributo
    ) {

        super(
                id,
                nombre,
                tipo,
                precio,
                stock,
                stockMinimo,
                talla,
                color,
                atributo
        );
    }

    @Override
    public void mostrarInformacion() {

        System.out.println("===== POLO =====");

        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Talla: " + talla);
        System.out.println("Color: " + color);
        System.out.println("Tipo Cuello: " + atributo);
    }
}