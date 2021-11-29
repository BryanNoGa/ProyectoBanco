package com.ceep.banco.accesobd;

import static com.ceep.banco.conexion.Conexion.close;
import static com.ceep.banco.conexion.Conexion.getConnection;
import com.ceep.banco.dominio.*;
import java.sql.*;
import java.util.*;

/**
 * @author braya
 */
public class AccesoSolicitudPrestamos {
   
    private static final String SQL_SELECT = "SELECT * FROM solicitudprestamos";
    
    private static final String SQL_INSERT = "INSERT INTO solicitudprestamos"
            + "(idCliente,fechaSolicitud,montoSolicitado) VALUES"
            + "(?, ?, ?)";
    
    private static final String SQL_DELETE = "DELETE FROM solicitudprestamos "
            + "WHERE idCliente = ?";
    
    public int insertar(SolicitudPrestamo solicitudprestamo) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn  = getConnection();
            
            stmt = conn.prepareStatement(SQL_INSERT); 
            stmt.setInt(1, solicitudprestamo.getIdCliente());
            stmt.setString(2, solicitudprestamo.getFechaSolicitud());
            stmt.setDouble(3, solicitudprestamo.getMontoSolicitado());
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
    
    public List<SolicitudPrestamo> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<SolicitudPrestamo> solicitudprestamos = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idsolicitudPrestamos = rs.getInt("idsolicitudPrestamos");
                int idCliente = rs.getInt("idCliente");
                String fechaSolicitud = rs.getString("fechaSolicitud");
                double montoSolicitado = rs.getDouble("montoSolicitado");
                
                solicitudprestamos.add(new SolicitudPrestamo(idsolicitudPrestamos,idCliente,fechaSolicitud,montoSolicitado));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            close(rs);
            close(stmt);
            close(conn);
        }
        return solicitudprestamos;
    }
    
    public int delete(int idCliente) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registro = 0;
        
        try{
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
        
            stmt.setInt(1, idCliente);
        
            registro = stmt.executeUpdate();
        } catch(SQLException ex){
            ex.printStackTrace();
        } finally {
            close(stmt);
            close(conn);
        }
        
        
        return registro;
    }
}
