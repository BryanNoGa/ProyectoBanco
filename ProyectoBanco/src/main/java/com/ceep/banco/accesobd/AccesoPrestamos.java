package com.ceep.banco.accesobd;

import static com.ceep.banco.conexion.Conexion.close;
import static com.ceep.banco.conexion.Conexion.getConnection;
import com.ceep.banco.dominio.*;

import java.sql.*;
import java.util.*;

/**
 * @author braya
 */
public class AccesoPrestamos {
    
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM prestamos WHERE idCliente = ? ORDER BY(fechaInicioPrestamo) DESC";
        
    private static final String SQL_SELECT = "SELECT * FROM prestamos WHERE idCliente = ? ORDER BY(fechaInicioPrestamo) DESC";
    
    private static final String SQL_INSERT = "INSERT INTO prestamos"
            + "(idCuentaBancaria, idCliente, cantidadPrestamo, pagoMensual, InteresAnual, fechaInicioPrestamo, fechaFinPrestamo) VALUES"
            + "(?, ?, ?, ?, ?, ?, ?)";
    
    public int insertar(Prestamos prestamo) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn  = getConnection();
            
            stmt = conn.prepareStatement(SQL_INSERT); 
            stmt.setInt(1, prestamo.getIdCuentaBancaria());
            stmt.setInt(2, prestamo.getIdCliente());
            stmt.setDouble(3, prestamo.getCantidadPrestamo());
            stmt.setDouble(4, prestamo.getPagoMensual());
            stmt.setDouble(5, prestamo.getInteresAnual());
            stmt.setString(6, prestamo.getFechaInicioPrestamo());
            stmt.setString(7, prestamo.getFechaFinPrestamo());
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
    
    
     public List<Prestamos> seleccionar(int idCliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Prestamos> prestamos = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                double cantidadPrestamo = rs.getDouble("cantidadPrestamo");
                double pagoMensual = rs.getDouble("pagoMensual");
                double InteresAnual = rs.getDouble("InteresAnual");
                String fechaInicioPrestamo = rs.getString("fechaInicioPrestamo");
                String fechaFinPrestamo = rs.getString("fechaFinPrestamo");
                
                prestamos.add(new Prestamos(cantidadPrestamo,pagoMensual,InteresAnual,fechaInicioPrestamo,fechaFinPrestamo));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            close(rs);
            close(stmt);
            close(conn);
        }
        return prestamos;
    }
     
    public List<Prestamos> seleccionarByIdCliente(int idCliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Prestamos> prestamos = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, idCliente);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idPrestamo = rs.getInt("idPrestamo");
                int idCuentaBancaria = rs.getInt("idCuentaBancaria");
                int idClient = rs.getInt("idCliente");
                double cantidadPrestamo = rs.getDouble("cantidadPrestamo");
                double pagoMensual = rs.getDouble("pagoMensual");
                double InteresAnual = rs.getDouble("InteresAnual");
                String fechaInicioPrestamo = rs.getString("fechaInicioPrestamo");
                String fechaFinPrestamo = rs.getString("fechaFinPrestamo");
                
                prestamos.add(new Prestamos(idPrestamo,idCuentaBancaria,idClient,cantidadPrestamo,pagoMensual,InteresAnual,fechaInicioPrestamo,fechaFinPrestamo));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            close(rs);
            close(stmt);
            close(conn);
        }
        return prestamos;
    }
    
}
