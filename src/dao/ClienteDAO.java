package dao;

import config.ConexionBD;
import dao.interfaces.Crud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDAO implements Crud<Cliente> {

    Connection conexion;

    public ClienteDAO() {
        conexion = ConexionBD.conectar();
    }

    // REGISTRAR
    @Override
    public void registrar(Cliente c) {

        String sql = 
                """
                INSERT INTO clientes
                (dni,nombre,telefono,correo)
                VALUES (?, ?, ?, ?)
                """;

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getCorreo());
            ps.executeUpdate();

            System.out.println("Cliente registrado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al registrar cliente: "+ e.getMessage());
        }
    }

    // LISTAR
    @Override
    public List<Cliente> listar() {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM clientes";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();

                c.setId(rs.getInt("id"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getString("telefono"));
                c.setCorreo(rs.getString("correo"));

                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: "+ e.getMessage());
        }

        return lista;
    }

    // BUSCAR POR ID
    @Override
    public Cliente buscarPorId(int id) {

        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente c = null;

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getString("telefono"));
                c.setCorreo(rs.getString("correo"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: "+ e.getMessage());
        }
        return c;
    }

    // ACTUALIZAR
    @Override
    public void actualizar(Cliente c) {

        String sql = """
                UPDATE clientes
                SET
                    dni = ?,
                    nombre = ?,
                    telefono = ?,
                    correo = ?
                WHERE id = ?
                """;

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, c.getDni());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getCorreo());
            ps.setInt(5, c.getId());
            ps.executeUpdate();
            System.out.println("Cliente actualizado correctamente");

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: "+ e.getMessage());
        }
    }

    // ELIMINAR
    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM clientes WHERE id = ?";

        try {

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, id);
            ps.executeUpdate();

            System.out.println("Cliente eliminado correctamente");

        } catch (SQLException e) {

            System.out.println("Error al eliminar cliente: "+ e.getMessage());
        }
    }

    // BUSCAR POR DNI
    public Cliente buscarPorDni(String dni) {

        String sql = "SELECT * FROM clientes WHERE dni = ?";

        Cliente c = null;

        try {

            PreparedStatement ps =conexion.prepareStatement(sql);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setDni(rs.getString("dni"));
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getString("telefono"));
                c.setCorreo(rs.getString("correo"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar DNI: "+ e.getMessage());
        }

        return c;
    }
}