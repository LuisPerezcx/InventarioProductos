package Modelo;

import javax.swing.*;

public class Validaciones{
    private static final JFrame frame = new JFrame();
    public static boolean validarIndexTabla(int index){
        if(index != -1){
            return true;
        }else{
            JOptionPane.showMessageDialog(frame,"Primero selecciona un elemento de la lista");
            return false;
        }
    }
    public static int validarNumeros(String str) {
        int cantidad;
        try {
            cantidad = Integer.parseInt(str);
            if (cantidad > 0) {
                return cantidad;
            } else {
                JOptionPane.showMessageDialog(frame, "Cantidad inválida");
                return -1;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Cantidad inválida");
            return -1;
        }
    }
    public static double validarDouble(String str){
        double cantidad;
        try {
            cantidad = Double.parseDouble(str);
            if (cantidad > 0) {
                return cantidad;
            } else {
                JOptionPane.showMessageDialog(frame, "Cantidad inválida");
                return -1;
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(frame, "Precio invalido");
            return -1;
        }
    }
    public static boolean validarCantidad(int existentes, int solicitadas){
        if (solicitadas == -1 ){
            return false;
        }else {
            if (existentes<solicitadas){
                JOptionPane.showMessageDialog(frame,"No hay productos suficientes");
                return false;
            }else return true;
        }
    }
}
