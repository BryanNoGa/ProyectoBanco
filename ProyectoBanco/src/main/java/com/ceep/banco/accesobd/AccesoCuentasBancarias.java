/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceep.banco.accesobd;

import static com.ceep.banco.conexion.Conexion.*;
import com.ceep.banco.dominio.*;

import java.sql.*;
import java.util.*;

/**
 *
 * @author braya
 */
public class AccesoCuentasBancarias {

    private static final String SQL_SELECT = "SELECT * FROM cuentasbancarias";
    
    private static final String SQL_UPDATE = "UPDATE cuentasbancarias SET "
            + "saldo = ? "
            + "WHERE idCliente = ?";
    
    public List<CuentaBancaria> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<CuentaBancaria> cuenta = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idCuentaBancaria = rs.getInt("idCuentaBancaria");
                int idCliente = rs.getInt("idCliente");
                String numeroCuenta = rs.getString("numeroCuenta");
                String fechaApertura = rs.getString("fechaApertura");
                double saldo = rs.getDouble("saldo");
                
                cuenta.add(new CuentaBancaria(idCuentaBancaria,idCliente,numeroCuenta,fechaApertura,saldo));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            close(rs);
            close(stmt);
            close(conn);
        }
        return cuenta;
    }


    public int actualizar(int idCliente, double nuevoMonto) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setDouble(1, nuevoMonto);
            stmt.setInt(2, idCliente);
            
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally{
            close(stmt);
            close(conn);
        }
        
        return registros;
    }
    
}
