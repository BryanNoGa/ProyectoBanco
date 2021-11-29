package com.ceep.banco.Negocio;

/**
 * @author braya
 */
import com.ceep.banco.accesobd.*;
import com.ceep.banco.dominio.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SolicitudPrestamoNegocio {
    
    private final AccesoPrestamos AccPrestamo;
    private final AccesoSolicitudPrestamos solPrestamo;
    private final AccesoCuentasBancarias cuentasBancarias;

    public SolicitudPrestamoNegocio() {
        this.AccPrestamo = new AccesoPrestamos();
        this.solPrestamo = new AccesoSolicitudPrestamos();
        this.cuentasBancarias = new AccesoCuentasBancarias();
    }
    
    
    public void autorizarPrestamo(int idSolicitudPrestamo) throws SQLException{
        Scanner ent = new Scanner(System.in);
        List<SolicitudPrestamo> solicitudprestamos = new ArrayList<>();
        List<CuentaBancaria> cuentabancarias = new ArrayList<>();
        
        
        int idCliente = 0;
        double monto = 0;
        double montoSolicitado = 0;
        double montoNuevo;
        double pagoMensual;
        double interesAnual;
        String fechaFin;
        String fechaActual;
        int cuentaBancaria = 0;
        
        solicitudprestamos = solPrestamo.seleccionar();
        for (int i = 0; i < solicitudprestamos.size(); i++) {
            if(solicitudprestamos.get(i).getIdSolicitudPrestamo() == idSolicitudPrestamo){
                idCliente = solicitudprestamos.get(i).getIdCliente();
                montoSolicitado = solicitudprestamos.get(i).getMontoSolicitado();
            } 
        }
        System.out.println(idCliente);
        
        cuentabancarias = cuentasBancarias.seleccionar();
        
        for (int i = 0; i < cuentabancarias.size(); i++) {
            if(cuentabancarias.get(i).getIdCliente() == idCliente){
                System.out.println(cuentabancarias.get(i).getIdCliente());
                monto = cuentabancarias.get(i).getSaldo();
                cuentaBancaria = cuentabancarias.get(i).getIdCuentaBancaria();
            } 
        }
        
        montoNuevo = monto + montoSolicitado;
        
        System.out.println("\nESCRIBA POR FAVOR EL PAGO MENSUAL");
        pagoMensual = ent.nextDouble();
        System.out.println("\nESCRIBA POR FAVOR EL INTERES ANUAL");
        interesAnual = ent.nextDouble();
        ent.nextLine();
        System.out.println("\nESCRIBA LA FECHA DE FIN DE PAGO PORFAVOR"
                + "\n EN ESTE FORMATO dd-mm-yyyy");
        fechaFin = ent.nextLine();
        if(pagoMensual < 10 ||
                interesAnual < 1 ||
                fechaFin.equalsIgnoreCase("")){
            System.out.println("Introduzca de forma correcta los datos por favor");
            return; 
        }
        // Fecha actual del dispositivo
            Date mydate = new Date();
            fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(mydate);
        Prestamos prestamo = new Prestamos(cuentaBancaria,idCliente,montoSolicitado,pagoMensual,interesAnual,fechaActual,fechaFin);
        AccPrestamo.insertar(prestamo);
        cuentasBancarias.actualizar(idCliente, montoNuevo);
        solPrestamo.delete(idCliente);
        
        
        System.out.println("SE HA AUTORIZADO EL PRESTAMO");
    }
    
}
