package view;

import dao.ProductoDAO;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Calzado;
import model.Camisa;
import model.Pantalon;
import model.Polo;
import model.Producto;

public class ProductosFrame extends JFrame {
    private JTextField txtNombre = new JTextField();
    private JTextField txtPrecio = new JTextField();
    private JTextField txtStock = new JTextField();
    private JComboBox<String> cbTipo = new JComboBox<>();
    private JTextField txtTalla = new JTextField();
    private JTextField txtColor = new JTextField();
    private JTextField txtAtributo = new JTextField();
    
    private DefaultTableModel modelo;
    private JTable tabla;
    
    private int idSeleccionado = -1;

    @SuppressWarnings("FieldMayBeFinal")
    private ProductoDAO productoDAO = new ProductoDAO();
    
    // Constructor recibe el modelo compartido
    public ProductosFrame() {
        setTitle("Gestión de Productos - Fashion Store");
        setSize(650, 450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 🎨 Colores y estilo
        Color fondo = new Color(245, 245, 250);
        Color encabezado = new Color(6, 90, 130);
        Font fuenteTitulo = new Font("Arial", Font.BOLD, 18);

        JLabel lblTitulo = new JLabel("Registro de Productos", SwingConstants.CENTER);
        lblTitulo.setFont(fuenteTitulo);
        lblTitulo.setForeground(encabezado);
        add(lblTitulo, BorderLayout.NORTH);

        // Formulario arriba
        JPanel formulario = new JPanel(new GridLayout(7, 2, 5, 5));
        formulario.setBackground(fondo);
        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);
        formulario.add(new JLabel("Tipo:"));
        cbTipo = new JComboBox<>();
        cbTipo.addItem("Polo");
        cbTipo.addItem("Camisa");
        cbTipo.addItem("Pantalon");
        cbTipo.addItem("Calzado");
        formulario.add(cbTipo);
        formulario.add(new JLabel("Precio (S/):"));
        formulario.add(txtPrecio);
        formulario.add(new JLabel("Stock:"));
        formulario.add(txtStock);
        formulario.add(new JLabel("Talla:"));
        formulario.add(txtTalla);
        formulario.add(new JLabel("Color:"));
        formulario.add(txtColor);
        formulario.add(new JLabel("Caracteristica:"));
        formulario.add(txtAtributo);
        add(formulario, BorderLayout.NORTH);

        modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        modelo.addColumn("Talla");
        modelo.addColumn("Color");
        modelo.addColumn("Atributo");
        
        // Tabla abajo (usa el modelo compartido)
        tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(encabezado);
        tabla.getTableHeader().setForeground(Color.WHITE);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        tabla.getColumnModel().getColumn(0).setPreferredWidth(40);   // ID
        tabla.getColumnModel().getColumn(1).setPreferredWidth(180);  // Nombre
        tabla.getColumnModel().getColumn(2).setPreferredWidth(100);  // Tipo
        tabla.getColumnModel().getColumn(3).setPreferredWidth(80);   // Precio
        tabla.getColumnModel().getColumn(4).setPreferredWidth(60);   // Stock
        tabla.getColumnModel().getColumn(5).setPreferredWidth(60);   // Talla
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);  // Color
        tabla.getColumnModel().getColumn(7).setPreferredWidth(150);  // Atributo
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
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tabla.getSelectedRow();
                if (fila >= 0) {
                    idSeleccionado = Integer.parseInt(
                            modelo.getValueAt(fila, 0).toString()
                    );
                    txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                    cbTipo.setSelectedItem(modelo.getValueAt(fila, 2).toString());
                    txtPrecio.setText(modelo.getValueAt(fila, 3).toString());
                    txtStock.setText(modelo.getValueAt(fila, 4).toString());
                    txtTalla.setText(modelo.getValueAt(fila, 5).toString());
                    txtColor.setText(modelo.getValueAt(fila, 6).toString());
                    txtAtributo.setText(modelo.getValueAt(fila, 7).toString());
                }
            }
        });

        listarProductos();
        setVisible(true);
    }

    // Métodos CRUD (igual que antes)...
    @SuppressWarnings("UseSpecificCatch")
    private void guardarProducto() {

        try {

            String nombre =txtNombre.getText().trim();
            double precio =Double.parseDouble(txtPrecio.getText());
            int stock =Integer.parseInt(txtStock.getText());
            String tipo = cbTipo.getSelectedItem().toString();
            String talla = txtTalla.getText().trim();
            String color = txtColor.getText().trim();
            String atributo = txtAtributo.getText().trim();
            
            // VALIDAR CAMPOS
            if (nombre.isEmpty()
                    || txtPrecio.getText().isEmpty()
                    || txtStock.getText().isEmpty()
                    || talla.isEmpty()
                    || color.isEmpty()
                    || atributo.isEmpty()) {

                JOptionPane.showMessageDialog(this,"Complete todos los campos");
                return;
            }
            // CREAR PRODUCTO SEGUN TIPO
            Producto producto;

            switch (tipo) {

                case "Camisa" -> producto =
                        new Camisa();

                case "Pantalon" -> producto =
                        new Pantalon();

                case "Calzado" -> producto =
                        new Calzado();

                default -> producto =
                        new Polo();
            }

            // DATOS

            producto.setNombre(nombre);
            producto.setTipo(tipo);
            producto.setPrecio(precio);
            producto.setStock(stock);
            producto.setStockMinimo(5);
            producto.setTalla(talla);
            producto.setColor(color);
            producto.setAtributo(atributo);

            // GUARDAR
            productoDAO.registrar(producto);

            JOptionPane.showMessageDialog(this,"Producto agregado correctamente");

            listarProductos();
            limpiarCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("UseSpecificCatch")
    private void editarProducto() {

        if (idSeleccionado == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un producto"
            );

            return;
        }

        try {

            Producto producto;

            String tipo =
                    cbTipo.getSelectedItem().toString();

            switch (tipo) {

                case "Camisa" -> producto = new Camisa();

                case "Pantalon" -> producto = new Pantalon();

                case "Calzado" -> producto = new Calzado();

                default -> producto = new Polo();
            }

            producto.setId(idSeleccionado);

            producto.setNombre(txtNombre.getText());

            producto.setTipo(tipo);

            producto.setPrecio(
                    Double.parseDouble(txtPrecio.getText())
            );

            producto.setStock(
                    Integer.parseInt(txtStock.getText())
            );
            producto.setTalla(txtTalla.getText());
            producto.setColor(txtColor.getText());
            producto.setAtributo(txtAtributo.getText());

            productoDAO.actualizar(producto);

            JOptionPane.showMessageDialog(
                    this,
                    "Producto actualizado"
            );

            listarProductos();
            limpiarCampos();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error: " + e.getMessage()
            );
        }
    }

    private void eliminarProducto() {

        if (idSeleccionado == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un producto"
            );

            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar producto?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {

            productoDAO.eliminar(idSeleccionado);

            JOptionPane.showMessageDialog(this,"Producto eliminado");
            
            listarProductos();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtColor.setText("");
        txtTalla.setText("");
        txtAtributo.setText("");

        idSeleccionado = -1;

    }

    private void listarProductos() {

        modelo.setRowCount(0);

        List<Producto> lista = productoDAO.listar();

        for (Producto p : lista) {

            modelo.addRow(new Object[]{
                p.getId(),
                p.getNombre(),
                p.getTipo(),
                p.getPrecio(),
                p.getStock(),
                p.getTalla(),
                p.getColor(),
                p.getAtributo()
            });
        }
    }
}
