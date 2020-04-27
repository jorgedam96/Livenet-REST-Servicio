package com.livenet.livenet.mensaje;


import javax.persistence.*;

@Entity
@Table(name="mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "remitente", nullable = false)
    private String remitente;

    @Column(name = "destino", nullable = false)
    private String destino;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "fecha_hora", nullable = false)
    private String fecha_hora;

    public Mensaje() {
    }

    public Mensaje(String remitente, String destino, String contenido, String fecha_hora){
        this.remitente = remitente;
        this.destino = destino;
        this.contenido = contenido;
        this.fecha_hora = fecha_hora;
    }


    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "remitente='" + remitente + '\'' +
                ", destino='" + destino + '\'' +
                ", contenido='" + contenido + '\'' +
                ", fecha_hora='" + fecha_hora + '\'' +
                '}';
    }
}
