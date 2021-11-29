
package com.ceep.banco.Negocio;

/**
 * @author braya
 */

import com.ceep.banco.accesobd.*;
import com.ceep.banco.dominio.*;
import java.sql.*;
import java.util.*;

public class ClienteNegocio {
    
    private final AccesoCliente  datos;
    private final CuentaBancariaNegocio cuentaBancaria;
    private final TransaccionesNegocio transaccion;
    private final PrestamosNegocio prestamo;

    

    public ClienteNegocio() {
        this.datos = new AccesoCliente();
        this.cuentaBancaria = new CuentaBancariaNegocio();
        this.transaccion = new TransaccionesNegocio();
        this.prestamo = new PrestamosNegocio();
    }
    
    public void crearCuentaNueva()throws SQLException{
        Scanner ent = new Scanner(System.in);
        Boolean disp = true;
        String docIdentidad = "";
        String nombre = "";
        String apellido = "";
        String email = "";
        String direccion = "";
        String fechaNacimiento = "";
        int pass = 0;
        int opcion;
        double montoApertura;
        while(true){
                    System.out.println("\n======================");
                    System.out.println("CREAR UNA CUENTA NUEVA");
                    System.out.println("======================");
                    System.out.println("1. Continuar.");
                    System.out.println("0. Volver.");
                    opcion = ent.nextInt();
                    switch(opcion){
                        case 1:
                                ent.nextLine();
                                System.out.println("INTRODUZCA EL DOCUMENTO DE IDENTIDAD DEBE CONTENER 9 DIGITOS");
                                docIdentidad = ent.nextLine();
                                System.out.println("INTRODUZCA EL NOMBRE");
                                nombre = ent.nextLine();
                                System.out.println("INTRODUZCA EL APELLIDO");
                                apellido = ent.nextLine();
                                System.out.println("INTRODUZCA EL EMAIL");
                                email = ent.nextLine();
                                System.out.println("INTRODUZCA LA DIRECCION");
                                direccion = ent.nextLine();
                                System.out.println("INTRODUZCA LA FECHA DE NACIMIENTO");
                                System.out.println("CON EL SIGUIENTE FORMATO dd-mm-yyyy");
                                fechaNacimiento = ent.nextLine();
                                System.out.println("ELIGA UN CONTRASEÑA DE ACCESO DE 4 DIGITOS");
                                pass = ent.nextInt();
                                if(docIdentidad.equalsIgnoreCase("") ||
                                        nombre.equalsIgnoreCase("") ||
                                        apellido.equalsIgnoreCase("") ||
                                        email.equalsIgnoreCase("") ||
                                        direccion.equalsIgnoreCase("") ||
                                        fechaNacimiento.equalsIgnoreCase("") ||
                                        pass < 1000){
                                    System.out.println("Todos los campos son obligatorios y la contraseña"
                                            + "debe contener 4 digitos por favor vuelva a rellenar los datos.");
                                    return;
                                }
                                ent.nextLine();
                                disp = verificarDocIden(docIdentidad);
                                if(disp){
                                    break;
                                }
                            
                        System.out.println("MUCHAS GRACIAS POR ESCOGERNOS COMO SU NUEVO BANCO DE CONFIANZA");
                        System.out.println("\n EN NUESTRO BANCO PUEDES ABRIR TU CUENTA SIN ENTRADA PERO SI DESEAS AGREGAR \nDINERO POR FAVOR"
                                + "INDICALO ACONTINUACIÓN EN CASO CONTRARIO INTRODUCE EL MONTO 0:");
                        montoApertura = ent.nextDouble();
                        datos.insertar(new Cliente(nombre,apellido,email,direccion,fechaNacimiento,docIdentidad,pass), montoApertura);
                        System.out.println("=========================================================");
                            System.out.println("AHORA YA PUEDES ACCEDER COMO CLIENTE A TU CUENTA DE BANCARIA POR FAVOR\n"
                                    + "\tENTRA COMO CLIENTE, USANDO EL DOCUMENTO DE IDENTIDAD COMO USUARIO");
                            System.out.println("=========================================================");
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("Debe seleccionar una opcion entre 0 y 1.");
                    }
                }
    }
    
    
    public boolean verificarDocIden(String docIdentidad)throws SQLException{
        
        List<Cliente> clientes = new ArrayList<>();
        boolean existe = true;
        clientes = datos.seleccionar();
        for (int i = 0; i < clientes.size(); i++) {
            if(!clientes.get(i).getDocIdentidad().equalsIgnoreCase(docIdentidad)){
                 existe = false;
            }else {
                System.out.println("ESTE DOCUMENTO DE INDENTIDAD YA TIENE CUENTA EN NUESTRO BANCO");
                existe = true;
            }
        }
        
        return existe;
        
    }
    
