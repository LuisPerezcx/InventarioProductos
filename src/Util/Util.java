package Util;

import Modelo.Carrito;
import Modelo.Producto;
import Modelo.Usuarios;
import Modelo.Venta;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class Util {
    public static ArrayList<Producto> productoArrayList = new ArrayList<>();
    public static ArrayList<Carrito> carritoArrayList = new ArrayList<>();
    public static ArrayList<Venta> ventaArrayList = new ArrayList<>();
    public static ArrayList<Usuarios> usuariosArrayList =new ArrayList<>();
    public static String[] titulosTabla = {"CÓDIGO","NOMBRE","DISPONIBLES","PRECIO","DEPARTAMENTO"};
    public static String[] departamentos = {"ABARROTES","LACTEOS","LIMPIEZA","EMBUTIDOS"};


    public static class CustomCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (table.getValueAt(row, 2).equals("0")) {
                c.setBackground(Color.RED);
                c.setForeground(Color.WHITE);
            } else {
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
            }
            return c;
        }
    }

}
