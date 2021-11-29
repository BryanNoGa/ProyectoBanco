
package com.ceep.banco.Negocio;

import com.ceep.banco.dominio.*;
import com.ceep.banco.accesobd.*;
import com.ceep.banco.FicheroNegocio.*;
import com.ceep.banco.excepcionesFicheros.AccesoFicheroEx;
import com.ceep.banco.enumerados.Cargos;


import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * @author braya
 */
public class AdministradorNegocio {
    
    private final AccesoAdministrador  datos;
    private final TransaccionesNegocio transacciones;
    private final ClienteNegocio cliente;
    private final PrestamosNegocio prestamos;
    private final FicheroNegocio fichero;
    private final AccesoSolicitudPrestamos solicitudPrestamos;
    private final SolicitudPrestamoNegocio negSolicitudPrestamos;

    public AdministradorNegocio() {
        this.datos = new AccesoAdministrador();
        this.transacciones = new TransaccionesNegocio();
        this.cliente = new ClienteNegocio();
        this.prestamos = new PrestamosNegocio();
        this.fichero = new FicheroNegocio();
        this.solicitudPrestamos = new AccesoSolicitudPrestamos();
        this.negSolicitudPrestamos = new SolicitudPrestamoNegocio();
    }
    
    
    
    public void intrducirDatosLogin()throws SQLException, AccesoFicheroEx, IOException{
        Scanner ent = new Scanner(System.in);
        String docIdentidadAdmin = "";
        int opcion;
        int pass = 0;
        boolean disp = true;
            System.out.println("\nINTRODUCE TU DOCUMENTO DE IDENTIDAD");
            System.out.println("------------------------------------");
            docIdentidadAdmin = ent.nextLine();
            System.out.println("INTRODUCE TU CONTRASEÑA DE ACCESO");
            System.out.println("------------------------------------");
            pass = ent.nextInt();
            disp = verificarLogin(docIdentidadAdmin, pass);
                if(disp == false){
                    System.out.println("\n============================================");
                    System.out.println("BIENVENIDO/A "+saludoBienvenida(docIdentidadAdmin));
                    System.out.println("=============================================");
                    menuAdministrador(docIdentidadAdmin);
                    }else{
                        System.out.println("\nLOS DATOS INTRODUCIDOS SON INCORRECTOS O EL USUARIO NO EXISTE");
                        return;
                    }
    }
    
    public void menuAdministrador(String docIdentidadAdmin)throws SQLException, AccesoFicheroEx, IOException{
        int opcion;
        Scanner menu = new Scanner(System.in);
        while(true){
            System.out.println("=====================================================================================================");
            System.out.println("=====================================   OPERACIONES DISPONIBLES   ===================================");
            System.out.println("=====================================================================================================");
            System.out.println("\t1.- Ver cliente.");
            System.out.println("\t2.- Ver transacciones.");
            System.out.println("\t3.- Ver prestamos");
            System.out.println("\t4.- Ver solicitudes de prestamos.");
            System.out.println("\t5.- Aprobar prestamo.");
            System.out.println("\t0.- Cerrar sesión.");
            System.out.println("\tElige una opcion");
        opcion = menu.nextInt();
        switch (opcion) {
            case 1:
                verClientes();
                break;
            case 2:
                verTransacciones();
                break;
            case 3:
                verPrestamos();
                break;
            case 4:
                verSolicitudesPrestamos();
                break;
            case 5:
                if(verificarCargo(docIdentidadAdmin)){
                    aprobarPrestamo();
                }else {
                    System.out.println("\nNo estás autorizado a aprobar prestamos.");
                }
                break;
            case 0:
                System.out.println();
                return;
            default:
                System.out.println("Debe seleccionar una opción entre 0 y 4");
            }
        }
    }
    
    public void verClientes() throws SQLException, AccesoFicheroEx, IOException{
        int opcion;
        int idCliente;
        String docIdentidad;
        String nombreFichero = "clientes.txt";
        Scanner menu = new Scanner(System.in);
        while(true){
            fichero.crear(nombreFichero);
            System.out.println("\n=====================================================================================================");
            System.out.println("=====================================   OPERACIONES CLIENTES   ======================================");
            System.out.println("=====================================================================================================");
            System.out.println("\t1.- Filtrar por identificador de cliente.");
            System.out.println("\t2.- Filtrar por documento de identidad.");
            System.out.println("\t3.- Listar todos los clientes de mi banco.");
            System.out.println("\t0.- Volver.");
            System.out.println("\tElige una opcion");
        opcion = menu.nextInt();
        menu.nextLine();
        switch (opcion) {
            case 1:
                System.out.println("Escriba el identificador del cliente.");
                idCliente = menu.nextInt();
                fichero.escribirClientes(nombreFichero);
                fichero.listarClienteById(nombreFichero, idCliente);
                fichero.borrar(nombreFichero);
                break;
            case 2:
                System.out.println("Escriba el documento de identidad del cliente.");
                docIdentidad = menu.nextLine();
                fichero.escribirClientes(nombreFichero);
                fichero.listarClienteByDoc(nombreFichero, docIdentidad);
                fichero.borrar(nombreFichero);
                break;
            case 3:
                System.out.println("\n=======================================================================================================");
                System.out.println("=====================================   LISTADO CLIENTES BANCO   ======================================");
                System.out.println("=======================================================================================================");
                fichero.escribirClientes(nombreFichero);
                fichero.listarClientes(nombreFichero);
                fichero.borrar(nombreFichero);
                break;
            case 0:
                System.out.println("Gracias por utilizar la aplicación");
                return;
            default:
                System.out.println("Debe seleccionar una opción entre 0 y 2");
            }
        }
    }
    
