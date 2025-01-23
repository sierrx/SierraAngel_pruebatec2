package com.turnos.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name ="ciudadano")
public class Ciudadano implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ciudadano")
    private List<Turno> listaTurno;
    

    public Ciudadano() {
    }

    public Ciudadano(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }
    
    public Ciudadano(String nombre, String apellido, String telefono, List<Turno> listaTurno) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.listaTurno = listaTurno;
    }
    
    //getters n setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Turno> getListaTurno() {
        return listaTurno;
    }

    public void setListaTurno(List<Turno> listaTurno) {
        this.listaTurno = listaTurno;
    }
    
    //to string

    @Override
    public String toString() {
        return "Ciudadano{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", listaTurno=" + listaTurno + '}';
    }

  
    
    
    
}
