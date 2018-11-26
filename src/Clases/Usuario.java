/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author usuario
 */
public class Usuario {
   String rut;
   String pass;
   String nombre;
   String paterno;
   String materno;
   Date fechaIngreso;
   String correo;
   String fono;
   int habilitado;
   String nacionalidad;
   int id_tipoUsuario;
   int id_Unidad;
   int dias;

    public Usuario() {
    }

    public Usuario(String rut, String pass, String nombre, String paterno, String materno, Date fechaIngreso, String correo, String fono, int habilitado, String nacionalidad, int id_tipoUsuario, int id_Unidad, int dias) {
        this.rut = rut;
        this.pass = pass;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.fechaIngreso = fechaIngreso;
        this.correo = correo;
        this.fono = fono;
        this.habilitado = habilitado;
        this.nacionalidad = nacionalidad;
        this.id_tipoUsuario = id_tipoUsuario;
        this.id_Unidad = id_Unidad;
        this.dias = dias;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getId_tipoUsuario() {
        return id_tipoUsuario;
    }

    public void setId_tipoUsuario(int id_tipoUsuario) {
        this.id_tipoUsuario = id_tipoUsuario;
    }

    public int getId_Unidad() {
        return id_Unidad;
    }

    public void setId_Unidad(int id_Unidad) {
        this.id_Unidad = id_Unidad;
    }
    
    public int getDias(){
        return dias;
    }
    public void setDias(int dias)
    {
        this.dias = dias;
    }

    @Override
    public String toString() {
        return "Usuario{" + "rut=" + rut + ", pass=" + pass + ", nombre=" + nombre + ", paterno=" + paterno + ", materno=" + materno + ", fechaIngreso=" + fechaIngreso + ", correo=" + correo + ", fono=" + fono + ", habilitado=" + habilitado + ", nacionalidad=" + nacionalidad + ", id_tipoUsuario=" + id_tipoUsuario + ", id_Unidad=" + id_Unidad + ", dias = "+ dias+'}';
    }

    

}
