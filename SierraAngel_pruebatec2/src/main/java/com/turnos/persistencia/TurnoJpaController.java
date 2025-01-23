package com.turnos.persistencia;

import com.turnos.model.Turno;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;

public class TurnoJpaController implements Serializable {

    private final EntityManagerFactory emf;

    public TurnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turno turno) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(turno);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void borrarLogico(Long id) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Optional.ofNullable(em.find(Turno.class, id))
                    .ifPresentOrElse(
                            turno -> {
                                turno.setActivo(false);
                                em.merge(turno);
                            },
                            () -> {
                                throw new RuntimeException("Turno no encontrado");
                            }
                    );
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new Exception(e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Turno> findTurnoByFecha(LocalDate busquedaFecha) {
        EntityManager em = getEntityManager();
        try {
            String consulta = "SELECT tur FROM Turno tur WHERE tur.fecha = :busquedaFecha AND tur.activo = true";
            Query query = em.createQuery(consulta);
            query.setParameter("busquedaFecha", busquedaFecha);
            List<Turno> turnos = query.getResultList();
            return turnos.stream()
                         .filter(turno -> turno.getFecha().equals(busquedaFecha))
                         .sorted((t1, t2) -> t1.getTema().compareToIgnoreCase(t2.getTema()))
                         .toList();
        } finally {
            em.close();
        }
    }

    public List<Turno> findTurnoByStatus(String busquedaStatus) {
        EntityManager em = getEntityManager();
        try {
            String consulta = "SELECT tur FROM Turno tur WHERE tur.status = :busquedaStatus AND tur.activo = true";
            Query query = em.createQuery(consulta);
            query.setParameter("busquedaStatus", busquedaStatus);
            List<Turno> turnos = query.getResultList();
            return turnos.stream()
                         .map(turno -> {
                             turno.setTema(turno.getTema().toUpperCase()); // Ejemplo: transformar el tema
                             return turno;
                         })
                         .toList();
        } finally {
            em.close();
        }
    }

    public void actualizarEstado(Long idTurno, String nuevoEstado) throws Exception {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Optional.ofNullable(em.find(Turno.class, idTurno))
                    .ifPresentOrElse(
                            turno -> {
                                turno.setStatus(nuevoEstado);
                                em.merge(turno);
                            },
                            () -> {
                                throw new RuntimeException("Turno no encontrado");
                            }
                    );
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            throw new Exception(e.getMessage());
        } finally {
            em.close();
        }
    }
}

