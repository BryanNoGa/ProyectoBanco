package com.ceep.banco.dominio;

/**
 * @author braya
 */
public class CuentaBancaria {
    
    private int idCuentaBancaria;
    private int idCliente;
    private String numeroCuenta;
    private String fechaApertura;
    private double saldo;

    public CuentaBancaria() {
    }

    public CuentaBancaria(String numeroCuenta, String fechaApertura, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
    }

    public CuentaBancaria(int idCuentaBancaria, int idCliente,String numeroCuenta, String fechaApertura, double saldo) {
        this.idCuentaBancaria = idCuentaBancaria;
        this.idCliente = idCliente;
        this.numeroCuenta = numeroCuenta;
        this.fechaApertura = fechaApertura;
        this.saldo = saldo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    
    
    
}
