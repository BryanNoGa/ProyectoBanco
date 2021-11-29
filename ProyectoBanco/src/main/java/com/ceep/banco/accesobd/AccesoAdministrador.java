
package com.ceep.banco.accesobd;

import static com.ceep.banco.conexion.Conexion.close;
import static com.ceep.banco.conexion.Conexion.getConnection;
import com.ceep.banco.dominio.*;
import java.sql.*;
import java.util.*;

/**
 * @author braya
 */
public class AccesoAdministrador {
    
    private static final String SQL_SELECT = "SELECT * FROM administradores";
    
    
    public List<Administrador> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Administrador> administradores = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idAdministradores = rs.getInt("idAdministradores");
                String docIdentidadAdmin = rs.getString("docIdentidadAdmin");
                String nombreAdmin = rs.getString("nombreAdmin");
                String email = rs.getString("email");
                String cargo = rs.getString("cargo");
                int pass = rs.getInt("password");
                
                administradores.add(new Administrador(nombreAdmin,email,cargo,idAdministradores,docIdentidadAdmin,pass));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            close(rs);
            close(stmt);
            close(conn);
        }
        return administradores;
    }
}
