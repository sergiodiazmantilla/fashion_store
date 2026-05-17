import javax.swing.*;                // Para JFrame, JPanel, JLabel, JTextField, JTable, JScrollPane, JButton, JOptionPane
import javax.swing.table.DefaultTableModel; // Para la tabla con modelo
import java.awt.*;                   // Para BorderLayout, GridLayout, FlowLayout, Color, Font



public class ProductosFrame extends JFrame {
    private JTextField txtNombre = new JTextField();
    private JTextField txtPrecio = new JTextField();
    private JTextField txtStock = new JTextField();
    private DefaultTableModel modelo;
    private JTable tabla;

    // ✅ Constructor recibe el modelo compartido
    public ProductosFrame(DefaultTableModel modeloCompartido) {
        setTitle("Gestión de Productos - Fashion Store");
        setSize(650, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        this.modelo = modeloCompartido; // usar el modelo compartido

        // 🎨 Colores y estilo
        Color fondo = new Color(245, 245, 250);
        Color encabezado = new Color(6, 90, 130);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 18);

        JLabel lblTitulo = new JLabel("Registro de Productos", SwingConstants.CENTER);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(encabezado);
        add(lblTitulo, BorderLayout.NORTH);

        // Formulario arriba
        JPanel formulario = new JPanel(new GridLayout(3, 2, 5, 5));
        formulario.setBackground(fondo);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Precio (S/):"));
        formulario.add(txtPrecio);
        formulario.add(new JLabel("Stock:"));
        formulario.add(txtStock);
        add(formulario, BorderLayout.NORTH);

        // Tabla abajo (usa el modelo compartido)
        tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(encabezado);
        tabla.getTableHeader().setForeground(Color.WHITE);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnGuardar = new JButton("Guardar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnCancelar);
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        btnGuardar.addActionListener(e -> guardarProducto());
        btnEditar.addActionListener(e -> editarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnCancelar.addActionListener(e -> limpiarCampos());

        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    txtNombre.setText(modelo.getValueAt(fila, 0).toString());
                    txtPrecio.setText(modelo.getValueAt(fila, 1).toString());
                    txtStock.setText(modelo.getValueAt(fila, 2).toString());
                }
            }
        });

        setVisible(true);
    }

    // Métodos CRUD (igual que antes)...
    private void guardarProducto() {
        String nombre = txtNombre.getText().trim();
        String precio = txtPrecio.getText().trim();
        String stock = txtStock.getText().trim();

        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            JOptionPane.showMessageDialog(this, "El nombre debe contener solo letras");
            return;
        }
        if (!precio.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido");
            return;
        }
        if (!stock.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El stock debe ser un número entero");
            return;
        }

        modelo.addRow(new Object[]{nombre, precio, stock});
        JOptionPane.showMessageDialog(this, "Producto agregado correctamente");
        limpiarCampos();
    }

    private void editarProducto() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            modelo.setValueAt(txtNombre.getText(), fila, 0);
            modelo.setValueAt(txtPrecio.getText(), fila, 1);
            modelo.setValueAt(txtStock.getText(), fila, 2);
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para editar");
        }
    }

    private void eliminarProducto() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            modelo.removeRow(fila);
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar");
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
    }
}
