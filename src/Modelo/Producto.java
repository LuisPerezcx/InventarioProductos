package Modelo;

import Util.Util;

import java.util.ArrayList;
import java.util.Comparator;

public class Producto {
    private final int codigo;
    private final String nombre;
    private final double precio;
    private int cantidad;
    private final String departamento;

    public Producto(int codigo, String nombre, double precio, int cantidad, String departamento) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.departamento = departamento;
    }

    public static ArrayList<Producto> ordenarPorNombre() {
        ArrayList<Producto> copiaLista = new ArrayList<>(Util.productoArrayList);
        copiaLista.sort(Comparator.comparing(Producto::getNombre));
        return copiaLista;
    }
    public static ArrayList<Producto> ordenarPorPrecioMenorAMayor() {
        ArrayList<Producto> copiaLista = new ArrayList<>(Util.productoArrayList);
        copiaLista.sort(Comparator.comparingDouble(Producto::getPrecio));
        return copiaLista;
    }
    public static ArrayList<Producto> ordenarPorPrecioMayorAMenor() {
        ArrayList<Producto> copiaLista = new ArrayList<>(Util.productoArrayList);
        copiaLista.sort(Comparator.comparingDouble(Producto::getPrecio).reversed());
        return copiaLista;
    }
    public static ArrayList<Producto> ordenarPorDepartamento() {
        ArrayList<Producto> copiaLista = new ArrayList<>(Util.productoArrayList);
        copiaLista.sort(Comparator.comparing(Producto::getDepartamento));
        return copiaLista;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public int getCodigo() {
        return codigo;
    }
}
