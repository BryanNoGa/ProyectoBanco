package com.ceep.banco.dominio;

/**
 * @author braya
 */
public class SolicitudPrestamo {
    
    private int idSolicitudPrestamo;
    private int idCliente;
    private String fechaSolicitud;
    private double montoSolicitado;

    public SolicitudPrestamo() {
    }

    public SolicitudPrestamo(String fechaSolicitud, double montoSolicitado) {
        this.fechaSolicitud = fechaSolicitud;
        this.montoSolicitado = montoSolicitado;
    }

    public SolicitudPrestamo(int idCliente, String fechaSolicitud, double montoSolicitado) {
        this.idCliente = idCliente;
        this.fechaSolicitud = fechaSolicitud;
        this.montoSolicitado = montoSolicitado;
    }

    public SolicitudPrestamo(int idSolicitudPrestamo, int idCliente, String fechaSolicitud, double montoSolicitado) {
        this.idSolicitudPrestamo = idSolicitudPrestamo;
        this.idCliente = idCliente;
        this.fechaSolicitud = fechaSolicitud;
        this.montoSolicitado = montoSolicitado;
    }

    public int getIdSolicitudPrestamo() {
        return idSolicitudPrestamo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public double getMontoSolicitado() {
        return montoSolicitado;
    }

    public void setIdSolicitudPrestamo(int idSolicitudPrestamo) {
        this.idSolicitudPrestamo = idSolicitudPrestamo;
    }

    public void setMontoSolicitado(double montoSolicitado) {
        this.montoSolicitado = montoSolicitado;
    }

    @Override
    public String toString() {
        return "\n Identificador de prestamo: " + idSolicitudPrestamo + "\n Identificador del cliente que solicita el prestamo: " + idCliente + "\n Fecha de la solicitud: " + fechaSolicitud + "\n Monto que solicita: " + montoSolicitado;
    }
    
    
}
