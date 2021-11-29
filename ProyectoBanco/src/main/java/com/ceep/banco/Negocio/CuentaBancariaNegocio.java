
package com.ceep.banco.Negocio;

import com.ceep.banco.accesobd.*;
import com.ceep.banco.dominio.*;


import java.sql.*;
import java.util.*;

/**
 * @author braya
 */
public class CuentaBancariaNegocio {
    
    private final AccesoCuentasBancarias  datos;

    public CuentaBancariaNegocio() {
        this.datos = new AccesoCuentasBancarias();
    }
    
    public void mostrarDatosCuentaBanc(int idCliente)throws SQLException{
        List<CuentaBancaria> cuentas = new ArrayList<>();
        String numeroCuenta = "";
        String fechaApertura = "";
        double saldoDisponible = 0;
        
        cuentas = datos.seleccionar();
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getIdCliente() == idCliente) {
                numeroCuenta = cuentas.get(i).getNumeroCuenta();
                fechaApertura = cuentas.get(i).getFechaApertura();
                saldoDisponible = cuentas.get(i).getSaldo();
            }
        }
        System.out.println("==============================================================================");
        System.out.println("============================DATOS BANCARIOS===================================");
        System.out.println("==============================================================================");
        System.out.println("\t\tNUMERO DE CUENTA: " +numeroCuenta);
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\t\tSALDO DISPONIBLE: " +saldoDisponible+"€");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("\t\tFECHA DE APERTURA: " +fechaApertura);
        System.out.println("------------------------------------------------------------------------------");
    }
    
    public void mostrarSaldo(int idCliente)throws SQLException{
        
        List<CuentaBancaria> cuentas = new ArrayList<>();
        
        double saldoDisponible = 0;
        
        cuentas = datos.seleccionar();
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getIdCliente() == idCliente) {
                saldoDisponible = cuentas.get(i).getSaldo();
            }
        }
        
        System.out.println("==============================================================================");
        System.out.println("============================SALDO DISPONIBLE==================================");
        System.out.println("==============================================================================");
        System.out.println("\t\tSALDO DISPONIBLE: "+saldoDisponible);
        System.out.println("------------------------------------------------------------------------------");
    }
    
    public void modificarSaldo(int idCuentaBancaria,double monto, int idCliente)throws SQLException{
        
        List<CuentaBancaria> cuentas = new ArrayList<>();
        
        double saldoDisponible = 0;
        double nuevoMonto = 0;
        cuentas = datos.seleccionar();
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getIdCliente() == idCliente) {
                saldoDisponible = cuentas.get(i).getSaldo();
            }
        }
        
        nuevoMonto = saldoDisponible - monto;
        
        datos.actualizar(idCuentaBancaria,nuevoMonto);
    }
    
    public int conseguirIdCuentaBancaria(int idCliente)throws SQLException{
        List<CuentaBancaria> cuentas = new ArrayList<>();
        int idCli = 0;
        
        cuentas = datos.seleccionar();
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getIdCliente() == idCliente) {
                idCli = cuentas.get(i).getIdCliente();
            }
        }
        
        return idCli;
    }
}
