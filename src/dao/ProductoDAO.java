package dao;

import config.ConexionBD;
import dao.interfaces.Crud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Polo;
import model.Producto;

public class ProductoDAO implements Crud<Producto> {

    Connection conexion;

    public ProductoDAO() {

        conexion = ConexionBD.conectar();
    }

    @Override
    public void registrar(Producto p) {

        String sql = """
                INSERT INTO productos
                (
                    nombre,
                    tipo,
                    precio,
                    stock,
                    stock_minimo,
                    talla,
                    color,
                    atributo
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try {

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getStockMinimo());
            ps.setString(6, p.getTalla());
            ps.setString(7, p.getColor());
            ps.setString(8, p.getAtributo());

            ps.executeUpdate();

            System.out.println(
                    "Producto registrado correctamente"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Error al registrar: "
                            + e.getMessage()
            );
        }
    }

    @Override
    public List<Producto> listar() {

        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM productos";

        try {

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Producto p = new Polo();

                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setTipo(rs.getString("tipo"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                p.setStockMinimo(
                        rs.getInt("stock_minimo")
                );
                p.setTalla(rs.getString("talla"));
                p.setColor(rs.getString("color"));
                p.setAtributo(
                        rs.getString("atributo")
                );

                lista.add(p);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar: "
                            + e.getMessage()
            );
        }

        return lista;
    }

    @Override
    public Producto buscarPorId(int id) {

        return null;
    }

    @Override
    public void actualizar(Producto obj) {

    }

    @Override
    public void eliminar(int id) {

    }
}