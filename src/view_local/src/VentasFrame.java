import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VentasFrame extends JFrame {
    private JComboBox<String> cmbProducto;
    private JTextField txtCantidad = new JTextField();
    private DefaultTableModel modeloVentas;
    private DefaultTableModel modeloProductos; // referencia al modelo de productos

    public VentasFrame(DefaultTableModel modeloProductos) {
        setTitle("Registro de Ventas - Fashion Store");
        setSize(650, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 🎨 Colores y estilo
        Color encabezado = new Color(6, 90, 130);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 18);

        JLabel lblTitulo = new JLabel("Registro de Ventas", SwingConstants.CENTER);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(encabezado);
        add(lblTitulo, BorderLayout.NORTH);

        // Guardar referencia al modelo de productos
        this.modeloProductos = modeloProductos;

        // Combo con productos desde modeloProductos
        cmbProducto = new JComboBox<>();
        for (int i = 0; i < modeloProductos.getRowCount(); i++) {
            cmbProducto.addItem(modeloProductos.getValueAt(i, 0).toString());
        }

        // Formulario arriba
        JPanel formulario = new JPanel(new GridLayout(2, 2, 5, 5));
        formulario.add(new JLabel("Producto:"));
        formulario.add(cmbProducto);
        formulario.add(new JLabel("Cantidad:"));
        formulario.add(txtCantidad);
        add(formulario, BorderLayout.NORTH);

        // Tabla de ventas
        String[] columnas = {"Producto", "Cantidad", "Total (S/)", "Fecha"};
        modeloVentas = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modeloVentas);
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(encabezado);
        tabla.getTableHeader().setForeground(Color.WHITE);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRegistrar = new JButton("Registrar Venta");
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnReportes = new JButton("Ver Reportes");
        panelBotones.add(btnCancelar);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnReportes);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        btnRegistrar.addActionListener(e -> registrarVenta());
        btnCancelar.addActionListener(e -> txtCantidad.setText(""));
        btnReportes.addActionListener(e -> new ReportesFrame(modeloVentas));

        setVisible(true);
    }

    private void registrarVenta() {
        String producto = (String) cmbProducto.getSelectedItem();
        String cantidadStr = txtCantidad.getText().trim();

        if (!cantidadStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero");
            return;
        }

        int cantidad = Integer.parseInt(cantidadStr);

        // Buscar stock en modeloProductos
        int filaProducto = -1;
        for (int i = 0; i < modeloProductos.getRowCount(); i++) {
            if (modeloProductos.getValueAt(i, 0).toString().equals(producto)) {
                filaProducto = i;
                break;
            }
        }

        if (filaProducto == -1) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado en inventario");
            return;
        }

        int stockActual = Integer.parseInt(modeloProductos.getValueAt(filaProducto, 2).toString());
        double precio = Double.parseDouble(modeloProductos.getValueAt(filaProducto, 1).toString());

        if (cantidad > stockActual) {
            JOptionPane.showMessageDialog(this, "No hay suficiente stock de " + producto);
            return;
        }

        // Descontar stock
        modeloProductos.setValueAt(stockActual - cantidad, filaProducto, 2);

        // Registrar venta
        double total = precio * cantidad;
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        modeloVentas.addRow(new Object[]{producto, cantidad, total, fecha});

        JOptionPane.showMessageDialog(this, "Venta registrada correctamente");
    }
}
