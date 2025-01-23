package com.turnos.model;

import com.turnos.persistencia.ControladoraPersistencia;
import java.time.LocalDate;
import java.util.List;

public class ControladoraModel {

    private final ControladoraPersistencia controlPersistencia = new ControladoraPersistencia();

    public void crearTurno(LocalDate fecha, String tema, String descripcion, String nombre, String apellido, String telefono) {
        Ciudadano ciudadano = new Ciudadano(nombre, apellido, telefono);
        controlPersistencia.crearCiudadano(ciudadano);

        Turno turno = new Turno(fecha, tema, descripcion, "En espera", ciudadano);
        controlPersistencia.crearTurno(turno);
    }

    public void borrarTurnoLogico(Long id) throws Exception {
        controlPersistencia.borrarTurnoLogico(id);
    }

    public List<Turno> buscarPorFecha(LocalDate busquedaFecha) {
        return controlPersistencia.buscarPorFecha(busquedaFecha);
    }

    public List<Turno> buscarPorStatus(String busquedaStatus) {
        return controlPersistencia.buscarPorStatus(busquedaStatus);
    }
    
    public void actualizarEstadoTurno(Long idTurno, String nuevoEstado) throws Exception {
    controlPersistencia.actualizarEstadoTurno(idTurno, nuevoEstado);
    }
    
    
    
    
}

