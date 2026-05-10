package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // URL de la base de datos
    private static final String URL =
            "jdbc:mysql://localhost:3306/fashion_store_db";

    // Usuario de MySQL
    private static final String USER = "root";

    // Contraseña de MySQL
    private static final String PASSWORD = "";

    // Método para conectar
    public static Connection conectar() {

        Connection conexion = null;

        try {

            conexion = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Conexion exitosa a MySQL");

        } catch (SQLException e) {

            System.out.println(
                    "Error de conexion: " + e.getMessage()
            );
        }

        return conexion;
    }
}