package com.turnos.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = "Turno")
public class Turno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private LocalDate fecha;
    private String tema;
    private String descripcion;
    private String status;

    @Column(nullable = false)
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_ciudadano")
    private Ciudadano ciudadano;

    public Turno() {
    }

    public Turno(LocalDate fecha, String tema, String descripcion, String status) {
        this.fecha = fecha;
        this.tema = tema;
        this.descripcion = descripcion;
        this.status = status;
    }

    public Turno(LocalDate fecha, String tema, String descripcion, String status, Ciudadano ciudadano) {
        this.fecha = fecha;
        this.tema = tema;
        this.descripcion = descripcion;
        this.status = status;
        this.ciudadano = ciudadano;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    @Override
    public String toString() {
        return "Turno{" + "id=" + id + ", fecha=" + fecha + ", tema=" + tema + ", descripcion=" + descripcion + ", status=" + status + ", activo=" + activo + ", ciudadano=" + ciudadano + '}';
    }
}
