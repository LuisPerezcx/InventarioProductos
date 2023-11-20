package Controlador;

import Modelo.Carrito;
import Modelo.Producto;
import Modelo.Validaciones;
import Modelo.Venta;
import Util.Util;
import Vista.VistaCarrito;
import Vista.VistaPrincipal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

public class ControladorVistaCarrito extends CRUD implements ActionListener {
    private final VistaCarrito vistaCarrito;
    private final VistaPrincipal vistaPrincipal;
    public int indexT = -1;

    public ControladorVistaCarrito(VistaCarrito vistaCarrito, VistaPrincipal vistaPrincipal) {
        this.vistaCarrito = vistaCarrito;
        this.vistaPrincipal = vistaPrincipal;
        tablaListener();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object event = e.getSource();
        if (event == vistaCarrito.btnRegresar){
            vistaCarrito.setVisible(false);
            vistaPrincipal.setVisible(true);
            vistaPrincipal.initControl();
        } else if (event == vistaCarrito.btnEliminarDelCarrito) {
            if(Validaciones.validarIndexTabla(indexT)) {
                String nombreProducto = Util.carritoArrayList.get(indexT).getProducto().getNombre();
                int cuantos = Validaciones.validarNumeros(JOptionPane.showInputDialog(vistaCarrito, "Cuantos " + nombreProducto + " deseas eliminar", "CONFIRMACION", JOptionPane.INFORMATION_MESSAGE));
                int actuales = Util.carritoArrayList.get(indexT).getCantidad();
                int temIndex = indexT;
                if (Validaciones.validarCantidad(actuales, cuantos)) {
                    if (cuantos == actuales) {
                        regresarProducto(cuantos, temIndex);
                        eliminar(Util.carritoArrayList, indexT);
                    } else {
                        Util.carritoArrayList.get(indexT).setCantidad((actuales-cuantos));
                        regresarProducto(cuantos, temIndex);
                    }
                    vistaCarrito.initControl();
                    vistaCarrito.calcularTotal();
                }
            }
        } else if (event == vistaCarrito.btnPagarCarrito) {
            if(Util.carritoArrayList.isEmpty()){
                JOptionPane.showMessageDialog(vistaCarrito,"CARRITO VACIO");
            }else{
                llenarArrayVentas();
                eliminarTodo(Util.carritoArrayList);
                vistaCarrito.initControl();
                JOptionPane.showMessageDialog(vistaCarrito,"-PAGADO-");
                vistaCarrito.total1.setText("$ 0");
            }
        }
    }
    private void llenarArrayVentas(){
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        for(Carrito carrito : Util.carritoArrayList){
            double precio;
            double totalVenta;
            int cantidad = carrito.getCantidad();
            Producto producto = carrito.getProducto();
            precio = carrito.getProducto().getPrecio();
            totalVenta = cantidad*precio;
            agregar(Util.ventaArrayList, new Venta(producto,cantidad,fecha,hora,totalVenta));
        }
    }
    private void regresarProducto(int cuantos, int index) {
        System.out.println("Index = "+index);
        System.out.println("length = " + Util.carritoArrayList.size());
        int indexAnterior = Util.carritoArrayList.get(index).getIndex();
        int cantidadAnterior = Util.productoArrayList.get(indexAnterior).getCantidad();
        Util.productoArrayList.get(indexAnterior).setCantidad(cantidadAnterior + cuantos);
        vistaPrincipal.initControl();
    }
    private void tablaListener(){
        vistaCarrito.tablaCarrito.getSelectionModel().addListSelectionListener(e -> {
            indexT = vistaCarrito.tablaCarrito.getSelectedRow();
        });
    }
}
