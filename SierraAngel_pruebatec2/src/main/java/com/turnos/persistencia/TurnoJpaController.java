package com.turnos.persistencia;

import com.turnos.model.Turno;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
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
            Turno turno = em.find(Turno.class, id);
            if (turno != null) {
                turno.setActivo(false);
                em.merge(turno);
            } else {
                throw new Exception("Turno no encontrado");
            }
            em.getTransaction().commit();
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
            return query.getResultList();
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
            return query.getResultList();
        } finally {
            em.close();
        }
    }

   public void actualizarEstado(Long idTurno, String nuevoEstado) throws Exception {
    EntityManager em = getEntityManager();
    try {
        em.getTransaction().begin();
        Turno turno = em.find(Turno.class, idTurno);
        if (turno != null) {
            turno.setStatus(nuevoEstado);
            em.merge(turno);
        } else {
            throw new Exception("Turno no encontrado");
        }
        em.getTransaction().commit();
    } finally {
        em.close();
    }
}
}

