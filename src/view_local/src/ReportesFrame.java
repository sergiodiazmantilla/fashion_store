import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportesFrame extends JFrame {
    private DefaultTableModel modelo;

    // ✅ Constructor vacío (tabla vacía)
    public ReportesFrame() {
        this(new DefaultTableModel(
            new String[]{"   Producto", "   Cantidad", "   Total (S/)", "   Fecha"}, 0
        ));
    }

    // ✅ Constructor con modelo compartido
    public ReportesFrame(DefaultTableModel modeloCompartido) {
        setTitle("Reportes de Ventas");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        this.modelo = modeloCompartido;
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(new Color(6, 90, 130));
        tabla.getTableHeader().setForeground(Color.WHITE);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCerrar = new JButton("Cerrar");
        panelBotones.add(btnCerrar);
        add(panelBotones, BorderLayout.SOUTH);

        btnCerrar.addActionListener(e -> dispose());

        setVisible(true);
    }
}
