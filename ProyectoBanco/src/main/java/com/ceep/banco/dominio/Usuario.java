package com.ceep.banco.dominio;

/**
 * @author braya
 */

public abstract class Usuario {
    
    private int idUsuario;
    private String docIdentidad;
    private int password;

    public Usuario() {
    }

    public Usuario(int idUsuario, String docIdentidad, int password) {
        this.idUsuario = idUsuario;
        this.docIdentidad = docIdentidad;
        this.password = password;
    }

    public Usuario(int idUsuario, String docIdentidad) {
        this.idUsuario = idUsuario;
        this.docIdentidad = docIdentidad;
    }

    public Usuario(String docIdentidad, int password) {
        this.docIdentidad = docIdentidad;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public int getPassword() {
        return password;
    }

    public String getDocIdentidad() {
        return docIdentidad;
    }

    public void setDocIdentidad(String docIdentidad) {
        this.docIdentidad = docIdentidad;
    }

    public void setPassword(int password) {
        this.password = password;
    }
    

    @Override
    public String toString() {
        return "\n Identificador de usuario: " + idUsuario + "\n Documento de identidad: " + docIdentidad;
    }
    
    
    
}
