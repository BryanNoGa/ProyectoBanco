package Principal;

/**
 *
 * @author braya
 */
import com.ceep.banco.Negocio.ClienteNegocio;
import com.ceep.banco.Negocio.AdministradorNegocio;
import com.ceep.banco.excepcionesFicheros.AccesoFicheroEx;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Test {

    public static void main(String[] args) throws SQLException, AccesoFicheroEx, IOException {        
        menu();
    }
    
    public static void menu() throws SQLException, AccesoFicheroEx, IOException{
        int opcion;
        Scanner menu = new Scanner(System.in);
        while(true){
            System.out.println("===================================================================================================");
            System.out.println("=====================================   BIENVENIDO A TU BANCO   ===================================");
            System.out.println("===================================================================================================");
            System.out.println("\t1.- Acceder como cliente");
            System.out.println("\t2.- Accceder como Administrador.");
            System.out.println("\t0.- Salir.");
            System.out.println("\tElige una opcion");
        opcion = menu.nextInt();
        switch (opcion) {
            case 1:
                ClienteLogin();
                break;
            case 2:
                AdminLogin();
                break;
            case 0:
                System.out.println("Gracias por utilizar la aplicación");
                return;
            default:
                System.out.println("Debe seleccionar una opción entre 0 y 2");
            }
        }
    }
    
    public static void ClienteLogin() throws SQLException{
        int opcion;
        ClienteNegocio cliente = new ClienteNegocio();
        Scanner menu = new Scanner(System.in);
        while(true){
                    System.out.println("\n===================================");
                    System.out.println("MENU DE ACCESO PARA CLIENTES");
                    System.out.println("===================================");
                    System.out.println("1. Tengo cuenta en el banco.");
                    System.out.println("2. No tengo cuenta, abrir una.");
                    System.out.println("0. Volver.");
                    opcion = menu.nextInt();
                    switch(opcion){
                        case 1:
                            System.out.println("BIENVENIDO A TU BANCO DE CONFIANZA");
                            System.out.println("==================================");
                            System.out.println("==================================");
                            cliente.intrducirDatosLogin();
                            break;
                        case 2:
                            System.out.println("\nACONTINUACION VAMOS A CREAR UNA CUENTA\nPOR FAVOR SIGA LOS PASOS ->");
                            System.out.println("\n===========SU NUMERO DE CUENTA SERÁ CREADO AUTOMATICAMENTE============");
                            System.out.println("INTRODUZCA LOS DATOS EN EL ORDEN INDICADO");
                            cliente.crearCuentaNueva();
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("Debe seleccionar una opcion entre 0 y 2.");
                    }
                }
    }
    
    public static void AdminLogin() throws SQLException, AccesoFicheroEx, IOException{
        int opcion;
        AdministradorNegocio administrador = new AdministradorNegocio();
        Scanner menu = new Scanner(System.in);
        while(true){
                    System.out.println("\n===================================");
                    System.out.println("MENU DE ACCESO PARA ADMINISTRADORES");
                    System.out.println("===================================");
                    System.out.println("1. Acceder.");
                    System.out.println("0. Volver.");
                    opcion = menu.nextInt();
                    switch(opcion){
                        case 1:
                            System.out.println("\n==================================");
                            System.out.println("\tBIENVENIDO ADMINISTRADOR");
                            System.out.println("==================================");
                            administrador.intrducirDatosLogin();
                            break;
                        case 0:
                            return;
                        default:
                            System.out.println("Debe seleccionar una opcion entre 0 y 2.");
                    }
                }
    }
}
