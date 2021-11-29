package com.ceep.banco.accesobd;

import static com.ceep.banco.conexion.Conexion.*;
import com.ceep.banco.dominio.*;

import java.sql.*;
import java.util.*;

/**
 * @author braya
 */
public class AccesoTransacciones {
    
   
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM transacciones WHERE idCliente = ? ORDER BY(fechaTransaccion) DESC";
    
    private static final String SQL_SELECT = "SELECT fechaTransaccion, nombreDestinatario, cuentaDestinatario, concepto, monto "
            + "FROM transacciones "
            + "WHERE idCliente = ? ORDER BY(fechaTransaccion) DESC";
    
    private static final String SQL_INSERT = "INSERT INTO transacciones"
            + "(idCuentaBancaria, idCliente, fechaTransaccion, nombreDestinatario, cuentaDestinatario, concepto, monto) VALUES"
            + "(?, ?, ?, ?, ?, ?, ?)";
    
    
    public int insertar(Transacciones transaccion) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn  = getConnection();
            
            stmt = conn.prepareStatement(SQL_INSERT); 
            stmt.setInt(1, transaccion.getIdCuentaBancaria());
            stmt.setInt(2, transaccion.getIdCliente());
            stmt.setString(3, transaccion.getFechaTransaccion());
            stmt.setString(4, transaccion.getNombreDestinatario());
            stmt.setString(5, transaccion.getCuentaDestinatario());
            stmt.setString(6, transaccion.getConcepto());
            stmt.setDouble(7, transaccion.getMonto());
            registros = stmt.executeUpdate();
            
            System.out.println("Registro Realizado Correctamente.");
            
        } catch (SQLException ex) {
            System.out.println("Error en la Insercción de Datos.");
            ex.printStackTrace(System.out);
        }finally {
            close(stmt);
            close(conn);
        }
        
        return registros;
    }
    
    public List<Transacciones> seleccionarByDocIdentidad(int idCliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Transacciones> transacciones = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idTransaccion = rs.getInt("idTransaccion");
                int idCuentaBancaria = rs.getInt("idCuentaBancaria");
                int idClient = rs.getInt("idCliente");
                String fechaTransaccion = rs.getString("fechaTransaccion");
                String nombreDestinatario = rs.getString("nombreDestinatario");
                String cuentaDestinatario = rs.getString("cuentaDestinatario");
                String concepto = rs.getString("concepto");
                Double monto = rs.getDouble("monto");
                
                transacciones.add(new Transacciones(idTransaccion,idCuentaBancaria,idClient,fechaTransaccion,nombreDestinatario,cuentaDestinatario,concepto,monto));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            close(rs);
            close(stmt);
            close(conn);
        }
        return transacciones;
    }
    
    public List<Transacciones> seleccionar(int idCliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Transacciones> transacciones = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                String fechaTransaccion = rs.getString("fechaTransaccion");
                String nombreDestinatario = rs.getString("nombreDestinatario");
                String cuentaDestinatario = rs.getString("cuentaDestinatario");
                String concepto = rs.getString("concepto");
                Double monto = rs.getDouble("monto");
                
                transacciones.add(new Transacciones(fechaTransaccion,nombreDestinatario,cuentaDestinatario,concepto,monto));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            close(rs);
            close(stmt);
            close(conn);
        }
        return transacciones;
    }
}
