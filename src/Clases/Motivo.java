/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author usuario
 */
public class Motivo {
   int id_motivo;
   String nombreMotivo;
   

    public Motivo() {
    }

    public Motivo(int id_motivo, String nombreMotivo) {
        this.id_motivo = id_motivo;
        this.nombreMotivo = nombreMotivo;
    }

    public int getId_motivo() {
        return id_motivo;
    }

    public void setId_motivo(int id_motivo) {
        this.id_motivo = id_motivo;
    }

    public String getNombreMotivo() {
        return nombreMotivo;
    }

    public void setNombreMotivo(String nombreMotivo) {
        this.nombreMotivo = nombreMotivo;
    }

    @Override
    public String toString() {
        return "Motivo{" + "id_motivo=" + id_motivo + ", nombreMotivo=" + nombreMotivo + '}';
    }

   
    
    


    

   
}
