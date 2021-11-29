/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceep.banco.accesoFicheros;

//Paquetes
import com.ceep.banco.dominio.*;
import com.ceep.banco.excepcionesFicheros.*;
import com.ceep.banco.accesobd.AccesoCliente;
//Librerias
import java.util.*;
import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.logging.*;

/**
 * @author braya
 */
public class AccesoDatosFichero {

    private final AccesoCliente clientes;

    public AccesoDatosFichero() {
        this.clientes = new AccesoCliente();
    }

    public boolean existe(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }
    
    public void crear(String nombreFichero) throws AccesoFicheroEx {
        var archivo =  new File(nombreFichero);
        
        try {
            if (!archivo.exists()){
                var salida = new PrintWriter(archivo);
            }else {
                System.out.println("El archivo ya existe");
            }
            
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new AccesoFicheroEx("Excepcion Intentando Crear el Archivo");
        }
    }

    public void escribir(String nombreFichero, boolean anexar) throws ExcepFicheroEscritura, SQLException, AccesoFicheroEx {
        var archivo = new File(nombreFichero);
        List<Cliente> clients = new ArrayList<>();
        try {
            
            clients = clientes.seleccionar();
            
            PrintWriter salida = new PrintWriter(new FileWriter(archivo,anexar));
            clients.forEach(client->{
                salida.println(client.getIdUsuario()+";"+
                    client.getDocIdentidad()+";"+
                    client.getNombre()+";"+
                    client.getApellido()+";"+
                    client.getEmail()+";"+
                    client.getDireccion()+";"+
                    client.getFechaNacimiento());
            });
            
            salida.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new ExcepFicheroEscritura("Error Intentando Escribir en el Fichero");
        }
        
    }

    public List<Cliente> listar(String nombreFichero) throws ExcepFicheroLectura {
        var archivo = new File(nombreFichero);
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = null;
        String separador [] = new String[7];
        
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            
                while(lectura != null){
                    separador = lectura.split(";");
                    
                    clientes.add(new Cliente (Integer.parseInt(separador[0]),
                            separador[1],separador[2],separador[3],
                            separador[4],separador[5],separador[6]));
                    lectura = entrada.readLine();
                }
                entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex){
            ex.printStackTrace(System.out);
            throw new ExcepFicheroLectura("Error Intentando Leer el Fichero");
        }
        
        return clientes;
    }
    

    public void borrar(String nombreFichero) throws AccesoFicheroEx {
        var archivo = new File(nombreFichero);
            archivo.delete();
            System.out.println("Borrado");
    }

    
    
}