    public void verTransacciones()throws SQLException{
        int opcion;
        Scanner menu = new Scanner(System.in);
        while(true){
            System.out.println("\n=====================================================================================================");
            System.out.println("=====================================   OPERACIONES TRANSACCIONES   ===================================");
            System.out.println("=====================================================================================================");
            System.out.println("\t1.- Filtrar por documento de identidad.");
            System.out.println("\t0.- Volver.");
            System.out.println("\tElige una opcion");
        opcion = menu.nextInt();
        menu.nextLine();
        switch (opcion) {
            case 1:
                String docIdentidadCliente = "";
                int idCliente = 0;
                System.out.println("INTRODUZCA EL DOCUMENTO DE IDENTIDAD DEL CLIENTE:");
                docIdentidadCliente = menu.nextLine();
                idCliente = cliente.conseguirIdCliente(docIdentidadCliente);
                System.out.println(docIdentidadCliente);
                transacciones.listarTransaccionesCliente(idCliente);
                break;
            case 0:
                System.out.println("Gracias por utilizar la aplicación");
                return;
            default:
                System.out.println("Debe seleccionar una opción entre 0 y 1");
            }
        }
    }
    
    public void verPrestamos()throws SQLException{
        int opcion;
        Scanner menu = new Scanner(System.in);
        while(true){
            System.out.println("\n=====================================================================================================");
            System.out.println("=====================================   OPERACIONES PRESTAMOS   =====================================");
            System.out.println("=====================================================================================================");
            System.out.println("\t1.- Filtrar por documento de identidad.");
            System.out.println("\t0.- Volver.");
            System.out.println("\tElige una opcion");
        opcion = menu.nextInt();
        menu.nextLine();
        switch (opcion) {
            case 1:
                String docIdentidadCliente = "";
                int idCliente = 0;
                System.out.println("INTRODUZCA EL DOCUMENTO DE IDENTIDAD DEL CLIENTE:");
                docIdentidadCliente = menu.nextLine();
                idCliente = cliente.conseguirIdCliente(docIdentidadCliente);
                System.out.println(docIdentidadCliente);
                prestamos.listarPrestamosCliente(idCliente);
                break;
            case 0:
                System.out.println("Gracias por utilizar la aplicación");
                return;
            default:
                System.out.println("Debe seleccionar una opción entre 0 y 1");
            }
        }
    }
    
    public void verSolicitudesPrestamos()throws SQLException{
        
        List<SolicitudPrestamo> solicitudprestamos = new ArrayList<>();
        solicitudprestamos = solicitudPrestamos.seleccionar();
            System.out.println("\n=======================================================================================================");
            System.out.println("=====================================   SOLICITUDES DE PRESTAMOS   =====================================");
            System.out.println("========================================================================================================");
        solicitudprestamos.forEach(solPres ->{
            System.out.println(""+solPres);
        }); 
    }
    
    public void  aprobarPrestamo() throws SQLException{
        Scanner ent = new Scanner(System.in);
        int idSolicitudPrestamo;
        int opcion;
        while(true){
            System.out.println("\n==========================================================================================================");
            System.out.println("=====================================   AUTORIZACIONES DE PRESTAMOS   =====================================");
            System.out.println("===========================================================================================================");
            System.out.println("\t1.- Autorizar prestamo");
            System.out.println("\t0.- Volver.");
            System.out.println("\tElige una opcion");
            opcion = ent.nextInt();
            switch (opcion) {
            case 1:
                System.out.println("POR FAVOR INDIQUE EL IDENTIFICADOR DEL PRESTAMO QUE DESEA AUTORIZAR");
                idSolicitudPrestamo = ent.nextInt();
                negSolicitudPrestamos.autorizarPrestamo(idSolicitudPrestamo);
                break;
            case 0:
                System.out.println("Gracias por utilizar la aplicación");
                return;
            default:
                System.out.println("Debe seleccionar una opción entre 0 y 1");
            }
        }
        
    }
    
    public boolean verificarLogin(String docIdentidadAdmin, int pass) throws SQLException{
        
        List<Administrador> administradores = new ArrayList<>();
        boolean existe = true;
        administradores = datos.seleccionar();
        
        for (int i = 0; i < administradores.size(); i++) {
            if(administradores.get(i).getDocIdentidad().equalsIgnoreCase(docIdentidadAdmin) && administradores.get(i).getPassword() == pass){
                 existe = false;
            }
        }
        return existe;
    }
    
    
    public String saludoBienvenida(String docIdentidadAdmin)throws SQLException{
        
        List<Administrador> administradores = new ArrayList<>();
        String nombre = "";
        String saludo = "";
        
        administradores = datos.seleccionar();
        for (int i = 0; i < administradores.size(); i++){
            if(administradores.get(i).getDocIdentidad().equalsIgnoreCase(docIdentidadAdmin)){
                nombre = administradores.get(i).getNombreAdmin();
            }
        }
        saludo = nombre;
        
        return saludo;
    }
    
    public boolean verificarCargo(String docIdentidadAdmin)throws SQLException{
        
        List<Administrador> administradores = new ArrayList<>();
        boolean permitido = false;
        administradores = datos.seleccionar();
        
        for (int i = 0; i < administradores.size(); i++){
            if(administradores.get(i).getCargo().equalsIgnoreCase(Cargos.GERENTE.toString()) &&
                    administradores.get(i).getDocIdentidad().equalsIgnoreCase(docIdentidadAdmin)){
                permitido = true;
            } 
        }
        System.out.println(permitido);
        return permitido;
    }
    
}
