package Vista;

import Controlador.ControladorLogin;
import Media.MiImagen;
import Util.Util;

import javax.swing.*;
import java.awt.*;

public class VistaLogin extends JFrame{
    private JPanel panelContenedor, panelLogin,pnlArriba,pnlContenedorPrincipal,pnlTitulo;
    private final MiImagen imagen;
    public JButton btnRegresar, btnAceptar, btnRegistrarse;
    private JLabel usuario,contrasena;
    public JLabel titulo;
    public JTextField txtUsuario;
    public JPasswordField txtContrasena;
    private final VistaPrincipal vistaPrincipal;
    public  VistaLogin(MiImagen imagen, VistaPrincipal vistaPrincipal){
        this.imagen = imagen;
        this.vistaPrincipal = vistaPrincipal;
        initComponents();
        registrado();
        initFrame();
        setPanelContainer();
        addListeners();
        add(pnlContenedorPrincipal);
    }
    private void initComponents(){
        panelContenedor = new JPanel();
        btnRegresar = new JButton("Regresar");
        btnAceptar = new JButton("Aceptar");
        panelLogin = new JPanel();
        pnlArriba = new JPanel();
        pnlContenedorPrincipal = new JPanel();
        pnlTitulo = new JPanel();

        btnRegistrarse = new JButton("Registrarse");

        titulo = new JLabel("Iniciar Sesion");
        usuario = new JLabel("Usuario: ");
        contrasena = new JLabel("Contrasena: ");
        txtUsuario = new JTextField();
        txtContrasena = new JPasswordField();
    }
    private void initFrame(){
        setTitle("LOGIN ADMIN");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(380,420);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    private void setPanelContainer(){
        pnlContenedorPrincipal.setLayout(new BoxLayout(pnlContenedorPrincipal,BoxLayout.Y_AXIS));
        pnlArriba.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlArriba.add(btnRegistrarse);
        pnlTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitulo.add(titulo);

        panelContenedor.setLayout(new GridLayout(2,1));
        panelContenedor.setPreferredSize(new Dimension(300,300));
        panelContenedor.add(imagen);

        panelLogin.setLayout(new GridLayout(4,2));
        panelLogin.add(usuario);
        panelLogin.add(txtUsuario);
        panelLogin.add(contrasena);
        panelLogin.add(txtContrasena);
        panelLogin.add(new JLabel());
        panelLogin.add(new JLabel());
        panelLogin.add(btnRegresar);
        panelLogin.add(btnAceptar);
        panelContenedor.add(panelLogin);

        pnlContenedorPrincipal.add(pnlArriba);
        pnlContenedorPrincipal.add(pnlTitulo);
        pnlContenedorPrincipal.add(panelContenedor);
    }
    private void addListeners(){
        btnRegresar.addActionListener(new ControladorLogin(this,vistaPrincipal));
        btnAceptar.addActionListener(new ControladorLogin(this,vistaPrincipal));
        btnRegistrarse.addActionListener(new ControladorLogin(this,vistaPrincipal));
    }
    private void registrado(){
        if (Util.usuariosArrayList.isEmpty()){
            titulo.setText("PARA EMPEZAR REGISTRE UN USUARIO");
            btnRegistrarse.setVisible(false);
        }
    }
}
