package service;

import dao.ClienteDAO;
import dao.ProductoDAO;
import dao.VentaDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Cliente;
import model.DetalleVenta;
import model.Producto;
import model.Venta;

public class VentaService {

    Scanner sc = new Scanner(System.in);

    VentaDAO ventaDAO =
            new VentaDAO();

    ClienteDAO clienteDAO =
            new ClienteDAO();

    ProductoDAO productoDAO =
            new ProductoDAO();

    // =========================================
    // REGISTRAR VENTA
    // =========================================

    public void registrarVenta() {

        sc.nextLine();

        System.out.println(
                "\n===== REGISTRAR VENTA ====="
        );

        // =====================================
        // BUSCAR CLIENTE
        // =====================================

        System.out.print(
                "Ingrese DNI cliente: "
        );

        String dni =
                sc.nextLine();

        Cliente cliente =
                clienteDAO.buscarPorDni(dni);

        if (cliente == null) {

            System.out.println(
                    "Cliente no encontrado."
            );

            return;
        }

        // =====================================
        // CREAR VENTA
        // =====================================

        Venta venta = new Venta();

        venta.setCliente(cliente);

        venta.setFecha(
                LocalDateTime.now()
        );

        List<DetalleVenta> detalles =
                new ArrayList<>();

        double total = 0;

        int opcion = 0;

        // =====================================
        // AGREGAR PRODUCTOS
        // =====================================

        do {

            System.out.println(
                    "\n===== PRODUCTOS ====="
            );

            productoDAO.listar();

            System.out.print(
                    "\nIngrese ID producto: "
            );

            int productoId =
                    sc.nextInt();

            Producto producto =
                    productoDAO.buscarPorId(productoId);

            if (producto == null) {

                System.out.println(
                        "Producto no encontrado."
                );

                continue;
            }

            System.out.print(
                    "Cantidad: "
            );

            int cantidad =
                    sc.nextInt();

            // VALIDAR STOCK

            if (cantidad > producto.getStock()) {

                System.out.println(
                        "Stock insuficiente."
                );

                continue;
            }

            // CALCULAR SUBTOTAL

            double subtotal =
                    cantidad * producto.getPrecio();

            // CREAR DETALLE

            DetalleVenta detalle =
                    new DetalleVenta();

            detalle.setProducto(producto);

            detalle.setCantidad(cantidad);

            detalle.setPrecioUnitario(
                    producto.getPrecio()
            );

            detalle.setSubtotal(subtotal);

            detalles.add(detalle);

            total += subtotal;

            System.out.println(
                    "\nProducto agregado."
            );

            System.out.println(
                    "Subtotal: S/ " + subtotal
            );

            System.out.println(
                    "Total actual: S/ " + total
            );

            System.out.println(
                    "\n1. Agregar otro producto"
            );

            System.out.println(
                    "2. Finalizar venta"
            );

            System.out.print(
                    "Seleccione opcion: "
            );

            opcion = sc.nextInt();

        } while (opcion == 1);

        // =====================================
        // FINALIZAR
        // =====================================

        venta.setDetalles(detalles);

        venta.setTotal(total);

        ventaDAO.registrarVenta(venta);

        System.out.println(
                "\n===== RESUMEN ====="
        );

        System.out.println(
                "Cliente: "
                        + cliente.getNombre()
        );

        System.out.println(
                "Total venta: S/ "
                        + total
        );
    }

    // =========================================
    // LISTAR VENTAS
    // =========================================

    public void listarVentas() {

        System.out.println(
                "\n===== LISTA VENTAS ====="
        );

        ventaDAO.listarVentas();
    }

    // =========================================
    // VER DETALLE VENTA
    // =========================================

    public void verDetalleVenta() {

        System.out.print(
                "\nIngrese ID venta: "
        );

        int ventaId =
                sc.nextInt();

        ventaDAO.verDetalleVenta(ventaId);
    }
}