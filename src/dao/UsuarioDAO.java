package dao;

import config.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    Connection conexion;

    public UsuarioDAO() {

        conexion = ConexionBD.conectar();
    }

    // LOGIN
    public boolean login(
            String usuario,
            String password
    ) {

        String sql = """
                SELECT *
                FROM usuarios
                WHERE usuario = ?
                AND password = ?
                """;

        try {

            PreparedStatement ps =
                    conexion.prepareStatement(sql);

            ps.setString(1, usuario);

            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            System.out.println(
                    "Error login: "
                            + e.getMessage()
            );
        }

        return false;
    }
}