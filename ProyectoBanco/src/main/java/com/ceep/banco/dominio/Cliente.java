package com.ceep.banco.dominio;

/**
 * @author braya
 */
public class Cliente extends Usuario {
   
    private String nombre;
    private String apellido;
    private String email;
    private String direccion;
    private String fechaNacimiento;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String email, String direccion, String fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente(String nombre, String apellido, String email, String direccion, String fechaNacimiento, int idUsuario, String docIdentidad, int password) {
        super(idUsuario, docIdentidad, password);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente(String nombre, String apellido, String email, String direccion, String fechaNacimiento, String docIdentidad, int password) {
        super(docIdentidad, password);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente(int idUsuario, String docIdentidad, String nombre, String apellido, String email, String direccion, String fechaNacimiento) {
        super(idUsuario, docIdentidad);
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
            return super.toString()+ "\n Nombre: " + nombre + "\n Apellido: " + apellido + "\n Email: " + email + "\n Direccion: " + direccion + "\n Fecha de nacimiento: " + fechaNacimiento;
    }

    
    
}
