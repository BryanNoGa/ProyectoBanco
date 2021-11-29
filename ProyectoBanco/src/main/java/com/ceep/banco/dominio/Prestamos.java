package com.ceep.banco.dominio;

/**
 * @author braya
 */
public class Prestamos {
   
    private int idPrestamo;
    private int idCuentaBancaria;
    private int idCliente;  
    private double cantidadPrestamo;
    private double pagoMensual;
    private double interesAnual;
    private String fechaInicioPrestamo;
    private String fechaFinPrestamo;

    public Prestamos() {
    }

    public Prestamos(int idCuentaBancaria, int idCliente, double cantidadPrestamo, double pagoMensual, double interesAnual, String fechaInicioPrestamo, String fechaFinPrestamo) {
        this.idCuentaBancaria = idCuentaBancaria;
        this.idCliente = idCliente;
        this.cantidadPrestamo = cantidadPrestamo;
        this.pagoMensual = pagoMensual;
        this.interesAnual = interesAnual;
        this.fechaInicioPrestamo = fechaInicioPrestamo;
        this.fechaFinPrestamo = fechaFinPrestamo;
    }

    public Prestamos(double cantidadPrestamo, double pagoMensual, double interesAnual, String fechaInicioPrestamo, String fechaFinPrestamo) {
        this.cantidadPrestamo = cantidadPrestamo;
        this.pagoMensual = pagoMensual;
        this.interesAnual = interesAnual;
        this.fechaInicioPrestamo = fechaInicioPrestamo;
        this.fechaFinPrestamo = fechaFinPrestamo;
    }

    public Prestamos(int idPrestamo, int idCuentaBancaria, int idCliente, double cantidadPrestamo, double pagoMensual, double interesAnual, String fechaInicioPrestamo, String fechaFinPrestamo) {
        this.idPrestamo = idPrestamo;
        this.idCuentaBancaria = idCuentaBancaria;
        this.cantidadPrestamo = cantidadPrestamo;
        this.pagoMensual = pagoMensual;
        this.interesAnual = interesAnual;
        this.fechaInicioPrestamo = fechaInicioPrestamo;
        this.fechaFinPrestamo = fechaFinPrestamo;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public double getCantidadPrestamo() {
        return cantidadPrestamo;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public double getInteresAnual() {
        return interesAnual;
    }

    public String getFechaInicioPrestamo() {
        return fechaInicioPrestamo;
    }

    public String getFechaFinPrestamo() {
        return fechaFinPrestamo;
    }

    public void setCantidadPrestamo(double cantidadPrestamo) {
        this.cantidadPrestamo = cantidadPrestamo;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public void setInteresAnual(double interesAnual) {
        this.interesAnual = interesAnual;
    }

    public void setFechaInicioPrestamo(String fechaInicioPrestamo) {
        this.fechaInicioPrestamo = fechaInicioPrestamo;
    }

    public void setFechaFinPrestamo(String fechaFinPrestamo) {
        this.fechaFinPrestamo = fechaFinPrestamo;
    }

    @Override
    public String toString() {
        return "\n Cantidad de Prestamo: " + cantidadPrestamo + "\n Pago Mensual: " + pagoMensual + "\n Interes Anual: " + interesAnual + "\n Fecha de Inicio Prestamo: " + fechaInicioPrestamo + "\n Fecha Final del Prestamo: " + fechaFinPrestamo;
    }
    
    
}