    public void intrducirDatosLogin()throws SQLException{
        Scanner ent = new Scanner(System.in);
        String docIdentidad = "";
        int opcion;
        int pass = 0;
        boolean disp = true;
        while(true){
                    System.out.println("ACCEDER SI YA TIENES CUENTA");
                    System.out.println("===================================");
                    System.out.println("1. Continuar.");
                    System.out.println("0. Volver.");
                    opcion = ent.nextInt();
                    ent.nextLine();
                    switch(opcion){
                        case 1:
                                System.out.println("INTRODUCE TU DOCUMENTO DE IDENTIDAD");
                                System.out.println("------------------------------------");
                                docIdentidad = ent.nextLine();
                                System.out.println("INTRODUCE TU CONTRASEÑA DE ACCESO");
                                System.out.println("------------------------------------");
                                pass = ent.nextInt();
                                disp = verificarLogin(docIdentidad, pass);
                            if(disp == false){
                                System.out.println("============================================");
                                System.out.println("BIENVENIDO/A "+saludoBienvenida(docIdentidad));
                                System.out.println("=============================================");
                                cuentaBancaria.mostrarDatosCuentaBanc(conseguirIdCliente(docIdentidad));
                                menuCliente(docIdentidad);
                            }else{
                                System.out.println("\nLOS DATOS INTRODUCIDOS SON INCORRECTOS O EL USUARIO NO EXISTE");
                            }
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("Debe seleccionar una opcion entre 0 y 1.");
                    }
                }
    }
    
    
    public void menuCliente(String docIdentidad)throws SQLException{
        int opcion;
        int nuevaPass = 0;
        Scanner menu = new Scanner(System.in);
        while(true){
            System.out.println("=====================================================================================================");
            System.out.println("=====================================   OPERACIONES DISPONIBLES   ===================================");
            System.out.println("=====================================================================================================");
            System.out.println("\t1.- Ver mis datos bancarios.");
            System.out.println("\t2.- Consultar saldo.");
            System.out.println("\t3.- Ver mis movimientos.");
            System.out.println("\t4.- Realizar una transacción.");
            System.out.println("\t5.- Pedir un prestamo.");
            System.out.println("\t6.- Cambiar contraseña.");
            System.out.println("\t0.- Cerrar sesión.");
            System.out.println("\tElige una opcion");
        opcion = menu.nextInt();
        switch (opcion) {
            case 1:
                cuentaBancaria.mostrarDatosCuentaBanc(conseguirIdCliente(docIdentidad));
                break;
            case 2:
                cuentaBancaria.mostrarSaldo(conseguirIdCliente(docIdentidad));
                break;
            case 3:
                System.out.println("ººººººººººººººººººººººººººººººººººººººº     MOVIMIENTOS     ºººººººººººººººººººººººººººººººººººººººº");
                transaccion.listarTransacciones(conseguirIdCliente(docIdentidad));
                System.out.println("ººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººº");
                prestamo.listarPrestamos(conseguirIdCliente(docIdentidad));
                System.out.println("ººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººººº");
                break;
            case 4:
                int idCuenta;
                idCuenta = cuentaBancaria.conseguirIdCuentaBancaria(conseguirIdCliente(docIdentidad));
                transaccion.hacerTransaccion(conseguirIdCliente(docIdentidad),idCuenta);
                break;
            case 5:
                System.out.println("NUESTRO BANCO TE ACEPTA LOS PRESTAMOS EN CUESTION DE HORAS SOLO\n"
                        + "TIENES QUE DECIRNOS CUENTO QUIERES Y SE TE RESPONDEREMOS LO ANTES POSIBLE");
                prestamo.solicitarPrestamo(conseguirIdCliente(docIdentidad));
                break;
            case 6:
                System.out.println("PARA CAMBIAR LA CONTRASEÑA SIGA LAS INTRUCCIONES PORFAVOR");
                System.out.println("Por favor escriba la nueva contraseña.");
                nuevaPass = menu.nextInt();
                if(nuevaPass < 1000){
                    System.out.println("La contraseña debe contener 4 digitos como minimo");
                    break;
                }else {
                    datos.actualizarPassword(conseguirIdCliente(docIdentidad), nuevaPass);
                    System.out.println("\nCONTRASEÑA CAMBIADA");
                    break;
                }
            case 0:
                System.out.println("Gracias por utilizar la aplicación");
                return;
            default:
                System.out.println("Debe seleccionar una opción entre 0 y 5");
            }
        }
    }
    
    public boolean verificarLogin(String docIdentidad, int pass) throws SQLException{
        
        List<Cliente> clientes = new ArrayList<>();
        boolean existe = true;
        clientes = datos.seleccionar();
        for (int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).getDocIdentidad().equalsIgnoreCase(docIdentidad) && clientes.get(i).getPassword() == pass){
                 existe = false;
            }
        }
        return existe;
    }
    
    public int conseguirIdCliente(String docIdentidad)throws SQLException{
        
        List<Cliente> clientes = new ArrayList<>();
        int idCliente = 0;
        
        clientes = datos.seleccionar();
        for (int i = 0; i < clientes.size(); i++){
            if(clientes.get(i).getDocIdentidad().equalsIgnoreCase(docIdentidad)){
                idCliente = clientes.get(i).getIdUsuario();
            }
        }
        
        return idCliente;
        
    }
    
    public String saludoBienvenida(String docIdentidad)throws SQLException{
        List<Cliente> clientes = new ArrayList<>();
        String nombre = "";
        String apellidos = "";
        String saludo = "";
        
        clientes = datos.seleccionar();
        for (int i = 0; i < clientes.size(); i++){
            if(clientes.get(i).getDocIdentidad().equalsIgnoreCase(docIdentidad)){
                nombre = clientes.get(i).getNombre();
                apellidos = clientes.get(i).getApellido();
            }
        }
        saludo = nombre+" "+apellidos;
        
        return saludo;
    }
}
