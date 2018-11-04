/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;


/**
 *
 * @author Esteban
 */
public class Unidad {
    int id_unidad;
    String descripcion;
    String nombre;

    public Unidad() {
    }

    public Unidad(int id_unidad, String descripcion, String nombre) {
        this.id_unidad = id_unidad;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public int getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(int id_unidad) {
        this.id_unidad = id_unidad;
    }

    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Unidad{" + "id_unidad=" + id_unidad + ", descripcion=" + descripcion + ", nombre=" + nombre + '}';
    }
    
}
