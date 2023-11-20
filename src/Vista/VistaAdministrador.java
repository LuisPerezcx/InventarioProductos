package Vista;

import Controlador.ControladorVistaAdministrador;
import Media.MiImagen;
import Modelo.Producto;
import Modelo.Venta;
import Util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;

public class VistaAdministrador extends JFrame{
    private JPanel panelContenedor, panelBoton,pnlImagen,pnlTitulo,pnlVentas,pnlRegresar,pnlArriba,pnlTotal;
    public JButton btnEliminarProducto, btnEditarProducto,btnAgregarProducto, btnRegresar,btnVentas,btnProductos,btnElimnar1, btnEliminarT2;
    public JLabel titulo,total,lblTotal;
    public DefaultTableModel modeloProductos;
    public JTable tablaProductos;
    private final MiImagen imagen;
    private final VistaLogin vistaLogin;

    public VistaAdministrador(MiImagen imagen, VistaLogin vistaLogin) {
        this.imagen = imagen;
        this.vistaLogin = vistaLogin;
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
        btnEliminarProducto = new JButton("Eliminar Producto");
        btnEditarProducto = new JButton("Editar Producto");
        btnAgregarProducto = new JButton("Agregar Producto");
        btnRegresar = new JButton("Regresar");
        btnVentas = new JButton("Ver ventas");
        btnProductos = new JButton("Ver Productos");
        btnElimnar1 = new JButton("Eliminar Venta");
        btnEliminarT2 = new JButton("Eliminar todas las ventas");

        total = new JLabel(" ");
        lblTotal = new JLabel(" ");

        titulo = new JLabel("PRODUCTOS");
        modeloProductos = new DefaultTableModel();
        tablaProductos = new JTable(modeloProductos);

        panelContenedor = new JPanel();
        panelBoton = new JPanel();
        pnlImagen = new JPanel();
        pnlTitulo = new JPanel();
        pnlVentas = new JPanel();
        pnlArriba = new JPanel();
        pnlRegresar = new JPanel();
        pnlTotal = new JPanel();
    }

    private void configurarVentana() {
        setTitle("PANEL ADMINISTRADOR");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(700, 800);
        setLocationRelativeTo(null);
    }

    private void dibujarCuerpo() {
        pnlArriba.setLayout(new GridLayout(1,0));
        pnlRegresar.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnlRegresar.add(btnRegresar);

        panelBoton.setLayout(new FlowLayout());
        //panelBoton.setPreferredSize(new Dimension(650, 50));
        panelBoton.add(btnEliminarT2);
        panelBoton.add(btnElimnar1);
        panelBoton.add(btnEliminarProducto);
        panelBoton.add(btnEditarProducto);
        panelBoton.add(btnAgregarProducto);


        pnlTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitulo.add(titulo);


        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        pnlImagen.setLayout(new GridLayout(1,1));
        imagen.setPreferredSize(new Dimension(128,128));
        pnlImagen.add(imagen);

        pnlVentas.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnlVentas.add(btnProductos);
        pnlVentas.add(btnVentas);

        pnlArriba.add(pnlRegresar);
        pnlArriba.add(pnlVentas);

        panelContenedor.add(pnlArriba);
        panelContenedor.add(pnlImagen);
        panelContenedor.add(new JLabel(" "));
        panelContenedor.add(pnlTitulo);
        panelContenedor.add(new JScrollPane(tablaProductos));

        pnlTotal.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTotal.add(total);
        pnlTotal.add(lblTotal);

        panelContenedor.add(pnlTotal);
        panelContenedor.add(panelBoton);
    }

    private void configTable() {
        tablaProductos.setGridColor(new Color(88, 214, 141));
        tablaProductos.setPreferredScrollableViewportSize(new Dimension(800, 350));
    }
    public void initControl(){
        titulo.setText("PRODUCTOS");
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
            modeloProductos.addRow(fila);
        }
        tablaProductos.setDefaultRenderer(Object.class, new Util.CustomCellRenderer());
    }
    public void initControlVentas(){
        titulo.setText("VENTAS");
        modeloProductos.setRowCount(0);
        String[] titulos = {"NOMBRE PRODUCTO","PRECIO C/U","CANTIDAD","FECHA VENTA","HORA VENTA","TOTAL VENTA"};
        modeloProductos.setColumnIdentifiers(titulos);
        String [] fila = new String[modeloProductos.getColumnCount()];
        ArrayList<Venta> lista = Util.ventaArrayList;
        for (Venta userTable : lista) {
            fila[0] = userTable.getProducto().getNombre();
            fila[1] = String.valueOf(userTable.getProducto().getPrecio());
            fila[2] = String.valueOf(userTable.getCantidad());
            fila[3] = String.valueOf(userTable.getFecha());
            fila[4] = userTable.getHora();
            fila[5] = String.valueOf(userTable.getTotalVenta());
            modeloProductos.addRow(fila);
        }
    }
    private void addListeners() {
        btnAgregarProducto.addActionListener(new ControladorVistaAdministrador(this,vistaLogin));
        btnEditarProducto.addActionListener(new ControladorVistaAdministrador(this,vistaLogin));
        btnEliminarProducto.addActionListener(new ControladorVistaAdministrador(this,vistaLogin));
        btnVentas.addActionListener(new ControladorVistaAdministrador(this,vistaLogin));
        btnProductos.addActionListener(new ControladorVistaAdministrador(this,vistaLogin));
        btnRegresar.addActionListener(new ControladorVistaAdministrador(this,vistaLogin));
        btnElimnar1.addActionListener(new ControladorVistaAdministrador(this,vistaLogin));
        btnEliminarT2.addActionListener(new ControladorVistaAdministrador(this,vistaLogin));
    }
}
