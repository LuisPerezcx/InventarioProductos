package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Venta {
    private final Producto producto;
    private final int cantidad;
    private final LocalDate fecha;
    private final LocalTime hora;
    private final double totalVenta;

    public Venta(Producto producto, int cantidad, LocalDate fecha, LocalTime hora, double totalVenta) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.hora = hora;
        this.totalVenta = totalVenta;
    }

    public Producto getProducto() {
        return producto;
    }
    public int getCantidad() {
        return cantidad;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public String getHora() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = hora.format(formato);;
        return horaFormateada;
    }
    public double getTotalVenta() {
        return totalVenta;
    }
}
