package view;

import java.awt.*;
import javax.swing.*;

public class MainMenuFrame extends JFrame {

    private JButton btnProductos;

    private JButton btnClientes;

    private JButton btnVentas;

    private JButton btnReportes;

    private JButton btnSalir;

    @SuppressWarnings("unused")
    private ProductosFrame productosFrame;

    public MainMenuFrame() {

        setTitle("Fashion Store");

        setSize(500, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLayout(null);

        inicializarComponentes();

        setVisible(true);
    }

    // =====================================
    // COMPONENTES
    // =====================================

    private void inicializarComponentes() {

        JLabel lblTitulo =new JLabel("FASHION STORE");
        lblTitulo.setFont(new Font("Arial",Font.BOLD,24));
        lblTitulo.setBounds(130,30,300,30);
        add(lblTitulo);

        // PRODUCTOS
        btnProductos =new JButton("Productos");
        btnProductos.setBounds(150,90,180,35);
        add(btnProductos);

        // CLIENTES
        btnClientes =new JButton("Clientes");
        btnClientes.setBounds(150,140,180,35);
        add(btnClientes);

        // VENTAS
        btnVentas =new JButton("Ventas");
        btnVentas.setBounds(150,190,180,35);add(btnVentas);

        // REPORTES
        btnReportes =new JButton("Reportes");
        btnReportes.setBounds(150,240,180,35);
        add(btnReportes);

        // SALIR
        btnSalir =new JButton("Salir");
        btnSalir.setBounds(150,290,180,35);
        add(btnSalir);

        // EVENTOS
        btnProductos.addActionListener(e -> {

        productosFrame = new ProductosFrame();
        });

        btnClientes.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Modulo Clientes"
            );
        });

        btnVentas.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Modulo Ventas"
            );
        });

        btnReportes.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Modulo Reportes"
            );
        });

        btnSalir.addActionListener(e -> {

            System.exit(0);
        });
    }
}