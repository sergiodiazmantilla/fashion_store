import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClientesFrame extends JFrame {
    private JTextField txtNombre = new JTextField();
    private JTextField txtDni = new JTextField();
    private JTextField txtDireccion = new JTextField();
    private DefaultTableModel modelo;
    private JTable tabla;

    public ClientesFrame() {
        setTitle("Gestión de Clientes - Fashion Store");
        setSize(650, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 🎨 Colores y estilo
        Color fondo = new Color(245, 245, 250);
        Color encabezado = new Color(6, 90, 130);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 18);

        // Título
        JLabel lblTitulo = new JLabel("Registro de Clientes", SwingConstants.CENTER);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(encabezado);
        add(lblTitulo, BorderLayout.NORTH);

        // Formulario arriba
        JPanel formulario = new JPanel(new GridLayout(3, 2, 5, 5));
        formulario.setBackground(fondo);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("DNI:"));
        formulario.add(txtDni);
        formulario.add(new JLabel("Dirección:"));
        formulario.add(txtDireccion);
        add(formulario, BorderLayout.NORTH);

        // Tabla abajo
        String[] columnas = {"DNI", "Nombre", "Dirección"};
        modelo = new DefaultTableModel(columnas, 0);
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
        btnGuardar.addActionListener(e -> guardarCliente());
        btnEditar.addActionListener(e -> editarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnCancelar.addActionListener(e -> limpiarCampos());

        // Al hacer clic en la tabla, cargar datos en los campos
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    txtDni.setText(modelo.getValueAt(fila, 0).toString());
                    txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                    txtDireccion.setText(modelo.getValueAt(fila, 2).toString());
                }
            }
        });

        setVisible(true);
    }

    // Guardar nuevo cliente
    private void guardarCliente() {
        String dni = txtDni.getText().trim();
        String nombre = txtNombre.getText().trim();
        String direccion = txtDireccion.getText().trim();

        if (!dni.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El DNI debe contener solo números");
            return;
        }
        if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            JOptionPane.showMessageDialog(this, "El nombre debe contener solo letras");
            return;
        }
        if (!direccion.isEmpty() && !direccion.matches("(Av\\.|Calle|Jirón)\\s.+\\s\\d+")) {
            JOptionPane.showMessageDialog(this, "Formato sugerido: Av. Siempre Viva 123");
        }

        modelo.addRow(new Object[]{dni, nombre, direccion});
        JOptionPane.showMessageDialog(this, "Cliente agregado correctamente");
        limpiarCampos();
    }

    // Editar cliente seleccionado
    private void editarCliente() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            modelo.setValueAt(txtDni.getText(), fila, 0);
            modelo.setValueAt(txtNombre.getText(), fila, 1);
            modelo.setValueAt(txtDireccion.getText(), fila, 2);
            JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para editar");
        }
    }

    // Eliminar cliente seleccionado
    private void eliminarCliente() {
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            modelo.removeRow(fila);
            JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para eliminar");
        }
    }

    // Limpiar campos
    private void limpiarCampos() {
        txtDni.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
    }
}
