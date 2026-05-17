import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private DefaultTableModel modeloProductos; // modelo compartido

    public MainMenuFrame() {
        setTitle("Menú Principal - Fashion Store");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnProductos = new JButton("Productos");
        JButton btnClientes = new JButton("Clientes");
        JButton btnVentas = new JButton("Ventas");
        JButton btnReportes = new JButton("Reportes");

        add(btnProductos);
        add(btnClientes);
        add(btnVentas);
        add(btnReportes);

        // ✅ inicializar modeloProductos aquí
        String[] columnasProductos = {"Nombre", "Precio (S/)", "Stock"};
        modeloProductos = new DefaultTableModel(columnasProductos, 0);

        // ✅ pasar el modelo compartido
        btnProductos.addActionListener(e -> new ProductosFrame(modeloProductos));
        btnClientes.addActionListener(e -> new ClientesFrame());
        btnVentas.addActionListener(e -> new VentasFrame(modeloProductos));
        btnReportes.addActionListener(e -> new ReportesFrame());

        setVisible(true);
    }
}
