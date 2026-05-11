package service;

import dao.ClienteDAO;
import java.util.List;
import java.util.Scanner;
import model.Cliente;

public class ClienteService {

    Scanner sc = new Scanner(System.in);

    ClienteDAO clienteDAO =
            new ClienteDAO();

    // =========================================
    // REGISTRAR CLIENTE
    // =========================================

    public void registrarCliente() {

        sc.nextLine();

        System.out.println(
                "\n===== REGISTRAR CLIENTE ====="
        );

        Cliente c = new Cliente();

        System.out.print(
                "DNI: "
        );

        c.setDni(
                sc.nextLine()
        );

        System.out.print(
                "Nombre: "
        );

        c.setNombre(
                sc.nextLine()
        );

        System.out.print(
                "Telefono: "
        );

        c.setTelefono(
                sc.nextLine()
        );

        System.out.print(
                "Correo: "
        );

        c.setCorreo(
                sc.nextLine()
        );

        clienteDAO.registrar(c);
    }

    // =========================================
    // LISTAR CLIENTES
    // =========================================

    public void listarClientes() {

        System.out.println(
                "\n===== LISTA CLIENTES ====="
        );

        List<Cliente> lista =
                clienteDAO.listar();

        if (lista.isEmpty()) {

            System.out.println(
                    "No existen clientes."
            );

            return;
        }

        for (Cliente c : lista) {

            c.mostrarInformacion();

            System.out.println(
                    "----------------------"
            );
        }
    }

    // =========================================
    // BUSCAR CLIENTE
    // =========================================

    public void buscarCliente() {

        sc.nextLine();

        System.out.print(
                "\nIngrese DNI cliente: "
        );

        String dni =
                sc.nextLine();

        Cliente c =
                clienteDAO.buscarPorDni(dni);

        if (c != null) {

            c.mostrarInformacion();

        } else {

            System.out.println(
                    "Cliente no encontrado."
            );
        }
    }

    // =========================================
    // ACTUALIZAR CLIENTE
    // =========================================

    public void actualizarCliente() {

        System.out.print(
                "\nIngrese ID cliente: "
        );

        int id =
                sc.nextInt();

        Cliente c =
                clienteDAO.buscarPorId(id);

        if (c == null) {

            System.out.println(
                    "Cliente no encontrado."
            );

            return;
        }

        sc.nextLine();

        System.out.print(
                "Nuevo DNI: "
        );

        c.setDni(
                sc.nextLine()
        );

        System.out.print(
                "Nuevo nombre: "
        );

        c.setNombre(
                sc.nextLine()
        );

        System.out.print(
                "Nuevo telefono: "
        );

        c.setTelefono(
                sc.nextLine()
        );

        System.out.print(
                "Nuevo correo: "
        );

        c.setCorreo(
                sc.nextLine()
        );

        clienteDAO.actualizar(c);
    }

    // =========================================
    // ELIMINAR CLIENTE
    // =========================================

    public void eliminarCliente() {

        System.out.print(
                "\nIngrese ID cliente: "
        );

        int id =
                sc.nextInt();

        clienteDAO.eliminar(id);
    }
}
