package com.ceep.banco.accesobd;

import com.ceep.banco.dominio.*;
import static com.ceep.banco.conexion.Conexion.*;

//Librerias
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.util.List;

/**
 * @author braya
 */


public class AccesoCliente {

    //CONSUTAS TABLA CLIENTE
    private static final String SQL_SELECT = "SELECT * FROM cliente";
    private static final String SQL_INSERT = "INSERT INTO cliente"
            + "(docIdentidad, nombre, apellido, email, direccion, fechaNacimiento, password) VALUES"
            + "(?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_FOUND = "SELECT idCliente FROM cliente "
            + "WHERE docIdentidad = ?";
    private static final String SQL_UPDATE = "UPDATE cliente SET "
            + "password = ? "
            + "WHERE idCliente = ?";
    //CONSULTAS TABLA CUENTAS BANCARIAS
    private static final String SQL_INSERTCB = "INSERT INTO cuentasbancarias"
            + "(idCliente, numeroCuenta, fechaApertura, saldo) VALUES"
            + "(?, ?, ?, ?)";
    private static final String SQL_FOUND_CUENTABAN = "SELECT numeroCuenta FROM cuentasbancarias "
            + "WHERE numeroCuenta = ?";
    
    
    public List<Cliente> seleccionar() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idCliente = rs.getInt("idCliente");
                String docIden = rs.getString("docIdentidad");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String direccion = rs.getString("direccion");
                String fecha = rs.getString("fechaNacimiento");
                int pass = rs.getInt("password");
                
                clientes.add(new Cliente(nombre,apellido,email,direccion,fecha,idCliente,docIden,pass));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            close(rs);
            close(stmt);
            close(conn);
        }
        return clientes;
    }

    
    public int insertar(Cliente cliente,double monto) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn  = getConnection();
            // Hacemos de la query un bloque con el autocommit que por defecto es true
//            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(SQL_INSERT); 
            stmt.setString(1, cliente.getDocIdentidad());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getDireccion());
            stmt.setString(6, cliente.getFechaNacimiento());
            stmt.setInt(7, cliente.getPassword());
            registros = stmt.executeUpdate();
            
//            registros = stmt.executeUpdate();
//             Despues de ejecutar las dos querys le damos el okey lo hacemos con commit 
//            conn.commit();
//             Hay que decirle tambien que si algo va mal que me deje la base de datos como estaba
//             que vendria siendo la integridad de los datos
            System.out.println("Registro Realizado Correctamente.");
            crearCuentaBancaria(cliente,monto);
            
        } catch (SQLException ex) {
            System.out.println("Error en la Insercción de Datos.");
            ex.printStackTrace(System.out);
//            conn.rollback();
        }finally {
            close(stmt);
            close(conn);
        }
        
        return registros;
    }

    public int crearCuentaBancaria(Cliente cliente,double monto) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int ide= 0;
        String numeroCuentaBancaria;
        String fechaActual;
        try {
            conn  = getConnection();
            stmt = conn.prepareStatement(SQL_FOUND);
            stmt.setString(1, cliente.getDocIdentidad());
            rs = stmt.executeQuery();
            if(rs.next()){
                ide = rs.getInt("idCliente");
            }
            else {
                System.out.println("Articulo no encontrado");
            }
            
            // Generamos una cuenta bancaria
            numeroCuentaBancaria = generarCuentaBancariaAuto();
            // Fecha actual del dispositivo
            Date mydate = new Date();
            fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(mydate);
            
            stmt = conn.prepareStatement(SQL_INSERTCB);
            stmt.setInt(1, ide);
            stmt.setString(2, numeroCuentaBancaria);
            stmt.setString(3, fechaActual);
            stmt.setDouble(4, monto);
            
            ide = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error en la Insercción de Datos.");
            ex.printStackTrace(System.out);
        }finally {
            close(stmt);
            close(conn);
        }
        
        return ide;
    }
    
    public String generarCuentaBancariaAuto()throws SQLException{
        int numeroCuenta = alea(100000000,999999999);
        int digitoControl = alea(10,99);
        int entidadFinanciera = 5647; 
        String cuentaBuscada;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String cuenta;
        
        Boolean bol = true;
        try{
            //METODO WHILE PARA QUE NUNCA ASIGNE UNA CUENTA A UN CLIENTE QUE YA EXISTE
            while(bol){
                numeroCuenta = alea(100000000,999999999);
                digitoControl = alea(10,99);
                cuenta = "ES"+String.valueOf(digitoControl)+""+String.valueOf(entidadFinanciera)+""+String.valueOf(numeroCuenta);
                conn = getConnection();
                stmt = conn.prepareStatement(SQL_FOUND_CUENTABAN);
                stmt.setString(1, cuenta);
                rs = stmt.executeQuery();
                    if(rs.next()){
                        cuentaBuscada = rs.getString("numeroCuenta");
                    }else {
                        bol = false;
                    }
            }
            
        } catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
                
        cuenta = "ES"+String.valueOf(digitoControl)+""+String.valueOf(entidadFinanciera)+""+String.valueOf(numeroCuenta);
        
        return cuenta;
    }
    
    public int alea(int li, int ls){
        return (int)(Math.random()*(ls-li+1)+li); 
    }
    
    
    public int actualizar(Cliente cliente, int nuevaPass) throws SQLException {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            
            stmt.setInt(1, nuevaPass);
            stmt.setInt(2, cliente.getIdUsuario());
            
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
