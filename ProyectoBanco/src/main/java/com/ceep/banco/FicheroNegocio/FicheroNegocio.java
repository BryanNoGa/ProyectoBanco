package com.ceep.banco.FicheroNegocio;

/**
 *
 * @author braya
 */
import com.ceep.banco.accesoFicheros.AccesoDatosFichero;
import com.ceep.banco.excepcionesFicheros.*;
import com.ceep.banco.dominio.Cliente;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FicheroNegocio {

    private final AccesoDatosFichero datos;
    
    public FicheroNegocio() {
        this.datos = new AccesoDatosFichero();
    }
    
    public void crear(String nombreFichero) throws AccesoFicheroEx{
        datos.crear(nombreFichero);
    }
    
    public void borrar(String nombreFichero)throws AccesoFicheroEx{
        datos.borrar(nombreFichero);
    }
    
    public void escribirClientes(String nombreFichero)throws SQLException, IOException{
        List<Cliente> clientes = new ArrayList<>();
        BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero));
        bw.write("");
        try {
            datos.escribir(nombreFichero, true);
        } catch (AccesoFicheroEx ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public void listarClientes(String nombreFichero)throws SQLException{
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = this.datos.listar(nombreFichero);
            System.out.println("LISTADO DE LOS CLIENTES DE MI BANCO");
            clientes.forEach(cliente ->{
                System.out.println("\t"+cliente);
            });
        } catch (ExcepFicheroLectura ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error en la lectura");
        }
            
    }
    
    public void listarClienteById(String nombreFichero,int idCliente)throws SQLException{
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = datos.listar(nombreFichero);
            System.out.println("DATOS PERSONALES DE MI CLIENTE");
            clientes.forEach(cliente->{
                if(cliente.getIdUsuario() == idCliente){
                    System.out.println("\t"+cliente);
                } else {
                    System.out.println("El identificador de cliente es incorrecto o no existe pruebe de nuevo por favor.");
                }
            });
        } catch (ExcepFicheroLectura ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error en la lectura");
        }
            
    }
    
    public void listarClienteByDoc(String nombreFichero,String docIdentidad)throws SQLException{
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = datos.listar(nombreFichero);
            System.out.println("DATOS PERSONALES DE MI CLIENTE");
            clientes.forEach(cliente->{
                if(cliente.getDocIdentidad().equalsIgnoreCase(docIdentidad)){
                    System.out.println("\t"+cliente);
                }else{
                    System.out.println("El documento de identidad es incorrecto o no existe pruebe de nuevo por favor.");
                }
            });
        } catch (ExcepFicheroLectura ex) {
            ex.printStackTrace(System.out);
            System.out.println("Error en la lectura");
        }
            
    }
                    
}
