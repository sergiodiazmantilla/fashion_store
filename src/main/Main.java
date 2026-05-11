package main;

import dao.UsuarioDAO;
import java.util.Scanner;
import service.MenuService;

public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            UsuarioDAO dao = new UsuarioDAO();

            System.out.println(
                    "===== LOGIN ====="
            );

            System.out.print("Usuario: ");

            String usuario = sc.nextLine();

            System.out.print("Password: ");

            String password = sc.nextLine();

            boolean acceso =
                    dao.login(usuario, password);

            if (acceso) {

                System.out.println(
                        "Bienvenido al sistema"
                );

                MenuService menu =
                        new MenuService();

                menu.menuPrincipal();

            } else {

                System.out.println(
                        "Datos incorrectos"
                );
            }
        }
    }
}