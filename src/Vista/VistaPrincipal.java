package Vista;

import Controlador.ControladorVistaPrincipal;
import Media.ImagenCarrito;
import Modelo.Producto;
import Util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VistaPrincipal extends JFrame {
    private JPanel panelContenedor, panelBoton, pnlDetalles, pnlLogin, pnlImagen, pnlFiltros,pnlTitulos,pnlFiltrosYBuscar,pnlBuscar;
    public JButton btnVerCarrito, btnAgregarCarrito, btnLogin, btnBuscar,btnQuitar;
    private JLabel titulo, cantidadlbl;
    public DefaultTableModel modeloProductos;
    public JTable tablaProductos;
    private final ImagenCarrito imagenCarrito;
    public JTextField txtCantidad,txtBuscar;
    public JComboBox<String> comboFiltros;

    public VistaPrincipal(ImagenCarrito imagenCarrito) {
        this.imagenCarrito = imagenCarrito;
        crearComponentes();
        initControl();
        configTable();
        configurarVentana();
        dibujarCuerpo();
        add(panelContenedor);
        pack();
        addListeners();
    }

    private void crearComponentes() {
        btnLogin = new JButton("Login");
        btnVerCarrito = new JButton("Ver carrito");
        btnAgregarCarrito = new JButton("Agregar al carrito");
        titulo = new JLabel("Ordenar por");
        cantidadlbl = new JLabel("Cantidad de productos: ");
        btnBuscar = new JButton("Buscar");
        btnQuitar = new JButton("Quitar filtros");


        modeloProductos = new DefaultTableModel();
        tablaProductos = new JTable(modeloProductos);

        panelContenedor = new JPanel();
        panelBoton = new JPanel();
        pnlLogin = new JPanel();
        pnlImagen = new JPanel();
        pnlDetalles = new JPanel();
        pnlFiltros = new JPanel();
        pnlTitulos = new JPanel();

        pnlFiltrosYBuscar = new JPanel();
        pnlBuscar = new JPanel();

        txtCantidad = new JTextField();
        txtBuscar = new JTextField();

        String[] filtros = {" ","NOMBRE","PRECIO MENOR-MAYOR","PRECIO MAYOR-MENOR","CATEGORIA"};
        comboFiltros = new JComboBox<>(filtros);
    }

    private void configurarVentana() {
        setTitle("PRODUCTOS");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(800, 720);
        setLocationRelativeTo(null);
    }

    private void dibujarCuerpo() {
        panelBoton.setLayout(new FlowLayout());
        panelBoton.setPreferredSize(new Dimension(650, 50));
        panelBoton.add(cantidadlbl);
        txtCantidad.setPreferredSize(new Dimension(100, 20));
        panelBoton.add(txtCantidad);
        panelBoton.add(btnAgregarCarrito);

        pnlDetalles.setLayout(new GridLayout(3, 4));

        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        pnlImagen.setLayout(new GridLayout(1, 1));
        imagenCarrito.setPreferredSize(new Dimension(128, 128));
        pnlImagen.add(imagenCarrito);

        pnlLogin.setLayout(new BorderLayout());
        pnlLogin.add(btnVerCarrito, BorderLayout.WEST);
        pnlLogin.add(btnLogin, BorderLayout.EAST);


        pnlFiltros.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlFiltros.add(titulo);
        pnlFiltros.add(comboFiltros);

        pnlBuscar.setLayout(new FlowLayout(FlowLayout.LEFT));
        txtBuscar.setPreferredSize(new Dimension(110,24));
        pnlBuscar.add(txtBuscar);
        pnlBuscar.add(btnBuscar);
        pnlBuscar.add(btnQuitar);


        pnlFiltrosYBuscar.setLayout(new GridLayout(1,0));
        pnlFiltrosYBuscar.add(pnlBuscar);
        pnlFiltrosYBuscar.add(pnlFiltros);


        panelContenedor.add(pnlLogin);
        panelContenedor.add(pnlImagen);

        pnlTitulos.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitulos.add(new JLabel("PRODUCTOS"));

        panelContenedor.add(pnlTitulos);
        panelContenedor.add(pnlFiltrosYBuscar);
        panelContenedor.add(new JScrollPane(tablaProductos));
        panelContenedor.add(panelBoton);
        panelContenedor.add(pnlDetalles);
    }

    private void configTable() {
        tablaProductos.setGridColor(new Color(88, 214, 141));
        tablaProductos.setPreferredScrollableViewportSize(new Dimension(800, 350));
    }
    public void initControl(){
        modeloProductos.setRowCount(0);
        modeloProductos.setColumnIdentifiers(Util.titulosTabla);
        String [] fila = new String[modeloProductos.getColumnCount()];
        ArrayList<Producto> lista = Util.productoArrayList;
        for (Producto userTable : lista) {
            fila[0] = String.valueOf(userTable.getCodigo());
            fila[1] = userTable.getNombre();
            fila[2] = String.valueOf(userTable.getCantidad());
            fila[3] = String.valueOf(userTable.getPrecio());
            fila[4] = userTable.getDepartamento();
            if (!fila[2].equals("0")) {
               modeloProductos.addRow(fila);
            }
        }
    }
    public void initControlOrdenado(ArrayList<Producto> array){
        modeloProductos.setRowCount(0);
        modeloProductos.setColumnIdentifiers(Util.titulosTabla);
        String [] fila = new String[modeloProductos.getColumnCount()];
        for (Producto userTable : array) {
            fila[0] = String.valueOf(userTable.getCodigo());
            fila[1] = userTable.getNombre();
            fila[2] = String.valueOf(userTable.getCantidad());
            fila[3] = String.valueOf(userTable.getPrecio());
            fila[4] = userTable.getDepartamento();
            if (!fila[2].equals("0")) {
                modeloProductos.addRow(fila);
            }
        }
    }
    private void addListeners(){
        btnLogin.addActionListener(new ControladorVistaPrincipal(this));
        btnVerCarrito.addActionListener(new ControladorVistaPrincipal(this));
        btnAgregarCarrito.addActionListener(new ControladorVistaPrincipal(this));
        btnBuscar.addActionListener(new ControladorVistaPrincipal(this));
        btnQuitar.addActionListener(new ControladorVistaPrincipal(this));
    }
}
