package Vista;

import Controlador.ControladorVistaAdministrador;
import Controlador.ControladorVistaAgregarProducto;
import Util.Util;

import javax.swing.*;
import java.awt.*;

public class VistaAgregarProducto extends JFrame {
    private JPanel pnlContenedor,pnlBtns,pnlBtns1;
    private JLabel nombreProducto, codigo, existencia, departamento,precio;
    public JTextField txtNombre, txtCodigo, txtExistencia,txtPrecio;
    public JComboBox<String> departamentoCombo;
    public JButton btnRegistrar,btnRegresar,btnEditar;
    private final ControladorVistaAdministrador controladorVistaAdministrador;
    private final VistaAdministrador vistaAdministrador;
    public VistaAgregarProducto(ControladorVistaAdministrador controladorVistaAdministrador, VistaAdministrador vistaAdministrador){
        this.controladorVistaAdministrador = controladorVistaAdministrador;
        this.vistaAdministrador = vistaAdministrador;
        initComponents();
        initFrame();
        setPnlContenedor();
        add(pnlContenedor);
        addListeners();
        pack();
    }
    private void initFrame(){
        setTitle("inicio");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400,260);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    private void initComponents(){
        pnlContenedor = new JPanel();
        pnlBtns = new JPanel();
        pnlBtns1 = new JPanel();

        codigo = new JLabel("Codigo: ");
        nombreProducto = new JLabel("Nombre Producto: ");
        existencia = new JLabel("Extencia: ");
        departamento = new JLabel("Departamento: ");
        precio = new JLabel("Precio: ");
        txtNombre = new JTextField();
        txtCodigo = new JTextField();
        txtExistencia = new JTextField();
        txtPrecio = new JTextField();

        departamentoCombo = new JComboBox<>(Util.departamentos);
        btnRegistrar = new JButton("Registrar");
        btnRegresar = new JButton("Regresar");
        btnEditar = new JButton("Editar");
    }
    private void setPnlContenedor(){
        pnlContenedor.setLayout(new GridLayout(7,1));
        pnlContenedor.add(codigo);
        pnlContenedor.add(txtCodigo);
        pnlContenedor.add(nombreProducto);
        pnlContenedor.add(txtNombre);
        pnlContenedor.add(precio);
        pnlContenedor.add(txtPrecio);
        pnlContenedor.add(existencia);
        pnlContenedor.add(txtExistencia);
        pnlContenedor.add(departamento);
        pnlContenedor.add(departamentoCombo);
        pnlContenedor.add(new JLabel());
        pnlContenedor.add(new JLabel());

        pnlBtns.setLayout(new FlowLayout());
        pnlBtns.add(btnEditar);
        pnlBtns.add(btnRegistrar);

        pnlBtns1.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlBtns1.add(btnRegresar);

        pnlContenedor.add(pnlBtns1);
        pnlContenedor.add(pnlBtns);
    }
    public void addListeners(){
        ControladorVistaAgregarProducto controlador = new ControladorVistaAgregarProducto(this,vistaAdministrador,controladorVistaAdministrador);
        btnRegresar.addActionListener(controlador);
        btnRegistrar.addActionListener(controlador);
        btnEditar.addActionListener(controlador);
    }
    public void clear(){
        txtNombre.setText("");
        txtCodigo.setText("");
        txtExistencia.setText("");
        txtPrecio.setText("");
        departamentoCombo.setSelectedIndex(0);
    }
}
