package Vista;

import Controlador.ControladorVistaCarrito;
import Media.ImagenCarrito;
import Modelo.Carrito;
import Util.Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VistaCarrito extends JFrame {
    private JPanel panelContenedor, panelBoton, pnlTotal,pnlImagen, pnlCentar,pnlTitulo;
    public JButton btnEliminarDelCarrito, btnPagarCarrito, btnRegresar;
    private JLabel total,titulo;
    public JLabel total1;
    public DefaultTableModel modeloCarrito;
    public JTable tablaCarrito;
    private final ImagenCarrito imagenCarrito;
    private final VistaPrincipal vistaPrincipal;
    public double totalVenta = -1;
    public VistaCarrito(ImagenCarrito imagenCarrito, VistaPrincipal vistaPrincipal) {
        this.imagenCarrito = imagenCarrito;
        this.vistaPrincipal = vistaPrincipal;
        crearComponentes();
        initControl();
        configTable();
        configurarVentana();
        dibujarCuerpo();
        add(panelContenedor);
        pack();
        addListeners();
        calcularTotal();
    }
    public void calcularTotal() {
        if (Util.carritoArrayList.isEmpty()) {
            total1.setText("$0");
            return;
        }
        ArrayList<Carrito> carritoArrayList = Util.carritoArrayList;
        double acom = 0;
        for (Carrito carrito : carritoArrayList) {
            int cantidad = carrito.getCantidad();
            double precio = carrito.getProducto().getPrecio();
            double total = cantidad * precio;
            acom += total;
        }
        total1.setText("$" + acom);
        totalVenta = acom;
    }

    private void crearComponentes() {
        btnEliminarDelCarrito = new JButton("Eliminar del carrito");
        btnPagarCarrito = new JButton("Pagar carrito");
        btnRegresar = new JButton("Regresar");

        total = new JLabel("Total: ");
        total1 = new JLabel("");
        titulo = new JLabel("Productos en el carrito");

        modeloCarrito = new DefaultTableModel();
        tablaCarrito = new JTable(modeloCarrito);

        panelContenedor = new JPanel();
        panelBoton = new JPanel();
        pnlImagen = new JPanel();
        pnlTotal = new JPanel();
        pnlCentar = new JPanel();
        pnlTitulo = new JPanel();


    }

    private void configurarVentana() {
        setTitle("Formulario lista");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(700, 720);
        setLocationRelativeTo(null);
    }

    private void dibujarCuerpo() {
        panelBoton.setLayout(new FlowLayout());
        panelBoton.setPreferredSize(new Dimension(650, 50));
        panelBoton.add(btnEliminarDelCarrito);
        panelBoton.add(btnPagarCarrito);
        panelBoton.add(btnRegresar);

        pnlCentar.setLayout(new FlowLayout());
        pnlCentar.add(total);
        pnlCentar.add(total1);

        pnlTotal.setLayout(new BorderLayout());
        pnlTotal.add(pnlCentar, BorderLayout.CENTER);

        pnlTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTitulo.add(titulo);


        panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));

        pnlImagen.setLayout(new GridLayout(1,1));
        imagenCarrito.setPreferredSize(new Dimension(128,128));
        pnlImagen.add(imagenCarrito);

        panelContenedor.add(pnlImagen);
        panelContenedor.add(new JLabel(" "));
        panelContenedor.add(pnlTitulo);
        panelContenedor.add(new JScrollPane(tablaCarrito));
        panelContenedor.add(pnlTotal);
        panelContenedor.add(panelBoton);
    }

    private void configTable() {
        tablaCarrito.setGridColor(new Color(88, 214, 141));
        tablaCarrito.setPreferredScrollableViewportSize(new Dimension(650, 350));
    }
    public void initControl(){
        modeloCarrito.setRowCount(0);
        String[] titulos = {"PRODUCTO","PRECIO","CANTIDAD"};
        modeloCarrito.setColumnIdentifiers(titulos);
        String [] fila = new String[modeloCarrito.getColumnCount()];
        ArrayList<Carrito> lista = Util.carritoArrayList;
        for (Carrito userTable : lista) {
            fila[0] = userTable.getProducto().getNombre();
            fila[1] = String.valueOf(userTable.getProducto().getPrecio());
            fila[2] = String.valueOf(userTable.getCantidad());
            modeloCarrito.addRow(fila);
        }
    }
    private void addListeners() {
        btnRegresar.addActionListener(new ControladorVistaCarrito(this,vistaPrincipal));
        btnEliminarDelCarrito.addActionListener(new ControladorVistaCarrito(this,vistaPrincipal));
        btnPagarCarrito.addActionListener(new ControladorVistaCarrito(this,vistaPrincipal));
    }
}
