package dao;

import config.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.DetalleVenta;
import model.Venta;

//import model.Producto;

public class VentaDAO {

    Connection conexion;

    public VentaDAO() {

        conexion = ConexionBD.conectar();
    }

    // =========================================
    // REGISTRAR VENTA
    // =========================================

    public void registrarVenta(Venta venta) {

        String sqlVenta = """
                INSERT INTO ventas
                (
                    cliente_id,
                    fecha,
                    total
                )
                VALUES (?, NOW(), ?)
                """;

        String sqlDetalle = """
                INSERT INTO detalle_venta
                (
                    venta_id,
                    producto_id,
                    cantidad,
                    precio_unitario,
                    subtotal
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        String sqlStock = """
                UPDATE productos
                SET stock = stock - ?
                WHERE id = ?
                """;

        try {

            // INICIAR TRANSACCION
            conexion.setAutoCommit(false);

            // =====================================
            // INSERTAR VENTA
            // =====================================

            PreparedStatement psVenta =
                    conexion.prepareStatement(
                            sqlVenta,
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

            psVenta.setInt(
                    1,
                    venta.getCliente().getId()
            );

            psVenta.setDouble(
                    2,
                    venta.getTotal()
            );

            psVenta.executeUpdate();

            // OBTENER ID GENERADO
            ResultSet rs =
                    psVenta.getGeneratedKeys();

            int ventaId = 0;

            if (rs.next()) {

                ventaId = rs.getInt(1);
            }

            // =====================================
            // INSERTAR DETALLES
            // =====================================

            for (DetalleVenta d :
                    venta.getDetalles()) {

                // INSERT DETALLE
                PreparedStatement psDetalle =
                        conexion.prepareStatement(
                                sqlDetalle
                        );

                psDetalle.setInt(
                        1,
                        ventaId
                );

                psDetalle.setInt(
                        2,
                        d.getProducto().getId()
                );

                psDetalle.setInt(
                        3,
                        d.getCantidad()
                );

                psDetalle.setDouble(
                        4,
                        d.getPrecioUnitario()
                );

                psDetalle.setDouble(
                        5,
                        d.getSubtotal()
                );

                psDetalle.executeUpdate();

                // ACTUALIZAR STOCK
                PreparedStatement psStock =
                        conexion.prepareStatement(
                                sqlStock
                        );

                psStock.setInt(
                        1,
                        d.getCantidad()
                );

                psStock.setInt(
                        2,
                        d.getProducto().getId()
                );

                psStock.executeUpdate();
            }

            // CONFIRMAR
            conexion.commit();

            System.out.println(
                    "\nVenta registrada correctamente."
            );

        } catch (SQLException e) {

            try {

                conexion.rollback();

            } catch (SQLException ex) {

                System.out.println(
                        ex.getMessage()
                );
            }

            System.out.println(
                    "Error venta: "
                            + e.getMessage()
            );
        }
    }

    // =========================================
    // LISTAR VENTAS
    // =========================================

    public List<Venta> listarVentas() {

        List<Venta> lista =
                new ArrayList<>();

        String sql = """
                SELECT v.id,
                       c.nombre,
                       v.fecha,
                       v.total
                FROM ventas v
                INNER JOIN clientes c
                ON v.cliente_id = c.id
                """;

        try {

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Venta v = new Venta();

                v.setId(
                        rs.getInt("id")
                );

                v.setFecha(
                        rs.getTimestamp("fecha")
                                .toLocalDateTime()
                );

                v.setTotal(
                        rs.getDouble("total")
                );

                lista.add(v);

                System.out.println(
                        "\nID Venta: "
                                + rs.getInt("id")
                );

                System.out.println(
                        "Cliente: "
                                + rs.getString("nombre")
                );

                System.out.println(
                        "Fecha: "
                                + rs.getTimestamp("fecha")
                );

                System.out.println(
                        "Total: S/ "
                                + rs.getDouble("total")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error listar ventas: "
                            + e.getMessage()
            );
        }

        return lista;
    }

    // =========================================
    // VER DETALLE VENTA
    // =========================================

    public void verDetalleVenta(int ventaId) {

        String sql = """
                SELECT p.nombre,
                       d.cantidad,
                       d.precio_unitario,
                       d.subtotal
                FROM detalle_venta d
                INNER JOIN productos p
                ON d.producto_id = p.id
                WHERE d.venta_id = ?
                """;

        try {

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setInt(1, ventaId);

            ResultSet rs =
                    ps.executeQuery();

            System.out.println(
                    "\n===== DETALLE VENTA ====="
            );

            while (rs.next()) {

                System.out.println(
                        "\nProducto: "
                                + rs.getString("nombre")
                );

                System.out.println(
                        "Cantidad: "
                                + rs.getInt("cantidad")
                );

                System.out.println(
                        "Precio Unitario: S/ "
                                + rs.getDouble("precio_unitario")
                );

                System.out.println(
                        "Subtotal: S/ "
                                + rs.getDouble("subtotal")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error detalle venta: "
                            + e.getMessage()
            );
        }
    }

    // =========================================
    // REPORTE VENTAS POR FECHA
    // =========================================

    public void ventasPorFecha(
            String fechaInicio,
            String fechaFin
    ) {

        String sql = """
                SELECT v.id,
                       c.nombre,
                       v.fecha,
                       v.total
                FROM ventas v
                INNER JOIN clientes c
                ON v.cliente_id = c.id
                WHERE DATE(v.fecha)
                BETWEEN ? AND ?
                """;

        try {

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setString(1, fechaInicio);

            ps.setString(2, fechaFin);

            ResultSet rs =
                    ps.executeQuery();

            System.out.println(
                    "\n===== REPORTE VENTAS ====="
            );

            while (rs.next()) {

                System.out.println(
                        "\nVenta ID: "
                                + rs.getInt("id")
                );

                System.out.println(
                        "Cliente: "
                                + rs.getString("nombre")
                );

                System.out.println(
                        "Fecha: "
                                + rs.getTimestamp("fecha")
                );

                System.out.println(
                        "Total: S/ "
                                + rs.getDouble("total")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error reporte ventas: "
                            + e.getMessage()
            );
        }
    }

    // =========================================
    // PRODUCTOS MAS VENDIDOS
    // =========================================

    public void productosMasVendidos() {

        String sql = """
                SELECT p.nombre,
                       SUM(d.cantidad) AS total_vendido
                FROM detalle_venta d
                INNER JOIN productos p
                ON d.producto_id = p.id
                GROUP BY p.nombre
                ORDER BY total_vendido DESC
                LIMIT 5
                """;

        try {

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            System.out.println(
                    "\n===== PRODUCTOS MAS VENDIDOS ====="
            );

            while (rs.next()) {

                System.out.println(
                        "\nProducto: "
                                + rs.getString("nombre")
                );

                System.out.println(
                        "Cantidad Vendida: "
                                + rs.getInt("total_vendido")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error productos vendidos: "
                            + e.getMessage()
            );
        }
    }

    // =========================================
    // TOTAL VENDIDO
    // =========================================

    public void totalVendido() {

        String sql = """
                SELECT SUM(total) AS total_general
                FROM ventas
                """;

        try {

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                System.out.println(
                        "\nTOTAL GENERAL VENDIDO: S/ "
                                + rs.getDouble("total_general")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error total vendido: "
                            + e.getMessage()
            );
        }
    }
}