package com.ceep.banco.dominio;



/**
 * @author braya
 */
import java.util.*;

public class Transacciones {
    
    private int idTransaccion;
    private int idCuentaBancaria;
    private int idCliente;
    private String fechaTransaccion;
    private String nombreDestinatario;
    private String cuentaDestinatario;
    private String concepto;
    private double monto;

    public Transacciones() {
    }

    public Transacciones(String fechaTransaccion, String nombreDestinatario, String cuentaDestinatario, String concepto, double monto) {
        this.fechaTransaccion = fechaTransaccion;
        this.nombreDestinatario = nombreDestinatario;
        this.cuentaDestinatario = cuentaDestinatario;
        this.concepto = concepto;
        this.monto = monto;
    }

    public Transacciones(int idCuentaBancaria, int idCliente, String fechaTransaccion, String nombreDestinatario, String cuentaDestinatario, String concepto, double monto) {
        this.idCuentaBancaria = idCuentaBancaria;
        this.idCliente = idCliente;
        this.fechaTransaccion = fechaTransaccion;
        this.nombreDestinatario = nombreDestinatario;
        this.cuentaDestinatario = cuentaDestinatario;
        this.concepto = concepto;
        this.monto = monto;
    }

    public Transacciones(int idTransaccion,int idCuentaBancaria, int idCliente, String fechaTransaccion, String nombreDestinatario, String cuentaDestinatario, String concepto, double monto) {
        this.idTransaccion = idTransaccion;
        this.idCuentaBancaria = idCuentaBancaria;
        this.idCliente = idCliente;
        this.fechaTransaccion = fechaTransaccion;
        this.nombreDestinatario = nombreDestinatario;
        this.cuentaDestinatario = cuentaDestinatario;
        this.concepto = concepto;
        this.monto = monto;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public String getFechaTransaccion() {
        return fechaTransaccion;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public String getCuentaDestinatario() {
        return cuentaDestinatario;
    }

    public String getConcepto() {
        return concepto;
    }

    public double getMonto() {
        return monto;
    }

    public void setFechaTransaccion(String fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public void setCuentaDestinatario(String cuentaDestinatario) {
        this.cuentaDestinatario = cuentaDestinatario;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "\n Fecha de Transaccion: " + fechaTransaccion + "\n Nombre de Destinatario: " + nombreDestinatario + "\n Cuenta de Destinatario: " + cuentaDestinatario + "\n Concepto: " + concepto + "\n  Monto: " + monto;
    }
    
    
    
    
}
