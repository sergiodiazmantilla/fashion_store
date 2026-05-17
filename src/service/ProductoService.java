package service;

import dao.ProductoDAO;
import java.util.List;
import java.util.Scanner;
import model.*;

public class ProductoService {

    Scanner sc = new Scanner(System.in);

    ProductoDAO productoDAO =new ProductoDAO();

    // REGISTRAR PRODUCTO
    public void registrarProducto() {

        sc.nextLine();

        System.out.println("\n===== REGISTRAR PRODUCTO =====");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.println("\nTipo:");
        System.out.println("1. Polo");
        System.out.println("2. Camisa");
        System.out.println("3. Pantalon");
        System.out.println("4. Calzado");
        System.out.print("Seleccione tipo: ");

        int opcion =sc.nextInt();
        sc.nextLine();
        Producto p;

        switch (opcion) {

            case 1 -> {
                p = new Polo();
                p.setTipo("Polo");
            }

            case 2 -> {
                p = new Camisa();
                p.setTipo("Camisa");
            }

            case 3 -> {
                p = new Pantalon();
                p.setTipo("Pantalon");
            }

            case 4 -> {
                p = new Calzado();
                p.setTipo("Calzado");
            }

            default -> {
                System.out.println("Tipo invalido");
                return;
            }
        }
        p.setNombre(nombre);

        System.out.print("Precio: ");
        p.setPrecio(sc.nextDouble());

        System.out.print("Stock inicial: ");
        p.setStock(sc.nextInt());

        System.out.print("Stock minimo: ");
        p.setStockMinimo(sc.nextInt());

        sc.nextLine();

        System.out.print("Talla: ");
        p.setTalla(sc.nextLine());

        System.out.print("Color: ");
        p.setColor(sc.nextLine());

        System.out.print("Tipo Cuello: ");
        p.setAtributo(sc.nextLine());

        productoDAO.registrar(p);
    }

    // LISTAR PRODUCTOS
    public void listarProductos() {

        System.out.println("\n===== LISTA PRODUCTOS =====");

        List<Producto> lista = productoDAO.listar();

        if (lista.isEmpty()) {
            System.out.println("No existen productos.");
            return;
        }

        for (Producto p : lista) {
            p.mostrarInformacion();
            System.out.println("----------------------");
        }
    }

    // BUSCAR PRODUCTO
    public void buscarProducto() {

        System.out.print("\nIngrese ID producto: ");

        int id =sc.nextInt();

        Producto p =productoDAO.buscarPorId(id);

        if (p != null) {
            p.mostrarInformacion();
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    // ACTUALIZAR PRODUCTO
    public void actualizarProducto() {

        System.out.print("\nIngrese ID producto: ");
        int id =sc.nextInt();

        Producto p =productoDAO.buscarPorId(id);

        if (p == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        sc.nextLine();
        System.out.print("Nuevo nombre: ");
        p.setNombre(sc.nextLine());

        System.out.print("Nuevo precio: ");
        p.setPrecio(sc.nextDouble());

        System.out.print("Nuevo stock: ");
        p.setStock(sc.nextInt());

        System.out.print("Nuevo stock minimo: ");
        p.setStockMinimo(sc.nextInt());
        sc.nextLine();

        System.out.print("Nueva talla: ");
        p.setTalla(sc.nextLine());

        System.out.print("Nuevo color: ");
        p.setColor(sc.nextLine());

        System.out.print("Nueva Tipo Cuello: ");
        p.setAtributo(sc.nextLine());

        productoDAO.actualizar(p);
    }

    // ELIMINAR PRODUCTO
    public void eliminarProducto() {
        System.out.print("\nIngrese ID producto: ");
        int id =sc.nextInt();
        productoDAO.eliminar(id);
    }

    // STOCK BAJO
    public void mostrarStockBajo() {

        System.out.println("\n===== STOCK BAJO =====");

        List<Producto> lista =productoDAO.stockBajo();

        if (lista.isEmpty()) {
            System.out.println("No hay productos con stock bajo.");
            return;
        }

        for (Producto p : lista) {
            p.mostrarInformacion();
            System.out.println("----------------------");
        }
    }
}