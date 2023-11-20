package Controlador;

import Media.ImagenCarrito;
import Media.MiImagen;
import Modelo.Carrito;
import Modelo.Producto;
import Modelo.Validaciones;
import Util.Util;
import Vista.VistaCarrito;
import Vista.VistaLogin;
import Vista.VistaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaPrincipal extends CRUD implements ActionListener {
    private final VistaPrincipal vistaPrincipal;
    public int indexT = -1;
    private int indexC = -1;

    public ControladorVistaPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        listenrTabla();
        listenerCombo();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == vistaPrincipal.btnLogin){
            vistaPrincipal.dispose();
            new VistaLogin(new MiImagen(100,0),vistaPrincipal);
        }else if (event == vistaPrincipal.btnVerCarrito){
            vistaPrincipal.setVisible(false);
            new VistaCarrito(new ImagenCarrito(250,0),vistaPrincipal);
        } else if (event == vistaPrincipal.btnAgregarCarrito) {
            if(Validaciones.validarIndexTabla(indexT)){
                agregarCarrito();
            }
        } else if (event == vistaPrincipal.btnBuscar) {
            Producto producto =  buscar(vistaPrincipal.txtBuscar.getText());
            vistaPrincipal.comboFiltros.setSelectedIndex(0);
            if (producto != null){
                mostrarBusqueda(producto);
            }else {
                JOptionPane.showMessageDialog(vistaPrincipal,"Producto no encontrado");
                vistaPrincipal.txtBuscar.setText("");
            }
        } else if (event == vistaPrincipal.btnQuitar){
            vistaPrincipal.initControl();
            vistaPrincipal.txtBuscar.setText("");
            vistaPrincipal.comboFiltros.setSelectedIndex(0);
        }
    }
    private void agregarCarrito(){
        int cantidad = Validaciones.validarNumeros(vistaPrincipal.txtCantidad.getText());
        int cantidadActual = Util.productoArrayList.get(indexT).getCantidad();
        Producto producto = Util.productoArrayList.get(indexT);
        if(Validaciones.validarCantidad(cantidadActual,cantidad)){
            agregar(Util.carritoArrayList,new Carrito(producto,cantidad,indexT));
            vistaPrincipal.txtCantidad.setText("");
            JOptionPane.showMessageDialog(vistaPrincipal,"AGREGADO AL CARRITO");
            Util.productoArrayList.get(indexT).setCantidad((cantidadActual-cantidad));
            vistaPrincipal.initControl();
        }
    }
    private Producto buscar(String nombre){
        for (Producto producto : Util.productoArrayList){
            String nombreActual = producto.getNombre();
            if (nombreActual.equals(nombre)){
                return producto;
            }
        }
        return null;
    }
    private void mostrarBusqueda(Producto producto){
        vistaPrincipal.modeloProductos.setRowCount(0);
        vistaPrincipal.modeloProductos.setColumnIdentifiers(Util.titulosTabla);
        String [] fila = new String[vistaPrincipal.modeloProductos.getColumnCount()];
        fila[0] = String.valueOf(producto.getCodigo());
        fila[1] = producto.getNombre();
        fila[2] = String.valueOf(producto.getCantidad());
        fila[3] = String.valueOf(producto.getPrecio());
        fila[4] = producto.getDepartamento();
        vistaPrincipal.modeloProductos.addRow(fila);
    }

    private void listenrTabla(){
        vistaPrincipal.tablaProductos.getSelectionModel().addListSelectionListener( e -> {
            indexT = vistaPrincipal.tablaProductos.getSelectedRow();
        });
    }
    private void listenerCombo(){
        vistaPrincipal.comboFiltros.addActionListener(e -> {
            vistaPrincipal.txtBuscar.setText("");
            indexC = vistaPrincipal.comboFiltros.getSelectedIndex();
            switch (indexC){
                case 1 -> vistaPrincipal.initControlOrdenado(Producto.ordenarPorNombre());
                case 2 -> vistaPrincipal.initControlOrdenado(Producto.ordenarPorPrecioMenorAMayor());
                case 3 -> vistaPrincipal.initControlOrdenado(Producto.ordenarPorPrecioMayorAMenor());
                case 4 -> vistaPrincipal.initControlOrdenado(Producto.ordenarPorDepartamento());
            }
        });
    }
}
