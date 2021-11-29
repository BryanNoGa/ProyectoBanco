package com.ceep.banco.dominio;

/**
 * @author braya
 */
public class Administrador extends Usuario{
    
    private String nombreAdmin;
    private String email;
    private String cargo;

    public Administrador() {
    }

    public Administrador(String nombreAdmin, String email, String cargo) {
        this.nombreAdmin = nombreAdmin;
        this.email = email;
        this.cargo = cargo;
    }

    public Administrador(String nombreAdmin, String email, String cargo, int idUsuario, String docIdentidad, int password) {
        super(idUsuario, docIdentidad, password);
        this.nombreAdmin = nombreAdmin;
        this.email = email;
        this.cargo = cargo;
    }

    public Administrador(String nombreAdmin, String email, String cargo, String docIdentidad, int password) {
        super(docIdentidad, password);
        this.nombreAdmin = nombreAdmin;
        this.email = email;
        this.cargo = cargo;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return super.toString()+ "\nNombre Administrador: " + nombreAdmin + "\n Email: " + email + "\n Cargo: " + cargo;
    }
    
    
    
}
