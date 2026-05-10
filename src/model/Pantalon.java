package model;

public class Pantalon extends Producto {

    public Pantalon() {
    }

    public Pantalon(
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

        System.out.println("===== PANTALON =====");

        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Precio: " + precio);
        System.out.println("Stock: " + stock);
        System.out.println("Talla: " + talla);
        System.out.println("Color: " + color);
        System.out.println("Tipo tela: " + atributo);
    }
}