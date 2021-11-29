package com.ceep.banco.Negocio;

import com.ceep.banco.dominio.*;
import com.ceep.banco.accesobd.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author braya
 */
public class PrestamosNegocio {
    
    private final AccesoPrestamos datos;
    private final AccesoSolicitudPrestamos solPrestamo;

    public PrestamosNegocio() {
        this.datos = new AccesoPrestamos();
        this.solPrestamo = new AccesoSolicitudPrestamos();
    }
    
    public void solicitarPrestamo(int idCliente) throws SQLException{
        
        Scanner ent = new Scanner(System.in);
        String fechaActual = "";
        double montoSolicitar = 0;
        // Fecha actual del dispositivo
            Date mydate = new Date();
            fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(mydate);
        //=============================
        System.out.println("Introduzaca el monto que desea solicitar: ");
        montoSolicitar = ent.nextDouble();
        
        solPrestamo.insertar(new SolicitudPrestamo(idCliente, fechaActual, montoSolicitar));
        
    }
    
    public void listarPrestamos(int idCliente)throws SQLException{
        
        List<Prestamos> prestamos = new ArrayList<>();
        
        prestamos = datos.seleccionar(idCliente);
        if (prestamos.isEmpty()) {
            System.out.println("=====================================================================================================");
            System.out.println("===================================   DETALLES PRESTAMOS   ==========================================");
            System.out.println("=====================================================================================================");
            System.out.println("");
            System.out.println("-----------------------------------   NO HAY PRESTAMOS    -------------------------------------------");
        } else {
            System.out.println("=====================================================================================================");
            System.out.println("===================================   DETALLES PRESTAMOS   ==========================================");
            System.out.println("=====================================================================================================");
            prestamos.forEach(transaccion->{
                System.out.println(""+transaccion);
            });
        }
        
    }
    
    public void listarPrestamosCliente(int idCliente)throws SQLException{
        
        List<Prestamos> prestamos = new ArrayList<>();
        
        prestamos = datos.seleccionarByIdCliente(idCliente);
        
        System.out.println("\n=====================================================================================================");
        System.out.println("===================================   DETALLES PRESTAMOS   ==========================================");
        System.out.println("=====================================================================================================");
            prestamos.forEach(prestamo->{
                    System.out.println("Identificador prestamo: "+prestamo.getIdPrestamo()+"| "
                            + "Identificador cuenta bancaria: "+prestamo.getIdCuentaBancaria()+"| "
                            + "Identificador Cliente: "+prestamo.getIdCliente()+"| "
                            + "Monto solicitado: "+prestamo.getCantidadPrestamo()+"| "
                            + "Pago mensual: "+prestamo.getPagoMensual()+"| "
                            + "Interes anual: "+prestamo.getInteresAnual()+"| "
                            + "Fecha inicio del prestamo: "+prestamo.getFechaInicioPrestamo()+"| "
                            + "Fecha fin prestamo: "+prestamo.getFechaFinPrestamo());
            });
    }
}
