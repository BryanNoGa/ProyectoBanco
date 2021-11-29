
package com.ceep.banco.Negocio;

/**
 * @author braya
 */
import com.ceep.banco.accesobd.*;
import com.ceep.banco.dominio.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.SQLException;

public class TransaccionesNegocio {
    
    private final AccesoTransacciones datos;
    private final CuentaBancariaNegocio cuentaBancaria;
    private final AccesoCuentasBancarias accesoCuenta;
    

    public TransaccionesNegocio() {
        this.datos = new AccesoTransacciones();
        this.cuentaBancaria = new CuentaBancariaNegocio();
        this.accesoCuenta = new AccesoCuentasBancarias();
    }
    
    
    
    public void hacerTransaccion(int idCliente, int idCuentaBancaria)throws SQLException{
        String fechaActual = "";
        String nombreDestinatario = "";
        String numeroCuentaDestinatario = "";
        String concepto = ""; 
        double monto = 0;
        Scanner ent = new Scanner(System.in);
        System.out.println("INTRODUCES LOS DATOS NECESARIOS PARA REALIZAR UNA TRANSACCIÓN");
        System.out.println("Introduzca el nombre del destinatario: ");
        nombreDestinatario = ent.nextLine();
        System.out.println("Introduzca el numero de cuenta del destinatario: ");
        numeroCuentaDestinatario = ent.nextLine();
        System.out.println("Concepto de la transacción puede estar vacio: ");
        concepto = ent.nextLine();
        if(concepto.equalsIgnoreCase("")){
            concepto = "Sin Concepto";
        }
        System.out.println("Monto de la transacción: ");
        monto = ent.nextDouble();
        if(!verificarMontoCliente(idCliente,monto)){
            System.out.println("No tiene saldo suficiente para realizar la transaccion");
            return;
        }
        System.out.println("----------------------------------------------------");
        // Fecha actual del dispositivo
            Date mydate = new Date();
            fechaActual = new SimpleDateFormat("dd-MM-yyyy").format(mydate);
        //=============================
        if(nombreDestinatario.equalsIgnoreCase("") ||
                numeroCuentaDestinatario.equalsIgnoreCase("") ||
                monto < 9){
            System.out.println("\nTodos los datos son necesarios por favor intentelo de nuevo");
            System.out.println("Recuerde que el monto minimo debe ser 10 euros");
            return;
        }
        System.out.println("USTED ESTÁ APUNTO DE REALIZAR UN TRANSACCION A \n-" + nombreDestinatario +"\n"
                + " CON NUMERO DE CUENTA \n-" + numeroCuentaDestinatario + "\n"
                + " Y CONCEPTO \n-" + concepto + "\n POR UN MONTO DE \n-" + monto + "€");
        int opcion;
        while(true){
            System.out.println("\n=====================================================================================================");
            System.out.println("=====================================   REALIZAR TRANSACCIÓN   ======================================");
            System.out.println("=====================================================================================================");
            System.out.println("\t1.- Continuar con la transacción.");
            System.out.println("\t0.- CANCELAR TRANSACCIÓN.");
            opcion = ent.nextInt();
            switch (opcion){
                case 1:
                    cuentaBancaria.modificarSaldo(idCuentaBancaria,monto,idCliente);
                    datos.insertar(new Transacciones(idCuentaBancaria,idCliente,fechaActual,nombreDestinatario,numeroCuentaDestinatario,concepto,monto));
                    System.out.println("TRANSACCIÓN REALIZADA CON EXITO");
                    detallesTransaccion(nombreDestinatario,numeroCuentaDestinatario,concepto,monto,fechaActual);
                    return;
                case 0:
                    System.out.println("Gracias por utilizar la aplicación");
                return;
                default:
                    System.out.println("Debe seleccionar una opción entre 0 y 1");
            }
        }
        
        
    }
    
    public boolean verificarMontoCliente(int idCliente, double montoTransaccion) throws SQLException{
        
        List<CuentaBancaria> cuentas = new ArrayList<>();
        boolean tiene = false;
        double montoDisp = 0;
        cuentas = accesoCuenta.seleccionar();
        
        for (int i = 0; i < cuentas.size(); i++) {
            if(cuentas.get(i).getIdCliente() == idCliente){
                montoDisp = cuentas.get(i).getSaldo();
            }
        }
        
        if(montoDisp > montoTransaccion){
            tiene = true;
        }
        return tiene;
    }
    
    public void detallesTransaccion(String nombreDestinatario, String numeroCuentaDestinatario, String concepto,double monto,String fechaActual){
        System.out.println("\n=====================================================================================================");
        System.out.println("=====================================   DETALLES TRANSACCIÓN   ======================================");
        System.out.println("=====================================================================================================");
        System.out.println("\t\t\t NOMBRE DEL DESTINATARIO: "+ nombreDestinatario);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t NUMERO DE CUENTA DEL DESTINATARIO: "+ numeroCuentaDestinatario);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t CONCEPTO DE LA TRANSACCIÓN : "+ concepto);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t MONTO DE LA TRANSACCIÓN : "+ monto);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("\t\t\t CON FECHA : "+ fechaActual);
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.println("");
    }
    
    
    public void listarTransacciones(int idCliente)throws SQLException{
        
        List<Transacciones> transacciones = new ArrayList<>();
        
        transacciones = datos.seleccionar(idCliente);
        if (transacciones.isEmpty()) {
            System.out.println("\n=====================================================================================================");
            System.out.println("===================================   DETALLES TRANSACCIONES   ======================================");
            System.out.println("=====================================================================================================");
            System.out.println("");
            System.out.println("-----------------------------------   NO HAY TRANSACCIONES    ----------------------------------------");
        }else {
            System.out.println("\n=====================================================================================================");
            System.out.println("===================================   DETALLES TRANSACCIONES   ======================================");
            System.out.println("=====================================================================================================");
                transacciones.forEach(transaccion->{
                    System.out.println(""+transaccion);
                });
        }
        
    }
    
    public void listarTransaccionesCliente(int idCliente)throws SQLException{
        List<Transacciones> transacciones = new ArrayList<>();
        
        transacciones = datos.seleccionarByDocIdentidad(idCliente);
        
        System.out.println("\n=====================================================================================================");
        System.out.println("===================================   DETALLES TRANSACCIONES   ======================================");
        System.out.println("=====================================================================================================");
            transacciones.forEach(transaccion->{
                    System.out.println("Identificador transaccion: "+transaccion.getIdTransaccion()+"|"
                            + "Identificador cuenta bancaria: "+transaccion.getIdCuentaBancaria()+"|"
                            + "Identificador Cliente: "+transaccion.getIdCliente()+"|"
                            + "Fecha transaccion: "+transaccion.getFechaTransaccion()+"|"
                            + "Nombre destinatario: "+transaccion.getNombreDestinatario()+"|"
                            + "Cuenta destinatario: "+transaccion.getCuentaDestinatario()+"|"
                            + "Concepto: "+transaccion.getConcepto()+"|"
                            + "Monto: "+transaccion.getMonto());
            });
    }
}
