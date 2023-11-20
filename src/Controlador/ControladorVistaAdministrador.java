package Controlador;

import Media.ImagenCarrito;
import Modelo.Validaciones;
import Modelo.Venta;
import Util.Util;
import Vista.VistaAdministrador;
import Vista.VistaAgregarProducto;
import Vista.VistaLogin;
import Vista.VistaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControladorVistaAdministrador extends CRUD implements ActionListener{
    private final VistaAdministrador vistaAdministrador;
    private final VistaLogin vistaLogin;
    public int indexT = -1;

    public ControladorVistaAdministrador(VistaAdministrador vistaAdministrador, VistaLogin vistaLogin) {
        this.vistaAdministrador = vistaAdministrador;
        this.vistaLogin = vistaLogin;
        listenerTabla();
        habilitarBtns(true,false);
    }
    private void listenerTabla(){
        vistaAdministrador.tablaProductos.getSelectionModel().addListSelectionListener(e -> {
            indexT = vistaAdministrador.tablaProductos.getSelectedRow();
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if(event == vistaAdministrador.btnRegresar){
            vistaAdministrador.dispose();
            vistaLogin.dispose();
            new VistaPrincipal(new ImagenCarrito(330,0));
        }else if(event == vistaAdministrador.btnAgregarProducto){
            VistaAgregarProducto vistaAgregarProducto = new VistaAgregarProducto(this,vistaAdministrador);
            vistaAgregarProducto.btnEditar.setEnabled(false);
        }else if(event == vistaAdministrador.btnEditarProducto){
            if(Validaciones.validarIndexTabla(indexT)){
                VistaAgregarProducto vistaAgregarProducto = new VistaAgregarProducto(this,vistaAdministrador);
                vistaAgregarProducto.btnRegistrar.setEnabled(false);
                showInfo(vistaAgregarProducto);
            }
        } else if (event == vistaAdministrador.btnEliminarProducto) {
            eliminarProductoVenta(Util.productoArrayList);
            vistaAdministrador.initControl();
        } else if (event == vistaAdministrador.btnVentas) {
            vistaAdministrador.initControlVentas();
            vistaAdministrador.total.setText("Ventas totales: ");
            vistaAdministrador.lblTotal.setText(String.valueOf(totalVentas()));
            habilitarBtns(false,true);
        } else if (event == vistaAdministrador.btnProductos) {
            vistaAdministrador.lblTotal.setText("");
            vistaAdministrador.total.setText("");
            vistaAdministrador.initControl();
            habilitarBtns(true,false);
        } else if (event == vistaAdministrador.btnElimnar1) {
            eliminarProductoVenta(Util.ventaArrayList);
            vistaAdministrador.initControlVentas();
        } else if (event == vistaAdministrador.btnEliminarT2) {
            if (Util.ventaArrayList.isEmpty()){
                JOptionPane.showMessageDialog(vistaAdministrador, "NO HAY VENTAS QUE ELIMINAR");
                return;
            }
            eliminarTodo(Util.ventaArrayList);
            vistaAdministrador.initControlVentas();
            JOptionPane.showMessageDialog(vistaAdministrador, "VENTAS ELIMINADAS");
        }
    }
    private void habilitarBtns(boolean x, boolean y){
        vistaAdministrador.btnEliminarProducto.setEnabled(x);
        vistaAdministrador.btnEditarProducto.setEnabled(x);
        vistaAdministrador.btnAgregarProducto.setEnabled(x);
        vistaAdministrador.btnProductos.setEnabled(y);

        vistaAdministrador.btnElimnar1.setEnabled(y);
        vistaAdministrador.btnEliminarT2.setEnabled(y);
        vistaAdministrador.btnVentas.setEnabled(x);
    }
    private void eliminarProductoVenta(ArrayList<?> array){
        if (Validaciones.validarIndexTabla(indexT)) {
            eliminar(array, indexT);
            JOptionPane.showMessageDialog(vistaAdministrador, "ELIMINADO");
        }
    }
    private double totalVentas(){
        double total = 0;
        for(Venta venta : Util.ventaArrayList){
            total += venta.getTotalVenta();
        }
        return total;
    }
    private void showInfo(VistaAgregarProducto vistaAgregarProducto){
        int i = indexT;
        vistaAgregarProducto.txtCodigo.setText(String.valueOf(Util.productoArrayList.get(i).getCodigo()));
        vistaAgregarProducto.txtNombre.setText(Util.productoArrayList.get(i).getNombre());
        vistaAgregarProducto.txtPrecio.setText(String.valueOf(Util.productoArrayList.get(i).getPrecio()));
        vistaAgregarProducto.txtExistencia.setText(String.valueOf(Util.productoArrayList.get(i).getCantidad()));
        vistaAgregarProducto.departamentoCombo.setSelectedItem(Util.productoArrayList.get(i).getDepartamento());
    }
}
