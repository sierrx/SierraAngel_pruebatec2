package com.turnos.persistencia;

import com.turnos.model.Ciudadano;
import com.turnos.model.Turno;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

public class ControladoraPersistencia {

    CiudadanoJpaController ciudadanoJpa = new CiudadanoJpaController();
    TurnoJpaController turnoJpa = new TurnoJpaController();

    public void crearCiudadano(Ciudadano ciudadano) {
        ciudadanoJpa.create(ciudadano);
    }

    public void crearTurno(Turno turno) {
        turnoJpa.create(turno);
    }

    public void borrarTurnoLogico(Long id) throws Exception {
        turnoJpa.borrarLogico(id);
    }

    public List<Turno> buscarPorFecha(LocalDate fecha) {
        return turnoJpa.findTurnoByFecha(fecha);
    }

    public List<Turno> buscarPorStatus(String busquedaStatus) {
        return turnoJpa.findTurnoByStatus(busquedaStatus);
    }

  public void actualizarEstadoTurno(Long idTurno, String nuevoEstado) throws Exception {
    turnoJpa.actualizarEstado(idTurno, nuevoEstado);
    }

    
}
