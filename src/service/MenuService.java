package service;

import java.util.Scanner;

public class MenuService {

    Scanner sc = new Scanner(System.in);

    // SERVICES
    ProductoService productoService = new ProductoService();
    ClienteService clienteService = new ClienteService();
    VentaService ventaService = new VentaService();
    ReporteService reporteService = new ReporteService();

    // MENU PRINCIPAL
    public void menuPrincipal() {

        int opcion;

        do {

            System.out.println("\n==============================");
            System.out.println("       FASHION STORE       ");
            System.out.println("==============================");
            System.out.println("1. Gestionar productos");
            System.out.println("2. Gestionar clientes");
            System.out.println("3. Gestionar ventas");
            System.out.println("4. Reportes");
            System.out.println("5. Salir");
            System.out.print("Seleccione opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> menuProductos();
                case 2 -> menuClientes();
                case 3 -> menuVentas();
                case 4 -> menuReportes();
                case 5 -> System.out.println("\nGracias por usar el sistema.");
                default -> System.out.println("\nOpcion invalida.");
            }
        } while (opcion != 5);
    }

    // MENU PRODUCTOS
    public void menuProductos() {

        int opcion;

        do {

            System.out.println("\n========== PRODUCTOS ==========");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Eliminar producto");
            System.out.println("6. Mostrar productos con stock bajo");
            System.out.println("7. Volver");
            System.out.print("Seleccione opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> productoService.registrarProducto();
                case 2 -> productoService.listarProductos();
                case 3 -> productoService.buscarProducto();
                case 4 -> productoService.actualizarProducto();
                case 5 -> productoService.eliminarProducto();
                case 6 -> productoService.mostrarStockBajo();
                case 7 -> {
                }
                default -> System.out.println("\nOpcion invalida.");
            }
        } while (opcion != 7);
    }

    // MENU CLIENTES
    public void menuClientes() {

        int opcion;

        do {
            System.out.println("\n========== CLIENTES ==========");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Listar clientes");
            System.out.println("3. Buscar cliente por DNI");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Volver");
            System.out.print("Seleccione opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> clienteService.registrarCliente();
                case 2 -> clienteService.listarClientes();
                case 3 -> clienteService.buscarCliente();
                case 4 -> clienteService.actualizarCliente();
                case 5 -> clienteService.eliminarCliente();
                case 6 -> {
                }
                default -> System.out.println("\nOpcion invalida.");
            }
        } while (opcion != 6);
    }

    // MENU VENTAS
    public void menuVentas() {

        int opcion;

        do {

            System.out.println("\n========== VENTAS ==========");
            System.out.println("1. Registrar venta");
            System.out.println("2. Listar ventas");
            System.out.println("3. Ver detalle de venta");
            System.out.println("4. Volver");
            System.out.print("Seleccione opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> ventaService.registrarVenta();
                case 2 -> ventaService.listarVentas();
                case 3 -> ventaService.verDetalleVenta();
                case 4 -> {
                }
                default -> System.out.println("\nOpcion invalida.");
            }
        } while (opcion != 4);
    }

    // MENU REPORTES
    public void menuReportes() {

        int opcion;

        do {
            System.out.println("\n========== REPORTES ==========");
            System.out.println("1. Reporte ventas por fecha");
            System.out.println("2. Productos mas vendidos");
            System.out.println("3. Productos con stock bajo");
            System.out.println("4. Total vendido");
            System.out.println("5. Volver");
            System.out.print("Seleccione opcion: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> reporteService.reporteVentasPorFecha();
                case 2 -> reporteService.productosMasVendidos();
                case 3 -> reporteService.stockBajo();
                case 4 -> reporteService.totalVendido();
                case 5 -> {
                }
                default -> System.out.println("\nOpcion invalida.");
            }
        } while (opcion != 5);
    }
}
