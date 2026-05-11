package service;

import dao.ProductoDAO;
import dao.VentaDAO;
import java.util.Scanner;

public class ReporteService {

    Scanner sc = new Scanner(System.in);

    VentaDAO ventaDAO =
            new VentaDAO();

    ProductoDAO productoDAO =
            new ProductoDAO();

    // =========================================
    // REPORTE VENTAS POR FECHA
    // =========================================

    public void reporteVentasPorFecha() {

        sc.nextLine();

        System.out.println(
                "\n===== REPORTE POR FECHAS ====="
        );

        System.out.print(
                "Fecha inicio (YYYY-MM-DD): "
        );

        String fechaInicio =
                sc.nextLine();

        System.out.print(
                "Fecha fin (YYYY-MM-DD): "
        );

        String fechaFin =
                sc.nextLine();

        ventaDAO.ventasPorFecha(
                fechaInicio,
                fechaFin
        );
    }

    // =========================================
    // PRODUCTOS MAS VENDIDOS
    // =========================================

    public void productosMasVendidos() {

        ventaDAO.productosMasVendidos();
    }

    // =========================================
    // STOCK BAJO
    // =========================================

    public void stockBajo() {

        System.out.println(
                "\n===== PRODUCTOS STOCK BAJO ====="
        );

        productoDAO.stockBajo()
                .forEach(p -> {

                    p.mostrarInformacion();

                    System.out.println(
                            "--------------------"
                    );
                });
    }

    // =========================================
    // TOTAL VENDIDO
    // =========================================

    public void totalVendido() {

        ventaDAO.totalVendido();
    }
}