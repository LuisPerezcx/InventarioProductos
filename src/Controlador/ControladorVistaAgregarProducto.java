package Controlador;

import Modelo.Producto;
import Modelo.Validaciones;
import Util.Util;
import Vista.VistaAdministrador;
import Vista.VistaAgregarProducto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaAgregarProducto extends CRUD implements ActionListener {
    private final VistaAgregarProducto vistaAgregarProducto;
    private final VistaAdministrador vistaAdministrador;
    private final ControladorVistaAdministrador controladorVistaAdministrador;

    public ControladorVistaAgregarProducto(VistaAgregarProducto vistaAgregarProducto, VistaAdministrador vistaAdministrador, ControladorVistaAdministrador controladorVistaAdministrador) {
        this.vistaAgregarProducto = vistaAgregarProducto;
        this.vistaAdministrador = vistaAdministrador;
        this.controladorVistaAdministrador = controladorVistaAdministrador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if(event == vistaAgregarProducto.btnRegresar){
            vistaAgregarProducto.dispose();
            vistaAdministrador.initControl();
        } else if (event == vistaAgregarProducto.btnRegistrar) {
             Producto producto = nuevoProducto();
             if (producto==null) return;
             agregar(Util.productoArrayList,producto);
             vistaAdministrador.initControl();
             vistaAgregarProducto.clear();
            //JOptionPane.showMessageDialog(vistaAdministrador,"Agregado");
        } else if (event == vistaAgregarProducto.btnEditar) {
            int index = controladorVistaAdministrador.indexT;
            Producto producto = nuevoProducto();
            if (producto == null) return;
            editar(Util.productoArrayList,index,producto);
            vistaAdministrador.initControl();
            vistaAgregarProducto.dispose();
            JOptionPane.showMessageDialog(vistaAdministrador,"Editado");
        }
    }
    private Producto nuevoProducto(){
        if(validarCamposCompletos()){
            int codigo = Validaciones.validarNumeros(vistaAgregarProducto.txtCodigo.getText());
            String nombre = vistaAgregarProducto.txtNombre.getText();
            double precio = Validaciones.validarDouble(vistaAgregarProducto.txtPrecio.getText());
            int existencias = Validaciones.validarNumeros(vistaAgregarProducto.txtExistencia.getText());
            String departamento = String.valueOf(vistaAgregarProducto.departamentoCombo.getSelectedItem());
            if (codigo==-1 || precio == -1 || existencias ==-1){
                vistaAgregarProducto.clear();
                return null;
            }else return  new Producto(codigo,nombre,precio,existencias,departamento);
        }else return null;
    }
    private boolean validarCamposCompletos(){
        if (vistaAgregarProducto.txtCodigo.getText().isEmpty()||vistaAgregarProducto.txtNombre.getText().isEmpty()||
        vistaAgregarProducto.txtPrecio.getText().isEmpty()||vistaAgregarProducto.txtExistencia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(vistaAdministrador, "COMPLETA TODOS LOS CAMPOS");
            return false;
        }else return true;
    }
}
