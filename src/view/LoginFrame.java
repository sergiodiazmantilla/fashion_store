package view;

import dao.UsuarioDAO;
import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {

    // COMPONENTES
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    // DAO
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    @SuppressWarnings("unused")
    private MainMenuFrame mainMenuFrame;

    // CONSTRUCTOR
    public LoginFrame() {

        setTitle("Fashion Store - Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        inicializarComponentes();
        setVisible(true);
    }

    // COMPONENTES
    private void inicializarComponentes() {

        JLabel lblTitulo =new JLabel("FASHION STORE");
        lblTitulo.setFont(new Font("Arial",Font.BOLD,22));
        lblTitulo.setBounds(90,20,250,30);
        add(lblTitulo);

        // USUARIO
        JLabel lblUsuario =new JLabel("Usuario:");
        lblUsuario.setBounds(50,80,100,25);
        add(lblUsuario);

        txtUsuario =new JTextField();
        txtUsuario.setBounds(150,80,180,25);
        add(txtUsuario);

        // PASSWORD
        JLabel lblPassword =new JLabel("Password:");
        lblPassword.setBounds(50,120,100,25);
        add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150,120,180,25);
        add(txtPassword);

        // BOTON
        btnLogin =new JButton("Ingresar");
        btnLogin.setBounds(130,170,120,30);
        add(btnLogin);

        //BOTON CREDENCIALES
        JButton btnInfo = new JButton("ⓘ");
        btnInfo.setBounds(260,170,50,30);
        btnInfo.setToolTipText("Mostrar credenciales");
        add(btnInfo);
        btnInfo.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                this, 
                    """
                    Credenciales de acceso:
                    Usuario: admin
                    Password: 1234""",

                    "Información",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        // EVENTO LOGIN
        btnLogin.addActionListener(e -> login());
    }

    // LOGIN
    private void login() {

        String usuario =txtUsuario.getText();
        String password = new String(txtPassword.getPassword());
        boolean acceso = usuarioDAO.login(usuario,password);

        if (acceso) {
            JOptionPane.showMessageDialog(this,"Bienvenido al sistema");

            // ABRIR MENU
            mainMenuFrame = new MainMenuFrame();
            dispose();

        } else {
            JOptionPane.showMessageDialog(this,"Usuario o contraseña incorrectos");
        }
    }
}